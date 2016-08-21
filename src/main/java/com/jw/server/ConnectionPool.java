package com.jw.server;

import java.sql.*;
import java.util.*;

public class ConnectionPool {
    private static ConnectionPool connectionPoolInstance = null;
    private Queue<Connection> connectionPool = null;

    private ConnectionPool () {
	connectionPool = new LinkedList<Connection>();}

    public static ConnectionPool getInstance () {
	if (connectionPoolInstance==null) connectionPoolInstance = new ConnectionPool();
	return connectionPoolInstance;}

    public synchronized void addConnection(Connection con) {
	connectionPool.add(con);}

    public synchronized Connection getConnection () {
	return connectionPool.poll();}}

