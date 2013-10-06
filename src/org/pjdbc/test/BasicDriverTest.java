package org.pjdbc.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.pjdbc.drivers.BasicDriver;
import org.pjdbc.lib.AbstractProxyDriver;

public class BasicDriverTest extends AutoTest {
    private static String MOCK_SUBPROTOCOL = "mock";

    public static void main (String[] args) {
	autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.BasicDriver");
	DriverManager.registerDriver(getMockDriver(MOCK_SUBPROTOCOL));}

    public void testVersionInfo () {
	new Script () {public void run () throws Exception {
	    assertEquals(1, new BasicDriver().getMajorVersion());
	    assertEquals(0, new BasicDriver().getMinorVersion());
	}};}

    public void testJDBCCompliance () {
	new Script () {public void run () throws Exception {
	    assertFalse(new BasicDriver().jdbcCompliant());
	}};}

    public void testAcceptsSubProtocol () {
	new Script () {public void run () throws Exception {
	    assertTrue(new BasicDriver().acceptsSubProtocol("basic"));
	    assertFalse(new BasicDriver().acceptsSubProtocol("foo"));
	}};}

    public void testAcceptsURL () {
	new Script () {public void run () throws Exception {
	    assertTrue(new BasicDriver().acceptsURL("jdbc:basic:foo"));
	    assertTrue(new BasicDriver().acceptsURL("jdbc:basic:"));
	    assertFalse(new BasicDriver().acceptsURL("jdbc:basic"));
	}};}

    public void testConnectDirectly () {
	new Script () {public void run () throws Exception {
	    assertFalse(new BasicDriver().acceptsURL("foo"));
	    assertNull(new BasicDriver().connect("foo", null));
	    assertNull(new BasicDriver().connect("jdbc:basic", null));
	    assertNotNull(new BasicDriver().connect("jdbc:basic:", null));
	    assertNotNull(new BasicDriver().connect("jdbc:basic:jdbc:mock:foo", null));
	    assertEquals(MOCK_SUBPROTOCOL, new BasicDriver().connect("jdbc:basic:jdbc:mock:foo", null).getCatalog());
	}};}

    public void testConnectIndirectly () {
	new Script () {public void run () throws Exception {
	    assertNotNull(DriverManager.getConnection("jdbc:basic:", null));
	    assertNotNull(DriverManager.getConnection("jdbc:basic:jdbc:mock:foo", null));
	    assertEquals(MOCK_SUBPROTOCOL, DriverManager.getConnection("jdbc:basic:jdbc:mock:foo", null).getCatalog());
	}};}


    public void testConnectDirectlyAndInvokeMethods () {
	new Script () {public void run () throws Exception {
	    MockConnection c = (MockConnection)(new BasicDriver().connect("jdbc:basic:jdbc:mock:foo", null));
	    assertNotNull(c.getLog());
	    assertTrue(c.getLog().isEmpty());
	    c.createStatement();
	    c.prepareStatement("");
	    assertFalse(c.getLog().isEmpty());
	    assertEquals(2, c.getLog().size());
	    assertEquals("createStatement", c.getLog().get(0));
	    assertEquals("prepareStatement", c.getLog().get(1));
	}};}

    private interface ScratchLog {
	public List getLog ();}

    private interface MockDriver extends Driver, ScratchLog {}

    private interface MockConnection extends Connection, ScratchLog {}

    private MockDriver getMockDriver (final String subprotocol) {
	return (MockDriver)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{MockDriver.class}, new InvocationHandler () {
		private List<String> log = new ArrayList<String>();
		private boolean acceptsURL (String url) {return (subprotocol+"").equals(AbstractProxyDriver.subprotocol(url));}
		private Connection connect (String url) {return getMockConnection(subprotocol);}
		public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
		    if ("getLog".equals(method.getName())) return log;
		    log.add(method.getName());
		    if ("acceptsURL".equals(method.getName())) return acceptsURL(""+args[0]);
		    if ("connect".equals(method.getName())) return connect(""+args[0]);
		    return null;}});}

    private MockConnection getMockConnection (final String catalog) {
	return (MockConnection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{MockConnection.class}, new InvocationHandler () {
		private List<String> log = new ArrayList<String>();
		public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
		    if ("getLog".equals(method.getName())) return log;
		    if ("getCatalog".equals(method.getName())) return catalog;
		    log.add(method.getName());
		    return null;}});}}
