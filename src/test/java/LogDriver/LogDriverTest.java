import java.io.*;
import java.sql.*;
import java.util.logging.*;
import org.junit.*;
import org.pjdbc.drivers.*;
import static org.junit.Assert.*;

public class LogDriverTest {
    @Test
    public void acceptsURL () {
	assertFalse(new LogDriver().acceptsURL("jdbc:log"));
	assertFalse(new LogDriver().acceptsURL("jdbc:log:"));
	assertFalse(new LogDriver().acceptsURL("jdbc:log:foo"));
	assertTrue(new LogDriver().acceptsURL("jdbc:log:jdbc:mock:foo"));}

    @Test
    public void testConnectDirectlyAndInvokeMethods () {
	try {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    Logger.getLogger("jdbc:mock:foo").setLevel(Level.INFO);
	    Logger.getLogger("jdbc:mock:foo").setUseParentHandlers(false);
	    Logger.getLogger("jdbc:mock:foo").addHandler(new StreamHandler(out, new SimpleFormatter()));
	    Connection c = (Connection)(new LogDriver().connect("jdbc:log:jdbc:mock:foo", null));
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
			 out.toString().trim());}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void testConnectDirectly () {
	try {
	    assertFalse(new LogDriver().acceptsURL("foo"));
	    assertNull(new LogDriver().connect("foo", null));
	    assertNull(new LogDriver().connect("jdbc:log", null));
	    assertNull(new LogDriver().connect("jdbc:log:", null));
	    assertNotNull(new LogDriver().connect("jdbc:log:jdbc:mock:foo", null));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void connectIndirectly () {
	try {assertNotNull(DriverManager.getConnection("jdbc:log:jdbc:mock:foo"));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void compliance () {
	assertFalse(new LogDriver().jdbcCompliant());}

    @Test
    public void versionInfo () {
	assertEquals(1, new LogDriver().getMajorVersion());
	assertEquals(0, new LogDriver().getMinorVersion());}}
