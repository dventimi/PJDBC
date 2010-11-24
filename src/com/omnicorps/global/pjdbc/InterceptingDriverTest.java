package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 * Describe class <code>InterceptingDriverTest</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public class InterceptingDriverTest 
    extends AutoTest {

    Server hsqlServer = null;
    Connection connection = null;

    // ----------------- Helper Classes ---------------------------------


    public static class CollectingHandler implements HookFunction<String> {
	private List<String> sqlStatements = new ArrayList<String>();
	public String[] eval (String[] args) {
	    this.sqlStatements.add(args[0]);
	    return args;
	}
	public List<String> getSQLStatements () {
	    return this.sqlStatements;
	}
    }

    public static class DevNullHandler implements HookFunction<String> {
	public String[] eval (String[] args) {
	    return new String[]{""};
	}
    }

    // ----------------- Static Members ---------------------------------
    
    /**
     * <code>main</code>
     * Bootstrap code to call the superclass's autorun static method.
     * This makes a TestCase a stand-alone runnable class.
     *
     * @param args a <code>String</code> value
     */
    public static void main(String[] args){
	autorun(new Exception());
    }

    // ----------------- Setup & Teardown ------------------------------

    /**
     * Every test generally needs to work with a registered driver
     * so go ahead and register the drivers.  Also, try to start a running
     * HSQLDB Server.  Check to see if one is running first, and try to
     * nuke it if it is.
     *
     * @exception ClassNotFoundException if an error occurs
     */
    public void setUp ()
	throws ClassNotFoundException {
	if (this.hsqlServer!=null) {
	    try {
		this.hsqlServer.stop();
		this.hsqlServer = null;
	    }
	    catch (Exception e) {
		fail("Server already running and could not be stopped.");
	    }
	}
	this.hsqlServer = new Server();
	this.hsqlServer.setLogWriter(null);
	this.hsqlServer.setSilent(true);
	this.hsqlServer.setDatabaseName(0, "database");
	this.hsqlServer.setDatabasePath(0, "mem:database");
	this.hsqlServer.start();
	Class.forName("com.omnicorps.global.pjdbc.IdentityInterceptingDriver");
	Class.forName("com.omnicorps.global.pjdbc.NullInterceptingDriver");
	Class.forName("com.omnicorps.global.pjdbc.GenericInterceptingDriver");
	Class.forName("org.hsqldb.jdbcDriver");
    }

    /**
     * Try to stop the running HSSQLDB server instance.
     *
     */
    public void tearDown () {
	this.hsqlServer.stop();
    }

    // --------------------- Tests ----------------------------------
    
    /**
     * <code>testJDBCURLParsesThreePartURLs</code>
     * JDBCURL is a helper class.  Verify that it accepts a JDBC URL
     * that has the protocol, subprotocol, and subname, separated by
     * colons.
     */
    public void testJDBCURLParsesThreePartURLs () {
	String [] parsedURL = JDBCURL.parseURL("jdbc:intercepting:jdbc");
	assertNotNull(parsedURL);
	assertEquals(3, parsedURL.length);
	assertEquals("jdbc", parsedURL[0]);
	assertEquals("intercepting", parsedURL[1]);
	assertEquals("jdbc", parsedURL[2]);
    }

    /**
     * <code>testJDBCURLParsesFivePartURLs</code>
     * Verify that JDBCURL accepts a JDBCURL that has
     * the protocol, subprotocol, and subname, separated by
     * colons, but in which the subname also is itself a valid
     * JDBC URL, complete with its own protocol, subprotocol, and
     * subname.  The subname should be left intact and not
     * itself split into three parts.
     */
    public void testJDBCURLParsesFivePartURLs () {
	String [] parsedURL = JDBCURL.parseURL("jdbc:intercepting:jdbc:subprotocol:subname");
	assertNotNull(parsedURL);
	assertEquals(3, parsedURL.length);
	assertEquals("jdbc", parsedURL[0]);
	assertEquals("intercepting", parsedURL[1]);
	assertEquals("jdbc:subprotocol:subname", parsedURL[2]);
    }

    /**
     * <code>testJDBCURLRefusesFewerThanThreePartURLs</code>
     * Verify that JDBCURL throws a SQLException if 
     * a three-part (protocol, subprotocol, subname) URL 
     * is not provided.
     *
     */
    public void testJDBCURLRefusesFewerThanThreePartURLs () {
    	try {
    	    new JDBCURL("jdbc:intercepting");
    	}
    	catch (SQLException e) {
    	    assertNotNull(e);
    	    return;
    	}
    	fail("JDBCURL should not have accepted a URL with only 2 parts.");
    }

    /**
     * <code>testJDBDURLAcceptsThreePartURLs</code>
     * Verify that a three-part URL can be passed into
     * the JDBCURL constructor.
     *
     * @exception SQLException if an error occurs
     */
    public void testJDBDURLAcceptsThreePartURLs () 
	throws SQLException {
	JDBCURL url = new JDBCURL("jdbc:intercepting:protocol");
	assertEquals("jdbc", url.getProtocol());
	assertEquals("intercepting", url.getSubprotocol());
	assertEquals("protocol", url.getSubname());
    }
    
    /**
     * <code>testJDBDURLAcceptsMoreThanThreePartURLs</code>
     * Verify that JDBCURL can be passed a URL whose
     * subname is itself a JDBC URL.  Note that it should
     * not parse the subnname itself into separate pieces.
     *
     * @exception SQLException if an error occurs
     */
    public void testJDBDURLAcceptsMoreThanThreePartURLs () 
	throws SQLException {
	JDBCURL url = new JDBCURL("jdbc:intercepting:jdbc:subprotocol:subname:extra");
	assertEquals("jdbc", url.getProtocol());
	assertEquals("intercepting", url.getSubprotocol());
	assertEquals("jdbc:subprotocol:subname:extra", url.getSubname());
    }

    /**
     * <code>testInterceptingDriverAcceptsURL</code>
     * Verify that the acceptsURL method on the driver
     * returns true for a five-part URL.
     *
     * @exception SQLException if an error occurs
     */
    public void testInterceptingDriverAcceptsURL () 
    	throws SQLException {
    	Driver driver = new IdentityInterceptingDriver();
    	assertTrue(driver.acceptsURL("jdbc:identity-intercepting:jdbc:subprotocol:subname"));
    }

    /**
     * <code>testHSQLDBServerStart</code>
     * Perform a sanity check just to make sure we actually can start a server
     * for HSSQLDB and connect to it using its own driver.
     *
     * @exception ClassNotFoundException if an error occurs
     * @exception SQLException if an error occurs
     */
    public void testHSQLDBServerStart () 
	throws ClassNotFoundException, SQLException {
        Server hsqlServer = null;
	Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/database", "sa", "");
    }

    /**
     * <code>testInterceptingDriverAcceptsURL</code>
     * Verify that the acceptsURL method on the driver
     * returns true for a five-part URL.
     *
     * @exception SQLException if an error occurs
     */
    public void testInterceptingDriverAcceptsRealisticURL () 
    	throws SQLException {
    	Driver driver = new IdentityInterceptingDriver();
    	assertTrue(driver.acceptsURL("jdbc:identity-intercepting:hsqldb:hsql://localhost/database"));
    }

    /**
     * Describe <code>testIdentityInterceptingDriverGetConnection</code> method here.
     *
     * @exception ClassNotFoundException if an error occurs
     * @exception SQLException if an error occurs
     */
    public void testIdentityInterceptingDriverGetConnection ()
    	throws ClassNotFoundException, SQLException {
    	DriverManager.getConnection("jdbc:identity-intercepting:hsqldb:hsql://localhost/database", "sa", "");
    }
    /**
     * Describe <code>testNullInterceptingDriverGetConnection</code> method here.
     *
     * @exception ClassNotFoundException if an error occurs
     * @exception SQLException if an error occurs
     */
    public void testNullInterceptingDriverGetConnection ()
    	throws ClassNotFoundException, SQLException {
    	DriverManager.getConnection("jdbc:null-intercepting:hsqldb:hsql://localhost/database", "sa", "");
    }

    /**
     * Describe <code>testGenericInterceptingDriverGetConnection</code> method here.
     *
     * @exception ClassNotFoundException if an error occurs
     * @exception SQLException if an error occurs
     */
    public void testGenericInterceptingDriverGetConnection ()
    	throws ClassNotFoundException, SQLException {
	GenericInterceptingDriver.addHook(new HookFunction<String>() {
		public String[] eval (String[] args) {
		    return args;
		}
	    });
	DriverManager.getConnection("jdbc:generic-intercepting:hsqldb:hsql://localhost/database", "sa", "");
    }

    public void testGenericInterceptingDriverCreateStatement ()
	throws SQLException {
	GenericInterceptingDriver.addHook(new HookFunction<String>() {
		public String[] eval (String[] args) {
		    return args;
		}
	    });
	Connection connection = DriverManager.getConnection("jdbc:generic-intercepting:hsqldb:hsql://localhost/database", "sa", "");
	connection.createStatement();
    }

    public void testGenericInterceptingDriverAddCollectingHandler ()
	throws SQLException {
	GenericInterceptingDriver.addHook(new CollectingHandler());
	Connection connection = DriverManager.getConnection("jdbc:generic-intercepting:hsqldb:hsql://localhost/database", "sa", "");
	connection.createStatement();
    }

    public void testGenericInterceptingDriverAddCollectingHandlerAndTryToCollect () {
	try {
	    CollectingHandler collector = new CollectingHandler();
	    DevNullHandler sink = new DevNullHandler();
	    GenericInterceptingDriver.addHook(collector);
	    // GenericInterceptingDriver.addHook(sink);
	    Connection connection = DriverManager.getConnection("jdbc:generic-intercepting:hsqldb:hsql://localhost/database", "sa", "");
	    Statement statement = connection.createStatement();
	    statement.execute("create table person (name varchar(30))");
	    for (String sql : collector.getSQLStatements()) System.out.println(sql);
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
    }
}