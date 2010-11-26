package com.omnicorps.global.pjdbc; // Generated package name

import junit.framework.TestCase;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

/**
 * Describe class <code>AutoTest</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public abstract class AutoTest 
    extends TestCase {
    /**
     * Describe <code>autorun</code> method here.
     *
     * @param className a <code>String</code> value
     */
    public static void autorun (String className) {
	try {
	    for (Failure failure : JUnitCore.runClasses(Class.forName(className)).getFailures())
		System.out.println(failure);
	}
	catch (ClassNotFoundException e) {
	    fail("JUnit test class " + className + " could not be found.");
	}
    }

    /**
     * Describe <code>autorun</code> method here.
     *
     * @param e an <code>Exception</code> value
     */
    public static void autorun (Exception e) {
	autorun((e.getStackTrace()[0]).getClassName());
    }

    protected static abstract class Script {
	{try {this.run();} catch (Throwable t) {t.printStackTrace(); fail();}}
	public void run () throws Exception {
	    fail("Script.run() has not been implemented!");
	}
    }
}