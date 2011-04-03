package org.radiumsalt; // Generated package name

import org.pjdbc.AutoTest;

public class ProtoSaltTest extends AutoTest {
    public static void main (String[] args) {autorun(new Exception());}

    public void setUp () {
	SaltBin.init();}

    public void testFoundSaltForValidStatement () {
	SaltBin.addSalt(new ProtoSalt());
	assertNotNull(SaltBin.getSalt("create     domain key on foo.bar references foo.bar with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement1 () {
	SaltBin.addSalt(new ProtoSalt());
	assertNull(SaltBin.getSalt("create domain key on foo. references foo.bar with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement2 () {
	SaltBin.addSalt(new ProtoSalt());
	assertNull(SaltBin.getSalt("create domain key on .bar references foo.bar with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement3 () {
	SaltBin.addSalt(new ProtoSalt());
	assertNull(SaltBin.getSalt("create domain key on foo.bar references foo. with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement4 () {
	SaltBin.addSalt(new ProtoSalt());
	assertNull(SaltBin.getSalt("create domain key on foo.bar references .bar with message 'foo'"));}

    public void testFoundNoSaltForInValidStatement5 () {
	SaltBin.addSalt(new ProtoSalt());
	assertNull(SaltBin.getSalt("create domain key on foo.bar references foo.bar with message ''"));}

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