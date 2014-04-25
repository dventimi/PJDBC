/**
 * RemoteConnectionImpl - This class performs the role of main RMI server in Type-III
 * Driver. The client driver connects to the object of this class via RMI and then gets 
 * the Connection. 
 */

package com.jw.server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.sql.*;

public class RemoteDriverImpl extends UnicastRemoteObject
	implements IRemoteDriver
{
	//Data Source Name
	private static String DSN;
	//Data Source user
	private static String  dsUser;
	//Data source password
	private static String dsPassword;
	//Query Log level
	public static int queryLog = 0;
	//Maximum Conenction Pool Size
	private static int connectionPoolSize = 1;

	public RemoteDriverImpl() throws RemoteException
	{
		super();
  	}

	/**
	 * This is the main starting method of RMI server. It expects
	 * Data Source Name,  Data Source user, Data source password as 
	 * first three arguments.
	 */
	public static void main(String args[])
	{
		System.setSecurityManager(new RMISecurityManager());

		try
		{
			//get the Data Source Name,  Data Source user, Data source password and log level 
			ResourceBundle settingsBundle = ResourceBundle.getBundle("DriverSettings");			
			DSN = settingsBundle.getString("DSN");
			dsUser = settingsBundle.getString("User");
			dsPassword = settingsBundle.getString("Password");
			queryLog = Integer.parseInt(settingsBundle.getString("QueryLog"));
			connectionPoolSize = Integer.parseInt(settingsBundle.getString("ConnectionPoolSize"));
			
			//Creaete an object of RemoteDriverImpl to register with RMI naming service
			//After this the Type-III client driver layer can access this object. 
			RemoteDriverImpl serverInstance = new RemoteDriverImpl();
			Naming.rebind("RemoteDriver",serverInstance);			
			
			//Load the JDBC-ODBC bridge driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
			serverInstance.initializeConnectionPool();
			
			//Wait for close server command
			System.out.println();
			System.out.println("Remote Driver server has started successfully...");
			System.out.println();
			System.out.println("Press 'Y' to stop the server...");
			
			while(System.in.read() != 'Y')
			{	
				
			}
			
			//Exit the server after proper cleanup
			serverInstance.freeConnectionPool();
			
			DriverLog.getInstance().closeLog();
			
			System.out.println("Closing the remote server");
			System.exit(0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();	
		}
	}
	
	/**
	 * This method initializes the Driver connection pool with
	 * the specified number of connections.
	 */
	private void initializeConnectionPool()
	{
		try
		{
			String URL="jdbc:odbc:"+DSN;
			for(int i = 0; i < connectionPoolSize; i++)
			{	
				Connection sqlCon = DriverManager.getConnection(URL,dsUser,dsPassword);
				ConnectionPool.getInstance().addConnection(sqlCon);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}	
	
	/**
	 * This method closes the connections available in connection pool.
	 */
	private void freeConnectionPool()
	{
		try
		{
			while(true)
			{
				Connection con = ConnectionPool.getInstance().getConnection();
				if(con == null)
					break;
				
				con.close();
			}	
		}
		catch(Exception ex) {}
	}	

	/**
	 * This method creates a JDBC-ODBC connection and then returns a reference 
	 * to the remote interface of the RemoteConnectionImpl object holding 
	 * JDBC-ODBC connection.
	 */
	public IRemoteConnection getConnection() throws RemoteException,SQLException
	{
		Connection con = ConnectionPool.getInstance().getConnection();
		if(con == null)
			throw new SQLException("All connections in the driver Connection Pool are in use.");
	
		RemoteConnectionImpl ConnectionInstance = new RemoteConnectionImpl(con);
		return (IRemoteConnection)ConnectionInstance;
	}
}