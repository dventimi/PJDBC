package org.pjdbc.tests;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.pjdbc.drivers.FilteringDriver;
import org.pjdbc.drivers.MockDriver;
import org.pjdbc.util.AutoTest;

public class FilteringDriverTest extends AutoTest {
    public static void main (String[] args) {autorun(new Exception());}

    public static class UpcaseFilter extends FilteringDriver.AbstractFilter {
	public String apply (String sql) {return sql==null ? sql : sql.toUpperCase();}}

    public void setUp () throws ClassNotFoundException, SQLException {
	System.setProperty("org.pjdbc.drivers.FilteringDriver.Filter", "org.pjdbc.tests.FilteringDriverTest$UpcaseFilter");
	Class.forName("org.pjdbc.drivers.FilteringDriver");
	Class.forName("org.pjdbc.drivers.MockDriver");}

    public void testVersionInfo () {new Script () {public void run () throws Exception {
	assertEquals(1, new FilteringDriver().getMajorVersion());
	assertEquals(0, new FilteringDriver().getMinorVersion());
    }};}

    public void testJDBCCompliance () {	new Script () {public void run () throws Exception {
	assertFalse(new FilteringDriver().jdbcCompliant());
    }};}

    public void testAcceptsURL () {new Script () {public void run () throws Exception {
	assertFalse(new FilteringDriver().acceptsURL("jdbc:filtering"));
	assertFalse(new FilteringDriver().acceptsURL("jdbc:filter:"));
	assertFalse(new FilteringDriver().acceptsURL("jdbc:filter:foo"));
	assertTrue(new FilteringDriver().acceptsURL("jdbc:filter:jdbc:mock:foo"));
    }};}

    public void testConnectDirectly () {new Script () {public void run () throws Exception {
	assertFalse(new FilteringDriver().acceptsURL("foo"));
	assertNull(new FilteringDriver().connect("foo", null));
	assertNull(new FilteringDriver().connect("jdbc:filtering", null));
	assertNull(new FilteringDriver().connect("jdbc:filter:", null));
	assertNotNull(new FilteringDriver().connect("jdbc:filter:jdbc:mock:foo", null));
    }};}

    public void testConnectIndirectly () {new Script () {public void run () throws Exception {
	assertNotNull(DriverManager.getConnection("jdbc:filter:jdbc:mock:foo", null));
    }};}

    public void testConnectDirectlyAndInvokeMethods () {new Script () {public void run () throws Exception {
	Connection c = (Connection)(new FilteringDriver().connect("jdbc:filter:jdbc:mock:foo", null));
	MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
	Statement stmt = c.createStatement();
	String query = "select * from person;";
	stmt.executeQuery(query);
	assertNotNull(d.getLog("jdbc:mock:foo"));
	assertEquals("executeQuery[SELECT * FROM PERSON;]", d.getLog("jdbc:mock:foo"));
    }};}
}
