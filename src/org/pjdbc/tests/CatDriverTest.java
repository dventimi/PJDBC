package org.pjdbc.test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.pjdbc.drivers.CatDriver;
import org.pjdbc.util.AutoMockTest;

public class CatDriverTest extends AutoMockTest {
    private static String SUBPROTOCOL = "mock";

    public static void main (String[] args) {
	autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.CatDriver");
	DriverManager.registerDriver(getMockDriver(SUBPROTOCOL));}

    public void testVersionInfo () {
	new Script () {public void run () throws Exception {
	    assertEquals(1, new CatDriver().getMajorVersion());
	    assertEquals(0, new CatDriver().getMinorVersion());
	}};}

    public void testJDBCCompliance () {
	new Script () {public void run () throws Exception {
	    assertFalse(new CatDriver().jdbcCompliant());
	}};}

    public void testAcceptsSubProtocol () {
	new Script () {public void run () throws Exception {
	    // assertTrue(new CatDriver().acceptsSubProtocol("basic"));
	    // assertFalse(new CatDriver().acceptsSubProtocol("foo"));
	}};}

    public void testAcceptsURL () {
	new Script () {public void run () throws Exception {
	    assertTrue(new CatDriver().acceptsURL("jdbc:basic:foo"));
	    assertTrue(new CatDriver().acceptsURL("jdbc:basic:"));
	    assertFalse(new CatDriver().acceptsURL("jdbc:basic"));
	}};}

    public void testConnectDirectly () {
	new Script () {public void run () throws Exception {
	    assertFalse(new CatDriver().acceptsURL("foo"));
	    assertNull(new CatDriver().connect("foo", null));
	    assertNull(new CatDriver().connect("jdbc:basic", null));
	    assertNotNull(new CatDriver().connect("jdbc:basic:", null));
	    assertNotNull(new CatDriver().connect("jdbc:basic:jdbc:mock:foo", null));
	    assertEquals(SUBPROTOCOL, new CatDriver().connect("jdbc:basic:jdbc:mock:foo", null).getCatalog());
	}};}

    public void testConnectIndirectly () {
	new Script () {public void run () throws Exception {
	    assertNotNull(DriverManager.getConnection("jdbc:basic:", null));
	    assertNotNull(DriverManager.getConnection("jdbc:basic:jdbc:mock:foo", null));
	    assertEquals(SUBPROTOCOL, DriverManager.getConnection("jdbc:basic:jdbc:mock:foo", null).getCatalog());
	}};}


    public void testConnectDirectlyAndInvokeMethods () {
	new Script () {public void run () throws Exception {
	    Connection c = (Connection)(new CatDriver().connect("jdbc:basic:jdbc:mock:foo", null));
	    // assertNotNull(c.getLog());
	    // assertTrue(c.getLog().isEmpty());
	    c.createStatement();
	    c.prepareStatement("");
	    // assertFalse(c.getLog().isEmpty());
	    // assertEquals(2, c.getLog().size());
	    // assertEquals("createStatement", c.getLog().get(0));
	    // assertEquals("prepareStatement", c.getLog().get(1));
	}};}
}
