package org.pjdbc.tests;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.pjdbc.drivers.CatDriver;
import org.pjdbc.tests.MockDriver;
import org.pjdbc.util.AutoMockTest;

public class CatDriverTest extends AutoMockTest {
    public static void main (String[] args) {autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.CatDriver");
	Class.forName("org.pjdbc.tests.MockDriver");}

    public void testVersionInfo () {new Script () {public void run () throws Exception {
	assertEquals(1, new CatDriver().getMajorVersion());
	assertEquals(0, new CatDriver().getMinorVersion());
    }};}

    public void testJDBCCompliance () {	new Script () {public void run () throws Exception {
	assertFalse(new CatDriver().jdbcCompliant());
    }};}

    public void testAcceptsURL () {new Script () {public void run () throws Exception {
	assertFalse(new CatDriver().acceptsURL("jdbc:cat"));
	assertFalse(new CatDriver().acceptsURL("jdbc:cat:"));
	assertFalse(new CatDriver().acceptsURL("jdbc:cat:foo"));
	assertTrue(new CatDriver().acceptsURL("jdbc:cat:jdbc:mock:foo"));
    }};}

    public void testConnectDirectly () {new Script () {public void run () throws Exception {
	assertFalse(new CatDriver().acceptsURL("foo"));
	assertNull(new CatDriver().connect("foo", null));
	assertNull(new CatDriver().connect("jdbc:cat", null));
	assertNull(new CatDriver().connect("jdbc:cat:", null));
	assertNotNull(new CatDriver().connect("jdbc:cat:jdbc:mock:foo", null));
    }};}

    public void testConnectIndirectly () {new Script () {public void run () throws Exception {
	assertNotNull(DriverManager.getConnection("jdbc:cat:jdbc:mock:foo", null));
    }};}

    public void testConnectDirectlyAndInvokeMethods () {new Script () {public void run () throws Exception {
	Connection c = (Connection)(new CatDriver().connect("jdbc:cat:jdbc:mock:foo", null));
	MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
	Statement stmt = c.createStatement();
	stmt.executeQuery("select * from person;");
	stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
	assertNotNull(d.getLog("jdbc:mock:foo"));
	assertEquals("executeQuery[select * from person;]\n"+
		     "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]", 
		     d.getLog("jdbc:mock:foo"));
    }};}
}
