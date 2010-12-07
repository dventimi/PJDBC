package com.omnicorps.global.pjdbc; // Generated package name

public class SQLMacroProcessorTest extends AutoTest {
    public static void main(String[] args){autorun(new Exception());}

    public void testAcceptsStatementReturnsTrueForValidStatement () {
	assertTrue(SQLMacroProcessor.acceptsStatement("create    domain key on foo.bar references foo.bar with message 'foo'"));}

    public void testAcceptsStatementReturnsFalseForInValidStatement1 () {
	assertFalse(SQLMacroProcessor.acceptsStatement("create domain key on foo. references foo.bar with message 'foo'"));}

    public void testAcceptsStatementReturnsFalseForInValidStatement2 () {
	assertFalse(SQLMacroProcessor.acceptsStatement("create domain key on .bar references foo.bar with message 'foo'"));}

    public void testAcceptsStatementReturnsFalseForInValidStatement3 () {
	assertFalse(SQLMacroProcessor.acceptsStatement("create domain key on foo.bar references foo. with message 'foo'"));}

    public void testAcceptsStatementReturnsFalseForInValidStatement4 () {
	assertFalse(SQLMacroProcessor.acceptsStatement("create domain key on foo.bar references .bar with message 'foo'"));}

    public void testAcceptsStatementReturnsFalseForInValidStatement5 () {
	assertFalse(SQLMacroProcessor.acceptsStatement("create domain key on foo.bar references foo.bar with message ''"));}

    public void testExpandThrowsExceptionForInvalidStatement () {
	try {SQLMacroProcessor.expand("create domain key on foo.bar references foo.bar with message ''");}
	catch (Exception e) {return;}
	fail("Exception should have been thrown.");}

    public void testExpandDoesNotThrowExceptionForValidStatement () {
	try {SQLMacroProcessor.expand("create domain key on foo.bar references foo.bar with message 'foo'");}
	catch (Exception e) {fail("Exception should not have been thrown.");}}

    public void testExpandProducesCorrectExpansion () {
	try {
	    for (String s : SQLMacroProcessor.expand("create domain key on phone.person_id references adult.id with message 'Only persons older than 18 can have a telephone'"))
		System.out.println(s+"\n");}
	catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception should not have been thrown.");}}
}