package com.jw.server;

import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.*;

public class RemoteDriverImpl extends UnicastRemoteObject implements IRemoteDriver {
    private static String DSN;
    private static String dsUser;
    private static String dsPassword;
    public static int queryLog = 0;
    private static int connectionPoolSize = 1;

    public RemoteDriverImpl () throws RemoteException {
	super();}

    public static void main (String args[]) {
	System.setSecurityManager(new RMISecurityManager());
	try {
	    ResourceBundle settingsBundle = ResourceBundle.getBundle("DriverSettings");
	    DSN = settingsBundle.getString("DSN");
	    dsUser = settingsBundle.getString("User");
	    dsPassword = settingsBundle.getString("Password");
	    queryLog = Integer.parseInt(settingsBundle.getString("QueryLog"));
	    connectionPoolSize = Integer.parseInt(settingsBundle.getString("ConnectionPoolSize"));
	    RemoteDriverImpl serverInstance = new RemoteDriverImpl();
	    Naming.rebind("RemoteDriver",serverInstance);
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    serverInstance.initializeConnectionPool();
	    System.out.println();
	    System.out.println("Remote Driver server has started successfully...");
	    System.out.println();
	    System.out.println("Press 'Y' to stop the server...");
	    while (System.in.read()!='Y') {}
	    serverInstance.freeConnectionPool();
	    DriverLog.getInstance().closeLog();
	    System.out.println("Closing the remote server");
	    System.exit(0);}
	catch (Exception ex) {ex.printStackTrace();}}

    private void initializeConnectionPool () {
	try {
	    String URL="jdbc:odbc:"+DSN;
	    for (int i = 0; i<connectionPoolSize; i++) {
		Connection sqlCon = DriverManager.getConnection(URL,dsUser,dsPassword);
		ConnectionPool.getInstance().addConnection(sqlCon);}}
	catch (Exception ex) {ex.printStackTrace();}}

    private void freeConnectionPool () {
	try {
	    while (true) {
		Connection con = ConnectionPool.getInstance().getConnection();
		if (con == null) break;
		con.close();}}
	catch (Exception ex) {}}

    public IRemoteConnection getConnection () throws RemoteException, SQLException {
	Connection con = ConnectionPool.getInstance().getConnection();
	if (con==null) throw new SQLException("All connections in the driver Connection Pool are in use.");
	RemoteConnectionImpl ConnectionInstance = new RemoteConnectionImpl(con);
	return (IRemoteConnection)ConnectionInstance;}}
