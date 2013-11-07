package org.pjdbc.tests;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.pjdbc.drivers.TeeDriver;
import org.pjdbc.drivers.MockDriver;
import org.pjdbc.util.AutoTest;

public class TeeDriverTest extends AutoTest {
    public static void main (String[] args) {autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.TeeDriver");
	Class.forName("org.pjdbc.drivers.MockDriver");}

    public void testVersionInfo () {new Script () {public void run () throws Exception {
	assertEquals(1, new TeeDriver().getMajorVersion());
	assertEquals(0, new TeeDriver().getMinorVersion());
    }};}

    public void testJDBCCompliance () {	new Script () {public void run () throws Exception {
	assertFalse(new TeeDriver().jdbcCompliant());
    }};}

    public void testAcceptsURL () {new Script () {public void run () throws Exception {
	assertFalse(new TeeDriver().acceptsURL("jdbc:tee"));
	assertFalse(new TeeDriver().acceptsURL("jdbc:tee:"));
	assertFalse(new TeeDriver().acceptsURL("jdbc:tee:foo"));
	assertTrue(new TeeDriver().acceptsURL("jdbc:tee:jdbc:mock:foo;jdbc:mock:foo"));
    }};}

    public void testConnectDirectly () {new Script () {public void run () throws Exception {
	assertFalse(new TeeDriver().acceptsURL("foo"));
	assertNull(new TeeDriver().connect("foo", null));
	assertNull(new TeeDriver().connect("jdbc:tee", null));
	assertNull(new TeeDriver().connect("jdbc:tee:", null));
	assertNotNull(new TeeDriver().connect("jdbc:tee:jdbc:mock:foo;jdbc:mock:foo", null));
    }};}

    public void testConnectIndirectlyWithBadURLFails () {new Script () {public void run () throws Exception {
	try{DriverManager.getConnection("jdbc:tee:jdbc:mock:foo", null);} catch (SQLException e) {return;}
	fail("jdbc:tee:jdbc:mock:foo should have no registered driver!");
    }};}

    public void testConnectIndirectlyWithGoodURLSucceeds () {new Script () {public void run () throws Exception {
    	try{DriverManager.getConnection("jdbc:tee:jdbc:mock:foo;jdbc:mock:foo", null);} catch (SQLException e) {fail(e.toString());}
    }};}

    public void testConnectDirectlyAndInvokeMethods () {new Script () {public void run () throws Exception {
    	Connection c = (Connection)(new TeeDriver().connect("jdbc:tee:jdbc:mock:foo;jdbc:mock:bar", null));
    	MockDriver foo = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
    	MockDriver bar = (MockDriver)DriverManager.getDriver("jdbc:mock:bar");
	assertSame(foo, bar);
    	Statement stmt = c.createStatement();
    	stmt.executeQuery("select * from person;");
    	stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
    	assertNotNull(foo.getLog("jdbc:mock:foo"));
    	assertEquals("executeQuery[select * from person;]\n"+
    		     "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]", 
    		     foo.getLog("jdbc:mock:foo"));
    	assertNotNull(bar.getLog("jdbc:mock:bar"));
    	assertEquals("executeQuery[select * from person;]\n"+
    		     "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]", 
    		     foo.getLog("jdbc:mock:bar"));
    }};}
}
