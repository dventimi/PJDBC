/**
 * RemoteResultSetImpl - This class contains the actual JDBC-ODBC ResultSet. 
 * It acts as a remote wrapper over the JDBC-ODBC ResultSet.
 */

package com.jw.server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.sql.*;
import java.io.*;

public class RemoteResultSetImpl extends UnicastRemoteObject
	implements IRemoteResultSet
{
	//JDBC-ODBC ResultSet 
	private ResultSet sqlRs;
	//ResultSet meta data
	private ResultSetMetaData rsmd;
	
	//Number of columns in the ResultSet
	int colNum;
	
	//Map of Column Index to Column Name
	Hashtable columnList;
	
	//Maximum number of resultset rows that can be sent to client in chunk
	private static int CHUNK_SIZE = 50;

	/**
	 * Constructor for creating RemoteResultSetImpl with JDBC-ODBC ResultSet
	 */
	public RemoteResultSetImpl(ResultSet rs) throws RemoteException,SQLException
	{
		super();
		
		sqlRs = rs;
		
		//extract the Metadata for the ResultSet
		rsmd = sqlRs.getMetaData();
		colNum = rsmd.getColumnCount();
		columnList = new Hashtable(20,10);

		for(int i = 1; i <= colNum; i++)
		{
			String columnName = rsmd.getColumnName(i);
			Integer columnIndex = new Integer(i);
			columnList.put(columnName,columnIndex);
		}
	}

	/**
	 * This method returns Chunk of data rows in ResultSetChunk.
	 * It returns null if ResultSet does not have any more rows.
	 */
	public ResultSetChunk getNextChunk() throws RemoteException,SQLException
	{
		Vector rsDataChunk = new Vector();
		for(int curRowIndex = 0; curRowIndex < CHUNK_SIZE; curRowIndex++)
		{	 
			if(sqlRs.next() == false)
				break;
			
			//prepare the data row in an array of Objects
			Object []row = new Object[colNum];
			for(int i = 1; i <= colNum; i++)
			{
				row[i-1] = sqlRs.getString(i);
			}
			rsDataChunk.addElement(row);
		}
		
		//if no more data is left then return null
		if(rsDataChunk.size() == 0)
			return null;
		
		ResultSetChunk rsChunk = new ResultSetChunk(rsDataChunk);		
		return rsChunk;
	}
	
	/**
	 * It closes the JDBC-ODBC ResultSet
	 */
	public void close() throws RemoteException,SQLException
	{
		sqlRs.close();
	}
	
	/**
	 * This method returns the map of Column Index to Column Name
	 */
	public Hashtable getColumnList() throws RemoteException,SQLException
	{
		return columnList;
	}
}