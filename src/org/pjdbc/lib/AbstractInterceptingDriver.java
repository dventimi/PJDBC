package org.pjdbc.lib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * <code>AbstractInterceptingDriver</code> is a subclassable instance
 * of <code>InterceptingDriver</code> that supplies most of the
 * functionality needed by such a driver.  New kinds of
 * <code>InterceptingDriver</code> always should subclass this class.  
 *
 * This particular <code>InterceptingDriver</code> accepts a
 * <code>SQLHandler</code> by way of a static method
 * <code>setHandler</code>, and produces <code>Statement</code>
 * objects (by way of a special <code>Connection</code> object).
 * These <code>Statement</code> objects invoke the Driver's
 * <code>SQLHandler</code>.  The handler may then transparently pass
 * the SQL to the delegate.  In most cases, however, it should do
 * something more interesting, such as:
 *
 * <ul>
 *   <li>log queries</li>
 *   <li>filter certain queries</li>
 *   <li>transform queries</li>
 * </ul>
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public abstract class AbstractInterceptingDriver implements InterceptingDriver {
    private static List<SQLHandler> handlers = new ArrayList<SQLHandler>();
    private class ConnectionInvocationHandler implements InvocationHandler {
	private Connection delegate = null;
	public ConnectionInvocationHandler (Connection delegate) {
	    this.delegate = delegate;}
	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
	    if (method.getName().equals("createStatement")) {
		Class[] api = new Class[]{Statement.class};
		Statement statement = (Statement)method.invoke(this.delegate, args);
		InvocationHandler handler = new StatementInvocationHandler(statement);
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), api, handler);}
	    return method.invoke(delegate, args);}}
    private class StatementInvocationHandler implements InvocationHandler {
	private Statement delegate = null;
	public StatementInvocationHandler (Statement delegate) {
	    this.delegate = delegate;}
	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
	    if (method.getName().equals("execute"))
		for (SQLHandler handler : handlers) 
		    handler.execute((String)args[0], this.delegate.getConnection());
	    return method.invoke(delegate, args);}}

    /**
     * <code>setHandler</code> registers a single
     * <code>SQLHandler</code> for intercepting
     * <code>Statement.execute()</code>.
     *
     * @param handler a <code>SQLHandler</code> value
     */
    public static void setHandler (SQLHandler handler) {
	handlers.clear();
	if (handler!=null) handlers.add(handler);}

    /**
     * The <code>connect</code> gets a connection for the provided URL
     * and connection Properties.  Note that the subname is treated as
     * a JDBC URL itself, and is used to fetch a
     * <code>Connection</code>, which itself will be provided by the
     * intercepted driver.  Effectively, the <code>Connection</code>
     * factory delegates to the intercepted driver, which is
     * determined by the URL indicated in the subname.  Note further
     * that this means the other driver(s) also need to be registered,
     * in the usual way.  TODO: Right now, we're coupled tightly to
     * DriverManager for delegating to the other driver.  It would be
     * nice to also cleanly and silently support using DataSources as
     * well.  TODO: InterceptingDriver SHOULD NOT be re-entrant.  That
     * is, while in general it will not check the validity of the URL
     * in the subname, it should at the very least check that the
     * subname URL does not itself refer to the InterceptingDriver.
     *
     * @param URL a <code>String</code> value
     * @param properties a <code>Properties</code> value
     * @return a <code>Connection</code> value
     * @exception SQLException if an error occurs
     */
    public final Connection connect (final String URL, final Properties properties) throws SQLException {
	Class[] api = new Class[]{Connection.class};
	Connection connection = DriverManager.getConnection("jdbc:" + (new ProxyUrl(URL)).getSubName(), properties);
	InvocationHandler handler = new ConnectionInvocationHandler(connection);
	return (Connection)Proxy.newProxyInstance(this.getClass().getClassLoader(), api, handler);}

    /**
     * <code>acceptsURL</code> reports whether the driver does or does not accept a given JDBC URL.
     * The driver accepts URLs that have three parts, separated by colons:
     * <ol>
     *   <li>protocol</li>
     *   <li>subprotocol</li>
     *   <li>subname</li>
     * </ol>
     * Note that the subname itself, for this driver, should itself
     * be a valid JDBC URL, though this driver will not check that directly.
     *
     * @param jdbcUrl a <code>String</code> value
     * @return a <code>boolean</code> value
     * @exception SQLException if an error occurs
     */
    public final boolean acceptsURL (final String jdbcUrl) throws SQLException {
	ProxyUrl url = new ProxyUrl(jdbcUrl);
	if (!url.getSubProtocol().equals(this.getSubProtocol())) return false;
	if (DriverManager.getDriver(url.getUrl())==null) return false;
	return true;}

    /**
     * <code>getParentLogger</code>
     *
     * @return a <code>Logger</code>, the parent <code>Logger<code> of all the loggers used by this driver
     */
    public final Logger getParentLogger () throws SQLFeatureNotSupportedException {
	throw new SQLFeatureNotSupportedException();}

    /**
     * <code>getMajorVersion</code>
     * Retrieves the driver's major version number. Initially this should be 1.
     *
     * @return an <code>int</code> value, this driver's major version number.
     */
    public final int getMajorVersion () {return 1;}

    /**
     * <code>getMinorVersion</code> returns the driver's minor version
     * number. Initially this should be 0.
     *
     * @return an <code>int</code> value, this driver's minor version number.
     */
    public final int getMinorVersion () {return 0;}

    /**
     * <code>getPropertyInfo</code> returns information about the
     * possible properties for this driver.
     *
     * @param URL a <code>String</code> value
     * @param properties a <code>Properties</code> value
     * @return a <code>DriverPropertyInfo[]</code> value
     * @exception SQLException if an error occurs
     */
    public final DriverPropertyInfo[] getPropertyInfo (final String URL, final Properties properties) throws SQLException {
	return DriverManager.getDriver(URL).getPropertyInfo(URL, properties);}

    /**
     * <code>jdbcCompliant</code> reports whether this driver is a
     * genuine JDBC Compliant driver.  This driver has not passed the
     * JDBC Compliance tests, nor has any effort been made to run such
     * tests.  It is unlikely such a project ever would succeed, since
     * this driver serves only to filter query strings passed to a
     * delegate JDBC driver, which itself may or may not be fully JDBC
     * compliant.
     *
     * @return a <code>boolean</code> value
     */
    public final boolean jdbcCompliant() {return false;}}
