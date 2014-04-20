// -*- mode: java; -*-

import java.sql.*;
import org.pjdbc.drivers.*;


public assert (boolean assertion) {
    // if (!assertion) System.exit(1);
    print(assertion);
}

public static void setUp () throws Exception {
    getClass("org.pjdbc.drivers.PoolingDriver");
    getClass("org.pjdbc.drivers.MockDriver");}

public static void tearDown () throws Exception {}

public static void testVersionInfo () {
    assert(1==new PoolingDriver().getMajorVersion());
    assert(0==new PoolingDriver().getMinorVersion());
}

public static void testJDBCCompliance () {
    assert(!new PoolingDriver().jdbcCompliant());
}

public static void testAcceptsURL () {
    assert(!new PoolingDriver().acceptsURL("jdbc:pool"));
    assert(!new PoolingDriver().acceptsURL("jdbc:pool:"));
    assert(!new PoolingDriver().acceptsURL("jdbc:pool:foo"));
    assert((new PoolingDriver().acceptsURL("jdbc:pool:jdbc:mock:foo")));
}

public static void testConnectDirectly () throws Exception {
    assert(!new PoolingDriver().acceptsURL("foo"));
    assert(new PoolingDriver().connect("foo", null)==null);
    assert(new PoolingDriver().connect("jdbc:pool", null)==null);
    assert(new PoolingDriver().connect("jdbc:pool:", null)==null);
    assert(new PoolingDriver().connect("jdbc:pool:jdbc:mock:foo", null)!=null);
}

public static void testConnectIndirectly () throws Exception {
    assert(DriverManager.getConnection("jdbc:pool:jdbc:mock:foo")!=null);
}

public static void testConnectDirectlyAndInvokeMethods () throws Exception {
    Connection c = (Connection)(new PoolingDriver().connect("jdbc:pool:jdbc:mock:foo", null));
    MockDriver d = (MockDriver)DriverManager.getDriver("jdbc:mock:foo");
    Statement stmt = c.createStatement();
    stmt.executeQuery("select * from person;");
    stmt.executeQuery("insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);");
    assert(d.getLog("jdbc:mock:foo")!=null);
    assert("executeQuery[select * from perso;]\n"+
	    "executeQuery[insert into person (last_name, first_name, age) values ('David', 'Ventimiglia', 42);]"==d.getLog("jdbc:mock:foo"));
}

public static void testCallMethodOnClosedPooledConnectionThrowsSQLException () throws Exception {
    Connection foo = DriverManager.getConnection("jdbc:pool:jdbc:mock:foo");
    foo.close();
    try {foo.createStatement();} catch (SQLException e) {return;}
    assert(false);
}

public static void testGetConnectionTwiceProducesTwoDistinctConnections () throws Exception {
    Connection foo1 = DriverManager.getConnection("jdbc:pool:jdbc:mock:foo");
    Connection foo2 = DriverManager.getConnection("jdbc:pool:jdbc:mock:foo");
    assert(foo1!=null);
    assert(foo2!=null);
    assert(foo1!=foo2);
    foo1.close();
    // try {foo2.createStatement();} catch (SQLException e) {fail();}
    foo1.createStatement();
    System.out.println("But No exception was thrown.");
    // try {foo1.createStatement();} catch (SQLException e) {return;}
    // fail();
}

setUp();
// testVersionInfo();
// testJDBCCompliance();
// testAcceptsURL();
// testConnectDirectly();
// testConnectIndirectly();
// testConnectDirectlyAndInvokeMethods();
// testCallMethodOnClosedPooledConnectionThrowsSQLException();
// testGetConnectionTwiceProducesTwoDistinctConnections();
// tearDown();

