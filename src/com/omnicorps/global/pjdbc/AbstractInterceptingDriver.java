package com.omnicorps.global.pjdbc; // Generated package name

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Describe class <code>InterceptingDriver</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public abstract class AbstractInterceptingDriver 
    implements InterceptingDriver {

    protected static List<HookFunction<String>> hooks = new ArrayList<HookFunction<String>>();

    static {
	System.out.println("hooks.size() = " + hooks.size());
    }

    public static void addHook (HookFunction<String> hook) {
	System.out.println("Adding hook:  " + hook);
	hooks.add(hook);
    }

    /**
     * Describe <code>registerDriver</code> method here.
     *
     * @param driver a <code>Driver</code> value
     */
    public static void registerDriver (Driver driver) {
	try {
	    DriverManager.registerDriver(driver);
	}
	catch (SQLException se) {
	    throw new RuntimeException(se);
	}
    }

    // Implementation of java.sql.Driver

    public final String getProtocol () {
	return "jdbc";
    }

    /**
     * <code>connect</code>
     * Get a connection for the provided URL and connection Properties
     * Note that the subname is treated as a JDBC URL itself, and is
     * used to fetch a <code>Connection</code>, which itself will be
     * provided by another driver.  Effectively, the <code>Connection</code>
     * factory delegates to another driver, determined by the URL
     * indicated by the subname.  
     * Note further that this means the other driver(s) also need
     * to be registered, in the usual way.
     * TODO:  Right now, we're coupled tightly to DriverManager for
     * delegating to the other driver.  It would be nice to also
     * cleanly and silently support using DataSources as well.
     * TODO:  InterceptingDriver SHOULD NOT be re-entrant.  That is,
     * while in general it will not check the validity of the URL in the
     * subname, it should at the very least check that the subname URL
     * does not itself refer to the InterceptingDriver.
     *
     * @param URL a <code>String</code> value
     * @param properties a <code>Properties</code> value
     * @return a <code>Connection</code> value
     * @exception SQLException if an error occurs
     */
    public final Connection connect(final String URL, final Properties properties) throws SQLException {
	if (!this.acceptsURL(URL)) throw new SQLException("Malformed JDBC URL.");
	Class[] api = new Class[]{Connection.class};
	Connection connection = DriverManager.getConnection("jdbc:" + (new JDBCURL(URL)).getSubname(), properties);
	InvocationHandler handler = new ConnectionInvocationHandler(connection);
	return (Connection)Proxy.newProxyInstance(this.getClass().getClassLoader(), api, handler);
    }

    /**
     * <code>acceptsURL</code>
     * Report whether the driver does or does not accept a given JDBC URL.
     * The driver accepts URLs that have three parts, separated by colons:
     * <ol>
     *   <li>protocol</li>
     *   <li>subprotocol</li>
     *   <li>subname</li>
     * </ol>
     * Note that the subname itself, for this driver, should itself
     * be a valid JDBC URL, though this driver will not check that directly.
     *
     * @param URL a <code>String</code> value
     * @return a <code>boolean</code> value
     * @exception SQLException if an error occurs
     */
    public final boolean acceptsURL(final String URL) throws SQLException {
	JDBCURL parsedURL = new JDBCURL(URL);
	if (!parsedURL.getProtocol().equals(this.getProtocol())) return false;
	if (!parsedURL.getSubprotocol().equals(this.getSubProtocol())) return false;
	if (parsedURL.getSubname()==null) return false;
	return true;
    }

    /**
     * <code>getMajorVersion</code>
     * Retrieves the driver's major version number. Initially this should be 1.
     *
     * @return an <code>int</code> value, this driver's major version number.
     */
    public final int getMajorVersion() {
	return 1;
    }

    /**
     * <code>getMinorVersion</code>
     * Gets the driver's minor version number. Initially this should be 0.
     *
     * @return an <code>int</code> value, this driver's minor version number.
     */
    public final int getMinorVersion() {
	return 0;
    }

    /**
     * <code>getPropertyInfo</code>
     * Gets information about the possible properties for this driver.
     *
     * @param URL a <code>String</code> value
     * @param properties a <code>Properties</code> value
     * @return a <code>DriverPropertyInfo[]</code> value
     * @exception SQLException if an error occurs
     */
    public final DriverPropertyInfo[] getPropertyInfo(final String URL, 
						      final Properties properties) 
	throws SQLException {
	return DriverManager.getDriver(URL).getPropertyInfo(URL, properties);
    }

    /**
     * <code>jdbcCompliant</code>
     * Reports whether this driver is a genuine JDBC Compliant driver.
     * This driver has not passed the JDBC Compliance tests, nor has any
     * effort been made to run such tests.  It is unlikely such a project
     * ever would succeed, since this driver serves only to filter
     * query strings passed to a delegate JDBC driver, which itself may
     * or may not be fully JDBC compliant.
     *
     * @return a <code>boolean</code> value
     */
    public final boolean jdbcCompliant() {
	return false;
    }

    /**
     * Describe class <code>ConnectionInvocationHandler</code> here.
     *
     * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
     * @version 1.0
     */
    public class ConnectionInvocationHandler 
	implements InvocationHandler {
	private Connection delegate = null;
	/**
	 * Creates a new <code>ConnectionInvocationHandler</code> instance.
	 *
	 * @param delegate a <code>Connection</code> value
	 */
	public ConnectionInvocationHandler (Connection delegate) {
	    this.delegate = delegate;
	}

	/**
	 * Describe <code>invoke</code> method here.
	 *
	 * @param proxy an <code>Object</code> value
	 * @param method a <code>Method</code> value
	 * @param args an <code>Object</code> value
	 * @return an <code>Object</code> value
	 * @exception Throwable if an error occurs
	 */
	public Object invoke (Object proxy,
			      Method method,
			      Object[] args)
	    throws Throwable {
	    if (method.getName().equals("createStatement")) {
		Class[] api = new Class[]{Statement.class};
		Statement statement = (Statement)method.invoke(this.delegate, args);
		InvocationHandler handler = new StatementInvocationHandler(statement);
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), api, handler);
	    }
	    if (method.getName().equals("prepareStatement")) {
		String sql = (String)args[0];
		// transform sql
		Class[] api = new Class[]{Statement.class};
		PreparedStatement preparedStatement = (PreparedStatement)method.invoke(this.delegate, args);
		InvocationHandler handler = new PreparedStatementInvocationHandler(preparedStatement);
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), api, handler);
	    }
	    return method.invoke(delegate, args);
	}
    }

    /**
     * Describe class <code>StatementInvocationHandler</code> here.
     *
     * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
     * @version 1.0
     */
    public class StatementInvocationHandler 
	implements InvocationHandler {
	private Statement delegate = null;
	/**
	 * Creates a new <code>StatementInvocationHandler</code> instance.
	 *
	 * @param delegate a <code>Statement</code> value
	 */
	public StatementInvocationHandler (Statement delegate) {
	    this.delegate = delegate;
	}

	/**
	 * Describe <code>invoke</code> method here.
	 *
	 * @param proxy an <code>Object</code> value
	 * @param method a <code>Method</code> value
	 * @param args an <code>Object</code> value
	 * @return an <code>Object</code> value
	 * @exception Throwable if an error occurs
	 */
	public Object invoke (Object proxy,
			      Method method,
			      Object[] args)
	    throws Throwable {
	    String name = method.getName();
	    Class[] params = method.getParameterTypes();
 	    if (name.equals("execute")) {
		String[] sql = new String[]{(String)args[0]};
		System.out.println("hooks.size() = " + hooks.size());
		for (HookFunction<String> fn : hooks) {
		    sql = fn.eval(sql);
		    System.out.println("sql[0] = " + sql[0]);
		}
		    System.out.println("sql[0] = " + sql[0]);
		args[0] = sql[0];
	    }
	    return method.invoke(delegate, args);
	}
    }

    /**
     * Describe class <code>PreparedStatementInvocationHandler</code> here.
     *
     * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
     * @version 1.0
     */
    public class PreparedStatementInvocationHandler 
	implements InvocationHandler {
	private PreparedStatement delegate = null;
	/**
	 * Creates a new <code>PreparedStatementInvocationHandler</code> instance.
	 *
	 * @param delegate a <code>PreparedStatement</code> value
	 */
	public PreparedStatementInvocationHandler (PreparedStatement delegate) {
	    this.delegate = delegate;
	}

	/**
	 * Describe <code>invoke</code> method here.
	 *
	 * @param proxy an <code>Object</code> value
	 * @param method a <code>Method</code> value
	 * @param args an <code>Object</code> value
	 * @return an <code>Object</code> value
	 * @exception Throwable if an error occurs
	 */
	public Object invoke (Object proxy,
			      Method method,
			      Object[] args)
	    throws Throwable {
	    String name = method.getName();
	    Class[] params = method.getParameterTypes();
	    if (name.equals("execute") && 
		params.length > 0 &&
		params[0].getClass().equals(String.class)) {
		String[] sql = new String[]{(String)args[0]};
		for (HookFunction<String> fn : hooks) sql = fn.eval(sql);
		args[0] = sql[0];
		return method.invoke(proxy, method, args);
	    }
	    return method.invoke(delegate, args);
	}
    }
}

