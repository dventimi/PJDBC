package org.radiumsalt; // Generated package name

import org.pjdbc.AutoTest;
import java.sql.Connection;
import java.sql.DriverManager;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

public class ProtoSaltTest extends AutoTest {
    public static String DB = "derby:memory:testdb";
    public static String CREATE_DB = "jdbc:" + DB + ";create=true";
    public static String REMOVE_DB = "jdbc:" + DB + ";drop=true";

    public static void main (String[] args) {autorun(new Exception());}

    public void setUp () throws ClassNotFoundException {
	SaltBin.init();
	Class.forName("org.pjdbc.IdentityDriver");
	Class.forName("org.pjdbc.DevNullDriver");
	Class.forName("org.pjdbc.ProxyDriver");
	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	try {DriverManager.getConnection(CREATE_DB);} catch (Throwable t) {}}

    public void tearDown () {
	try {DriverManager.getConnection(REMOVE_DB);} catch (Throwable t) {}}

    public void testFoundSaltForValidStatement () {
	SaltBin.addSalt(new ProtoSalt());
	assertNotNull(SaltBin.hasSalt("create     domain key on foo.bar references foo.bar with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement1 () {
	SaltBin.addSalt(new ProtoSalt());
	assertFalse(SaltBin.hasSalt("create domain key on foo. references foo.bar with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement2 () {
	SaltBin.addSalt(new ProtoSalt());
	assertFalse(SaltBin.hasSalt("create domain key on .bar references foo.bar with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement3 () {
	SaltBin.addSalt(new ProtoSalt());
	assertFalse(SaltBin.hasSalt("create domain key on foo.bar references foo. with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement4 () {
	SaltBin.addSalt(new ProtoSalt());
	assertFalse(SaltBin.hasSalt("create domain key on foo.bar references .bar with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement5 () {
	SaltBin.addSalt(new ProtoSalt());
	assertFalse(SaltBin.hasSalt("create domain key on foo.bar references foo.bar with message ''"));}

    public void testProtoSaltEmitsDropTriggerSQL () {
	new Script () {
	    public void run () throws Exception {
		String g = 
		    "drop_trigger(name) ::= <<\n" +
		    "  drop trigger if exists <name>\n" +
		    ">>";
		STGroup group = new STGroupString(g);
		AbstractSalt.setSTGroup("Apache Derby Embedded JDBC Driver", group);
		Connection conn = DriverManager.getConnection("jdbc:" + DB);
		SaltBin.addSalt(new ProtoSalt());
		for (String s : SaltBin.getSaltedSQL(conn, "create domain key on foo.bar references foo.bar with message 'foo'"))
		    System.out.println(s);
		assertNotNull(SaltBin.getSaltedSQL(conn, "create domain key on foo.bar references foo.bar with message 'foo'"));}};}

    // public void testExpandThrowsExceptionForInvalidStatement () {
    // 	try {MacroProcessor.expand("create domain key on foo.bar references foo.bar with message ''");}
    // 	catch (Exception e) {return;}
    // 	fail("Exception should have been thrown.");}

    // public void testExpandDoesNotThrowExceptionForValidStatement () {
    // 	try {MacroProcessor.expand("create domain key on foo.bar references foo.bar with message 'foo'");}
    // 	catch (Exception e) {fail("Exception should not have been thrown.");}}

    // public void testExpandProducesCorrectExpansion () {
    // 	try {
    // 	    for (String s : MacroProcessor.expand("create domain key on phone.person_id references adult.id with message 'Only persons older than 18 can have a telephone'"))
    // 		System.out.println("\n" + s);}
    // 	catch (Exception e) {
    // 	    e.printStackTrace();
    // 	    fail("Exception should not have been thrown.");}}
}