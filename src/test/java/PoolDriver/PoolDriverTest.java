import java.sql.*;
import org.junit.*;
import org.pjdbc.drivers.*;
import static org.junit.Assert.*;

public class PoolDriverTest {
    @Test
    public void acceptsURL () {
	assert(!new PoolDriver().acceptsURL("jdbc:pool"));
	assert(!new PoolDriver().acceptsURL("jdbc:pool:"));
	assert(!new PoolDriver().acceptsURL("jdbc:pool:foo"));
	assert((new PoolDriver().acceptsURL("jdbc:pool:jdbc:mock:foo")));}

    @Test
    public void callMethodOnClosedPooledConnectionThrowsSQLException () {
	try {
	    Connection foo = DriverManager.getConnection("jdbc:pool:jdbc:mock:foo");
	    foo.close();
	    try {foo.createStatement();} catch (SQLException e) {return;}
	    assert(false);}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void connectDirectlyAndInvokeMethods () {
	try {
	    Connection c = (Connection)(new PoolDriver().connect("jdbc:pool:jdbc:mock:foo", null));
	    MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
	    Statement stmt = c.createStatement();
	    stmt.executeQuery("select * from person;");
	    stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
	    assert(d.getLog("jdbc:mock:foo")!=null);
	    assertEquals("executeQuery[select * from person;]\n" +
			 "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]", d.getLog("jdbc:mock:foo"));}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void connectDirectly () {
	try {
	    assert(!new PoolDriver().acceptsURL("foo"));
	    assert(new PoolDriver().connect("foo", null)==null);
	    assert(new PoolDriver().connect("jdbc:pool", null)==null);
	    assert(new PoolDriver().connect("jdbc:pool:", null)==null);
	    assert(new PoolDriver().connect("jdbc:pool:jdbc:mock:foo", null)!=null);}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void connectIndirectly () {
	try {assert(DriverManager.getConnection("jdbc:pool:jdbc:mock:foo")!=null);}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void getConnectionTwiceProducesTwoDistinctConnections () {
	try {
	    Connection foo1 = DriverManager.getConnection("jdbc:pool:jdbc:mock:foo");
	    Connection foo2 = DriverManager.getConnection("jdbc:pool:jdbc:mock:foo");
	    assert(foo1!=null);
	    assert(foo2!=null);
	    assert(foo1!=foo2);}
	catch (Exception e) {fail(e.getMessage());}}

    @Test
    public void compliance () {
	assert(!new PoolDriver().jdbcCompliant());}

    @Test
    public void versionInfo () {
	assert(1==new PoolDriver().getMajorVersion());
	assert(0==new PoolDriver().getMinorVersion());}}
