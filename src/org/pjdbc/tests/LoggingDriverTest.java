package org.pjdbc.tests;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import org.pjdbc.drivers.LoggingDriver;
import org.pjdbc.drivers.MockDriver;
import org.pjdbc.util.AutoTest;

public class LoggingDriverTest extends AutoTest {
    public static void main (String[] args) {autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.LoggingDriver");
	Class.forName("org.pjdbc.drivers.MockDriver");}

    public void testVersionInfo () {new Script () {public void run () throws Exception {
	assertEquals(1, new LoggingDriver().getMajorVersion());
	assertEquals(0, new LoggingDriver().getMinorVersion());
    }};}

    public void testJDBCCompliance () {	new Script () {public void run () throws Exception {
	assertFalse(new LoggingDriver().jdbcCompliant());
    }};}

    public void testAcceptsURL () {new Script () {public void run () throws Exception {
	assertFalse(new LoggingDriver().acceptsURL("jdbc:log"));
	assertFalse(new LoggingDriver().acceptsURL("jdbc:log:"));
	assertFalse(new LoggingDriver().acceptsURL("jdbc:log:foo"));
	assertTrue(new LoggingDriver().acceptsURL("jdbc:log:jdbc:mock:foo"));
    }};}

    public void testConnectDirectly () {new Script () {public void run () throws Exception {
	assertFalse(new LoggingDriver().acceptsURL("foo"));
	assertNull(new LoggingDriver().connect("foo", null));
	assertNull(new LoggingDriver().connect("jdbc:log", null));
	assertNull(new LoggingDriver().connect("jdbc:log:", null));
	assertNotNull(new LoggingDriver().connect("jdbc:log:jdbc:mock:foo", null));
    }};}

    public void testConnectIndirectly () {new Script () {public void run () throws Exception {
	assertNotNull(DriverManager.getConnection("jdbc:log:jdbc:mock:foo", null));
    }};}

    public void testConnectDirectlyAndInvokeMethods () {new Script () {public void run () throws Exception {
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	Logger.getLogger("jdbc:mock:foo").setLevel(Level.INFO);
	Logger.getLogger("jdbc:mock:foo").setUseParentHandlers(false);
	Logger.getLogger("jdbc:mock:foo").addHandler(new StreamHandler(out, new SimpleFormatter()));
	Connection c = (Connection)(new LoggingDriver().connect("jdbc:log:jdbc:mock:foo", null));
	MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
	Statement stmt = c.createStatement();
	stmt.executeQuery("select * from person;");
	stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
	for (Handler h : Logger.getLogger("jdbc:mock:foo").getHandlers()) h.flush();
	assertNotNull(d.getLog("jdbc:mock:foo"));
	assertEquals("executeQuery[select * from person;]\n" +
		     "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]", 
		     d.getLog("jdbc:mock:foo"));
	assertEquals("select * from person;\n" +
		     "insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);", 
		     out.toString().trim());
    }};}
}
