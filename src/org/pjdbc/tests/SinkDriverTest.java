package org.pjdbc.tests;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.pjdbc.drivers.SinkDriver;
import org.pjdbc.tests.MockDriver;
import org.pjdbc.util.AutoMockTest;

public class SinkDriverTest extends AutoMockTest {
    public static void main (String[] args) {autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.SinkDriver");
	Class.forName("org.pjdbc.tests.MockDriver");}

    public void testVersionInfo () {new Script () {public void run () throws Exception {
	assertEquals(1, new SinkDriver().getMajorVersion());
	assertEquals(0, new SinkDriver().getMinorVersion());
    }};}

    public void testJDBCCompliance () {	new Script () {public void run () throws Exception {
	assertFalse(new SinkDriver().jdbcCompliant());
    }};}

    public void testAcceptsURL () {new Script () {public void run () throws Exception {
	assertFalse(new SinkDriver().acceptsURL("jdbc:sink"));
	assertFalse(new SinkDriver().acceptsURL("jdbc:sink:"));
	assertFalse(new SinkDriver().acceptsURL("jdbc:sink:foo"));
	assertTrue(new SinkDriver().acceptsURL("jdbc:sink:jdbc:mock:foo"));
    }};}

    public void testConnectDirectly () {new Script () {public void run () throws Exception {
	assertFalse(new SinkDriver().acceptsURL("foo"));
	assertNull(new SinkDriver().connect("foo", null));
	assertNull(new SinkDriver().connect("jdbc:sink", null));
	assertNull(new SinkDriver().connect("jdbc:sink:", null));
	assertNotNull(new SinkDriver().connect("jdbc:sink:jdbc:mock:foo", null));
    }};}

    public void testConnectIndirectly () {new Script () {public void run () throws Exception {
	assertNotNull(DriverManager.getConnection("jdbc:sink:jdbc:mock:foo", null));
    }};}

    public void testConnectDirectlyAndInvokeMethods () {new Script () {public void run () throws Exception {
	Connection c = (Connection)(new SinkDriver().connect("jdbc:sink:jdbc:mock:foo", null));
	MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
	Statement stmt = c.createStatement();
	stmt.executeQuery("select * from person;");
	stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
	assertNotNull(d.getLog("jdbc:mock:foo"));
	assertEquals("", d.getLog("jdbc:mock:foo"));
    }};}
}
