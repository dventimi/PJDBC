/**
 * ConnectionPool - This is a singleton class. It maintains the pool of free database
 * connections.
 */
package com.jw.server;

import java.util.*;
import java.sql.*;

public class ConnectionPool
{
	//singleton instance of the ConnectionPool
	private static ConnectionPool connectionPoolInstance = null;
	
	//pool of connections
	private Vector connectionPool = null;
	
	/**
	 * Private constructor for keeping the ConnectionPool singleton
	 */
	private ConnectionPool()
	{
		connectionPool = new Vector();
	}
	
	/**
	 * This method gets the singleton instance of the ConnectionPool
	 */
	public static ConnectionPool getInstance()
	{
		if(connectionPoolInstance == null)
			connectionPoolInstance = new ConnectionPool();
		
		return connectionPoolInstance;
	}
	
	/**
	 * This method adds a connection to the connection pool
	 */
	public synchronized void addConnection(Connection con)
	{	
		connectionPool.addElement(con);
	}
	
	/**
	 * This method gets a connection from the connection pool.
	 */
	public synchronized Connection getConnection()
	{
		Connection con = null;
		
		if(connectionPool.size() > 0)
		{	
			con = (Connection)connectionPool.lastElement();
			connectionPool.removeElementAt(connectionPool.size() - 1);
		}
		
		return con;
	}
}
