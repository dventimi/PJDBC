package com.jw.client;

import com.jw.server.*;
import java.io.*;
import java.rmi.*;
import java.sql.*;
import java.util.*;

public abstract class JWDriver implements Driver {
    static IRemoteDriver remoteDriver = null;
    private static final String URL_PREFIX = "jdbc:JWDriver:";
    private static final int MAJOR_VERSION = 1;
    private static final int MINOR_VERSION = 0;
    // static {
    //  try {
    //System.setSecurityManager(new RMISecurityManager());
    // JWDriver driverInst = new JWDriver();
    // DriverManager.registerDriver(driverInst);}
    // catch (Exception e) {}}

    public static String getURLPrefix () {
	return URL_PREFIX;}

    public Connection connect (String url, Properties loginProp) throws SQLException {
	JWConnection localConInstance = null;
	if (acceptsURL(url)) {
	    try {
		String serverName = url.substring(URL_PREFIX.length(),url.length());
		connectRemote(serverName);
		IRemoteConnection remoteConInstance = (IRemoteConnection)remoteDriver.getConnection();}
	    // localConInstance = new JWConnection(remoteConInstance);}
	    catch (RemoteException ex) {throw new SQLException(ex.getMessage());}
	    catch (Exception ex) {throw(new SQLException(ex.getMessage()));}}
	return (Connection)localConInstance;}

    private void connectRemote (String serverName) throws Exception {
	if (remoteDriver==null) remoteDriver = (IRemoteDriver)Naming.lookup("rmi://"+serverName+":1099"+"/RemoteDriver");}

    public boolean acceptsURL (String url) throws SQLException {
	return url.startsWith(URL_PREFIX);}

    public int getMajorVersion () {
	return MAJOR_VERSION;}

    public int getMinorVersion () {
	return MINOR_VERSION;}

    public DriverPropertyInfo[] getPropertyInfo (String url, Properties loginProps) throws SQLException {
	return new DriverPropertyInfo[0];}

    public boolean jdbcCompliant () {
	return false;}}
