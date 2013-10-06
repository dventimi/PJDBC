package org.pjdbc.test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.pjdbc.drivers.BasicDriver;

public class BasicDriverTest extends AutoMockTest {
    private static String SUBPROTOCOL = "mock";

    public static void main (String[] args) {
	autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.BasicDriver");
	DriverManager.registerDriver(getMockDriver(SUBPROTOCOL));}

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
	    assertEquals(SUBPROTOCOL, new BasicDriver().connect("jdbc:basic:jdbc:mock:foo", null).getCatalog());
	}};}

    public void testConnectIndirectly () {
	new Script () {public void run () throws Exception {
	    assertNotNull(DriverManager.getConnection("jdbc:basic:", null));
	    assertNotNull(DriverManager.getConnection("jdbc:basic:jdbc:mock:foo", null));
	    assertEquals(SUBPROTOCOL, DriverManager.getConnection("jdbc:basic:jdbc:mock:foo", null).getCatalog());
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
}
