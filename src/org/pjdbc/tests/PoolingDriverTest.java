package org.pjdbc.tests;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.pjdbc.drivers.MockDriver;
import org.pjdbc.drivers.PoolingDriver;
import org.pjdbc.util.AutoTest;

public class PoolingDriverTest extends AutoTest {
    public static void main (String[] args) {autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.PoolingDriver");
	Class.forName("org.pjdbc.drivers.MockDriver");}

    public void testVersionInfo () {new Script () {public void run () throws Exception {
    	assertEquals(1, new PoolingDriver().getMajorVersion());
    	assertEquals(0, new PoolingDriver().getMinorVersion());
    }};}

    public void testJDBCCompliance () {	new Script () {public void run () throws Exception {
    	assertFalse(new PoolingDriver().jdbcCompliant());
    }};}

    public void testAcceptsURL () {new Script () {public void run () throws Exception {
    	assertFalse(new PoolingDriver().acceptsURL("jdbc:pool"));
    	assertFalse(new PoolingDriver().acceptsURL("jdbc:pool:"));
    	assertFalse(new PoolingDriver().acceptsURL("jdbc:pool:foo"));
    	assertTrue(new PoolingDriver().acceptsURL("jdbc:pool:jdbc:mock:foo"));
    }};}

    public void testConnectDirectly () {new Script () {public void run () throws Exception {
    	assertFalse(new PoolingDriver().acceptsURL("foo"));
    	assertNull(new PoolingDriver().connect("foo", null));
    	assertNull(new PoolingDriver().connect("jdbc:pool", null));
    	assertNull(new PoolingDriver().connect("jdbc:pool:", null));
    	assertNotNull(new PoolingDriver().connect("jdbc:pool:jdbc:mock:foo", null));
    }};}

    public void testConnectIndirectly () {new Script () {public void run () throws Exception {
    	assertNotNull(DriverManager.getConnection("jdbc:pool:jdbc:mock:foo"));
    }};}

    public void testConnectDirectlyAndInvokeMethods () {new Script () {public void run () throws Exception {
    	Connection c = (Connection)(new PoolingDriver().connect("jdbc:pool:jdbc:mock:foo", null));
    	MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
    	Statement stmt = c.createStatement();
    	stmt.executeQuery("select * from person;");
    	stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
    	assertNotNull(d.getLog("jdbc:mock:foo"));
    	assertEquals("executeQuery[select * from person;]\n"+
    		     "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]", 
    		     d.getLog("jdbc:mock:foo"));
    }};}

    public void testCallMethodOnClosedPooledConnectionThrowsSQLException () {new Script () {public void run () throws Exception {
    	Connection foo = DriverManager.getConnection("jdbc:pool:jdbc:mock:foo");
	foo.close();
	try {foo.createStatement();} catch (SQLException e) {return;}
	fail();
    }};}
}
