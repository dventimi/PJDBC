package org.pjdbc.util;

import junit.framework.TestCase;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

public abstract class AutoTest extends TestCase {
    public static void autorun (String className) {
	try {for (Failure failure : JUnitCore.runClasses(Class.forName(className)).getFailures()) System.out.println(failure);}
	catch (ClassNotFoundException e) {fail("JUnit test class " + className + " could not be found.");}}

    public static void autorun (Exception e) {
	autorun((e.getStackTrace()[0]).getClassName());}

    protected static abstract class Script {
	{try {this.run();} catch (Throwable t) {t.printStackTrace(); fail();}}
	public void run () throws Exception {fail("Script.run() has not been implemented!");}}}
