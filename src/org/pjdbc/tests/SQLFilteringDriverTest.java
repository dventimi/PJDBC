// package org.pjdbc.test;

// import java.sql.Connection;
// import java.sql.Driver;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import org.pjdbc.drivers.BasicDriver;
// import org.pjdbc.util.AutoTest;

// public class SQLFilteringDriverTest extends AutoTest {
//     private static String SUBPROTOCOL = "mock";

//     public static void main (String[] args) {
// 	autorun(new Exception());}

//     public void setUp () throws ClassNotFoundException, SQLException {
// 	Class.forName("org.pjdbc.drivers.SQLFilteringDriver");
// 	DriverManager.registerDriver(getMockDriver(SUBPROTOCOL));}

