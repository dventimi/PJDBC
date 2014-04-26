package com.jw.server;

import java.sql.*;
import java.util.*;

public class ConnectionPool {
    private static ConnectionPool connectionPoolInstance = null;
    private Vector connectionPool = null;

    private ConnectionPool () {
	connectionPool = new Vector();}

    public static ConnectionPool getInstance () {
	if (connectionPoolInstance==null) connectionPoolInstance = new ConnectionPool();
	return connectionPoolInstance;}

    public synchronized void addConnection(Connection con) {
	connectionPool.addElement(con);}

    public synchronized Connection getConnection () {
	Connection con = null;
	if (connectionPool.size()>0) {
	    con = (Connection)connectionPool.lastElement();
	    connectionPool.removeElementAt(connectionPool.size()-1);}
	return con;}}
