package org.pjdbc; // Generated package name

import junit.framework.TestCase;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

/**
 * <code>AutoTest</code> is a convenience class for creating
 * easily-runnable JUnit tests.  Create new tests that subclass
 * <code>AutoTest</code> instead of <code>TestCase</code>, which can
 * then be run from the command-line with Junit, in the following way.
 *
 *  <pre>java org.junit.runner.JUnitCore</pre> <em>AutoTest</em>
 *
 * where <em>AutoTest</em> is replaced with the appropriate
 * <code>AutoTest</em> subclass.
 *
 * In addition, test methods only have to encapsulate their test code
 * in local variable instances of anonymous classes that implement the
 * <code>Script</code> inner class.  Such test methods will
 * automatically have thrown Exceptions caught and logged to stdout,
 * and the thrown Exception will automatically be interpreted as a
 * test failure.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public abstract class AutoTest extends TestCase {
    /**
     * <code>autorun</code> is a convenience method that automatically
     * runs the given JUnit test with the JUnit runtime.
     *
     * @param className <code>String</code> the test to run
     */
    public static void autorun (String className) {
	try {
	    for (Failure failure : JUnitCore.runClasses(Class.forName(className)).getFailures())
		System.out.println(failure);}
	catch (ClassNotFoundException e) {
	    fail("JUnit test class " + className + " could not be found.");}}

    /**
     * <code>autorun</code> runs the given test using the
     * <code>ClassLoader</code> associated with the given
     * <code>Exception</code>.
     *
     * @param e an <code>Exception</code> value
     */
    public static void autorun (Exception e) {
	autorun((e.getStackTrace()[0]).getClassName());}

    protected static abstract class Script {
	{try {this.run();} catch (Throwable t) {t.printStackTrace(); fail();}}
	public void run () throws Exception {
	    fail("Script.run() has not been implemented!");}}
}