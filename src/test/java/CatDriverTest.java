import java.sql.*;
import org.junit.*;
import org.pjdbc.drivers.*;
import static org.junit.Assert.*;

public class CatDriverTest {
    @Test
    public void acceptsURL () {
	assertFalse(new CatDriver().acceptsURL("jdbc:cat"));
	assertFalse(new CatDriver().acceptsURL("jdbc:cat:"));
	assertFalse(new CatDriver().acceptsURL("jdbc:cat:foo"));
	assertTrue(new CatDriver().acceptsURL("jdbc:cat:jdbc:mock:foo"));}

    @Test
    public void connectDirectlyAndInvokeMethods () {
	try {
	    Connection c = (Connection)(new CatDriver().connect("jdbc:cat:jdbc:mock:foo", null));
	    MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
	    Statement stmt = c.createStatement();
	    stmt.executeQuery("select * from person;");
	    stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
	    assertNotNull(d.getLog("jdbc:mock:foo"));
	    assertEquals("executeQuery[select * from person;]\n"+
			 "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]",
			 d.getLog("jdbc:mock:foo"));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void testConnectDirectly () {
	try {
	    assertFalse(new CatDriver().acceptsURL("foo"));
	    assertNull(new CatDriver().connect("foo", null));
	    assertNull(new CatDriver().connect("jdbc:cat", null));
	    assertNull(new CatDriver().connect("jdbc:cat:", null));
	    assertNotNull(new CatDriver().connect("jdbc:cat:jdbc:mock:foo", null));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void connectIndirectly () {
	try {assertNotNull(DriverManager.getConnection("jdbc:cat:jdbc:mock:foo"));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void compliance () {
	assertFalse(new CatDriver().jdbcCompliant());}

    @Test
    public void versionInfo () {
	assertEquals(1, new CatDriver().getMajorVersion());
	assertEquals(0, new CatDriver().getMinorVersion());}}
