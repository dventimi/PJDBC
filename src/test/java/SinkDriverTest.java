import java.sql.*;
import org.junit.*;
import org.pjdbc.drivers.*;
import static org.junit.Assert.*;

public class SinkDriverTest {
    @Test
    public void acceptsURL () {
	assertFalse(new SinkDriver().acceptsURL("jdbc:sink"));
	assertFalse(new SinkDriver().acceptsURL("jdbc:sink:"));
	assertFalse(new SinkDriver().acceptsURL("jdbc:sink:foo"));
	assertTrue(new SinkDriver().acceptsURL("jdbc:sink:jdbc:mock:foo"));}

    @Test
    public void connectDirectlyAndInvokeMethods () {
	try {
	    Connection c = (Connection)(new SinkDriver().connect("jdbc:sink:jdbc:mock:foo", null));
	    MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
	    Statement stmt = c.createStatement();
	    stmt.executeQuery("select * from person;");
	    stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
	    assertNotNull(d.getLog("jdbc:mock:foo"));
	    assertEquals("", d.getLog("jdbc:mock:foo"));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void connectDirectly () {
	try {
	    assertFalse(new SinkDriver().acceptsURL("foo"));
	    assertNull(new SinkDriver().connect("foo", null));
	    assertNull(new SinkDriver().connect("jdbc:sink", null));
	    assertNull(new SinkDriver().connect("jdbc:sink:", null));
	    assertNotNull(new SinkDriver().connect("jdbc:sink:jdbc:mock:foo", null));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void connectIndirectly () {
	try {assertNotNull(DriverManager.getConnection("jdbc:sink:jdbc:mock:foo"));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void compliance () {
	assertFalse(new SinkDriver().jdbcCompliant());}

    @Test
    public void versionInfo () {
	assertEquals(1, new SinkDriver().getMajorVersion());
	assertEquals(0, new SinkDriver().getMinorVersion());}}
