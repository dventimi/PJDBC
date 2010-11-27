package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.DriverManager;

public class ScratchHarness {
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