package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runners.Suite;

/**
 * Describe class <code>InterceptingDriverTest</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public class InterceptingDriverTest 
    extends AutoTest {

    // ----------------- Helper Classes ---------------------------------

    /**
     * Describe class <code>CollectingHandler</code> here.
     *
     */
    public static class CollectingHandler implements HookFunction<String> {
	private List<String> sqlStatements = new ArrayList<String>();
	/**
	 * Describe <code>eval</code> method here.
	 *
	 * @param args a <code>String</code> value
	 * @return a <code>String[]</code> value
	 */
	public String[] eval (String[] args) {
	    this.sqlStatements.add(args[0]);
	    return args;
	}
	/**
	 * Describe <code>getSQLStatements</code> method here.
	 *
	 * @return a <code>List<String></code> value
	 */
	public List<String> getSQLStatements () {
	    return this.sqlStatements;
	}
    }

    /**
     * Describe class <code>DevNullHandler</code> here.
     *
     */
    public static class DevNullHandler implements HookFunction<String> {
	/**
	 * Describe <code>eval</code> method here.
	 *
	 * @param args a <code>String</code> value
	 * @return a <code>String[]</code> value
	 */
	public String[] eval (String[] args) {
	    return new String[]{""};
	}
    }

    // ----------------- Static Members ---------------------------------

    /**
     * Describe variable <code>DB</code> here.
     *
     */
    public static String DB = "derby:memory:testdb";
    /**
     * Describe variable <code>CREATE_DB</code> here.
     *
     */
    public static String CREATE_DB = "jdbc:" + DB + ";create=true";
    /**
     * Describe variable <code>REMOVE_DB</code> here.
     *
     */
    public static String REMOVE_DB = "jdbc:" + DB + ";drop=true";
    
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
     * so go ahead and register the drivers.
     *
     * @exception ClassNotFoundException if an error occurs
     */
    public void setUp ()
	throws ClassNotFoundException {
	Class.forName("com.omnicorps.global.pjdbc.IdentityInterceptingDriver");
	Class.forName("com.omnicorps.global.pjdbc.NullInterceptingDriver");
	Class.forName("com.omnicorps.global.pjdbc.ProxyDriver");
	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	try {DriverManager.getConnection(CREATE_DB);} catch (Throwable t) {}}

    /**
     * Describe <code>tearDown</code> method here.
     *
     */
    public void tearDown () {
	try {DriverManager.getConnection(REMOVE_DB);} catch (Throwable t) {}}

    // --------------------- Tests ----------------------------------
    
    /**
     * <code>testJDBCURLParsesThreePartURLs</code>
     * JDBCURL is a helper class.  Verify that it accepts a JDBC URL
     * that has the protocol, subprotocol, and subname, separated by
     * colons.
     */
    public void testJDBCURLParsesThreePartURLs () {
	new Script () {
	    public void run () throws Exception {
		String [] parsedURL = JDBCURL.parseURL("jdbc:intercepting:jdbc");
		assertNotNull(parsedURL);
		assertEquals(3, parsedURL.length);
		assertEquals("jdbc", parsedURL[0]);
		assertEquals("intercepting", parsedURL[1]);
		assertEquals("jdbc", parsedURL[2]);}};}

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
	new Script () {
	    public void run () throws Exception {
		String [] parsedURL = JDBCURL.parseURL("jdbc:intercepting:jdbc:subprotocol:subname");
		assertNotNull(parsedURL);
		assertEquals(3, parsedURL.length);
		assertEquals("jdbc", parsedURL[0]);
		assertEquals("intercepting", parsedURL[1]);
		assertEquals("jdbc:subprotocol:subname", parsedURL[2]);}};}

    /**
     * <code>testJDBCURLRefusesFewerThanThreePartURLs</code>
     * Verify that JDBCURL throws a SQLException if 
     * a three-part (protocol, subprotocol, subname) URL 
     * is not provided.
     *
     */
    public void testJDBCURLRefusesFewerThanThreePartURLs () {
	new Script () {
	    public void run () throws Exception {
		try {
		    new JDBCURL("jdbc:intercepting");}
		catch (SQLException e) {
		    return;}
		fail("URL should have been refused!!");}};}

    /**
     * <code>testJDBDURLAcceptsThreePartURLs</code>
     * Verify that a three-part URL can be passed into
     * the JDBCURL constructor.
     *
     * @exception SQLException if an error occurs
     */
    public void testJDBDURLAcceptsThreePartURLs () {
	new Script () {
	    public void run () throws Exception {
		JDBCURL url = new JDBCURL("jdbc:intercepting:protocol");
		assertEquals("jdbc", url.getProtocol());
		assertEquals("intercepting", url.getSubprotocol());
		assertEquals("protocol", url.getSubname());}};}
    
    /**
     * <code>testJDBDURLAcceptsMoreThanThreePartURLs</code>
     * Verify that JDBCURL can be passed a URL whose
     * subname is itself a JDBC URL.  Note that it should
     * not parse the subnname itself into separate pieces.
     *
     * @exception SQLException if an error occurs
     */
    public void testJDBDURLAcceptsMoreThanThreePartURLs () {
	new Script () {
	    public void run () throws Exception {
		JDBCURL url = new JDBCURL("jdbc:intercepting:jdbc:subprotocol:subname:extra");
		assertEquals("jdbc", url.getProtocol());
		assertEquals("intercepting", url.getSubprotocol());
		assertEquals("jdbc:subprotocol:subname:extra", url.getSubname());}};}

    /**
     * <code>testInterceptingDriverAcceptsURL</code>
     * Verify that the acceptsURL method on the driver
     * returns true for a five-part URL.
     *
     * @exception SQLException if an error occurs
     */
    public void testInterceptingDriverAcceptsURL () {
	new Script () {
	    public void run () throws Exception {
		Driver driver = new IdentityInterceptingDriver();
		assertTrue(driver.acceptsURL("jdbc:identity-intercepting:jdbc:subprotocol:subname"));}};}

    /**
     * <code>testInterceptingDriverAcceptsURL</code>
     * Verify that the acceptsURL method on the driver
     * returns true for a five-part URL.
     *
     * @exception SQLException if an error occurs
     */
    public void testInterceptingDriverAcceptsRealisticURL () {
	new Script () {
	    public void run () throws Exception {
		Driver driver = new IdentityInterceptingDriver();
		assertTrue(driver.acceptsURL("jdbc:identity-intercepting:" + DB));}};}

    /**
     * Describe <code>testIdentityInterceptingDriverGetConnection</code> method here.
     *
     * @exception ClassNotFoundException if an error occurs
     * @exception SQLException if an error occurs
     */
    public void testIdentityInterceptingDriverGetConnection () {
    	new Script () {
    	    public void run () throws Exception {
    		DriverManager.getConnection("jdbc:identity-intercepting:" + DB);}};}

    /**
     * Describe <code>testNullInterceptingDriverGetConnection</code> method here.
     *
     * @exception ClassNotFoundException if an error occurs
     * @exception SQLException if an error occurs
     */
    public void testNullInterceptingDriverGetConnection () {
	new Script () {
	    public void run () throws Exception {
		DriverManager.getConnection("jdbc:null-intercepting:" + DB);}};}

    /**
     * Describe <code>testProxyDriverGetConnection</code> method here.
     *
     */
    public void testProxyDriverGetConnection ()	{
    	new Script () {
    	    public void run () throws Exception {
    		DriverManager.getConnection("jdbc:pjdbc:" + DB);}};}

    /**
     * Describe <code>testProxyDriverCreateStatement</code> method here.
     *
     */
    public void testProxyDriverCreateStatement () {
    	new Script () {
    	    public void run () throws Exception {
    		DriverManager.getConnection("jdbc:pjdbc:" + DB)
    		    .createStatement();}};}

    /**
     * Describe <code>testProxyDriverWithNoHandlersExecutesPassedInQuery</code> method here.
     *
     */
    public void testProxyDriverWithNoHandlersExecutesPassedInQuery () {
    	new Script () {
    	    public void run () throws Exception {
    		DriverManager.getConnection("jdbc:pjdbc:" + DB)
    		    .createStatement()
    		    .execute("create table person (name varchar(30))");}};}

    /**
     * Describe <code>testProxyDriverAddCollectingHandlerAndTryToCollect</code> method here.
     *
     */
    public void testProxyDriverAddCollectingHandlerAndTryToCollect () {
    	new Script () {
    	    public void run () throws Exception {
    		// CollectingHandler collector = new CollectingHandler();
    		// DevNullHandler sink = new DevNullHandler();
    		// ProxyDriver.addHook(collector);
    		// ProxyDriver.addHook(sink);
    		DriverManager.getConnection("jdbc:pjdbc:" + DB)
    		    .createStatement()
    		    .execute("create table person (name varchar(30))");}};}
    		// for (String sql : collector.getSQLStatements()) System.out.println(sql);}};}

}