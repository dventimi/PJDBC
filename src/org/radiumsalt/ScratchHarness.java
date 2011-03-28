package org.radiumsalt; // Generated package name

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <code>ScratchHarness</code> is just a runnable class that has a
 * <code>main</code> method.  Use it to run arbitrary code as needed
 * while developing.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public class ScratchHarness {
    /**
     * Describe <code>main</code> method here.
     *
     * @param args a <code>String</code> value
     */
    public static void main (String[] args) {
	try {
	    Class.forName("com.omnicorps.global.pjdbc.MacroDriver");
	    Connection conn = DriverManager.getConnection("jdbc:mjdbc:derby:memory:testdb;create=true");
	    conn.createStatement().execute("create table person (name varchar(30))");
	}
	catch (Exception e) {
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
    }
}