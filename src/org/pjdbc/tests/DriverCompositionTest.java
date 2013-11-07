package org.pjdbc.tests;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.pjdbc.drivers.CatDriver;
import org.pjdbc.drivers.MockDriver;
import org.pjdbc.util.AutoTest;

public class DriverCompositionTest extends AutoTest {
    public static void main (String[] args) {autorun(new Exception());}

    public void setUp () throws ClassNotFoundException, SQLException {
	Class.forName("org.pjdbc.drivers.CatDriver");
	Class.forName("org.pjdbc.drivers.FilteringDriver");
	Class.forName("org.pjdbc.drivers.LoggingDriver");
	Class.forName("org.pjdbc.drivers.MockDriver");
	Class.forName("org.pjdbc.drivers.SinkDriver");
	Class.forName("org.pjdbc.drivers.TeeDriver");}

    public void testProgressiveComposition () {new Script () {public void run () throws Exception {
	assertNotNull(DriverManager.getConnection("jdbc:cat:jdbc:mock:foo"));
	assertNotNull(DriverManager.getConnection("jdbc:cat:jdbc:sink:jdbc:mock:foo"));
	assertNotNull(DriverManager.getConnection("jdbc:log:jdbc:filter:jdbc:cat:jdbc:sink:jdbc:mock:foo"));
	assertNotNull(DriverManager.getConnection("jdbc:log:jdbc:filter:jdbc:cat:jdbc:tee:jdbc:sink:jdbc:mock:foo;jdbc:mock:bar"));
    }};}

    public void testUrlsCanHaveWhitespace () {new Script () {public void run () throws Exception {
	assertNotNull(DriverManager.getConnection("jdbc: cat:jdbc:\nmock:foo"));
	assertNotNull(DriverManager.getConnection("jdbc: cat:jdbc:\nsink:jdbc:mock:foo"));
	assertNotNull(DriverManager.getConnection("jdbc: log:jdbc:\nfilter:jdbc:cat:jdbc:sink:jdbc:mock:foo"));
	assertNotNull(DriverManager.getConnection("jdbc: log:jdbc:\nfilter:jdbc\n:cat :  jdbc:\ntee:jdbc:sink :jdbc:mock:foo ;\njdbc:mock:bar"));
    }};}

    public void testTeePlusSink () {new Script () {public void run () throws Exception {
	Connection c = DriverManager.getConnection("jdbc:tee:jdbc:mock:foo;jdbc:sink:jdbc:mock:bar");
    	MockDriver foo = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
    	MockDriver bar = (MockDriver)DriverManager.getDriver("jdbc:mock:bar");
	assertSame(foo, bar);
    	Statement stmt = c.createStatement();
    	stmt.executeQuery("select * from person;");
    	stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
    	assertNotNull(foo.getLog("jdbc:mock:foo"));
    	assertNotNull(bar.getLog("jdbc:mock:bar"));
    	assertEquals("executeQuery[select * from person;]\n"+
    		     "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]", 
    		     foo.getLog("jdbc:mock:foo"));
    	assertEquals("", bar.getLog("jdbc:mock:bar"));
    }};}

    public void testTeePlusSinkButReverseOrder () {new Script () {public void run () throws Exception {
	Connection c = DriverManager.getConnection("jdbc:tee:jdbc:sink:jdbc:mock:foo;jdbc:mock:bar");
    	MockDriver foo = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
    	MockDriver bar = (MockDriver)DriverManager.getDriver("jdbc:mock:bar");
	assertSame(foo, bar);
    	Statement stmt = c.createStatement();
    	stmt.executeQuery("select * from person;");
    	stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
    	assertNotNull(foo.getLog("jdbc:mock:foo"));
    	assertNotNull(bar.getLog("jdbc:mock:bar"));
    	assertEquals("executeQuery[select * from person;]\n"+
    		     "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]", 
    		     bar.getLog("jdbc:mock:bar"));
    	assertEquals("", foo.getLog("jdbc:mock:foo"));
    }};}
}
