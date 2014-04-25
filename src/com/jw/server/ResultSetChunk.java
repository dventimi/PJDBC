/**
 * ResultSetChunk - This class is the chunk of data that is passed from the 
 * RemoteResultSet to the client resultset layer (JWResultSet) for efficiency
 */
package com.jw.server;

import java.io.*;
import java.util.*;

public class ResultSetChunk implements Serializable
{
	//Rows of data
	private Vector rsRows;
	//current data row index
	int curIndex = 0;
	
	/**
	* Constructor for creating the chunk ResultSet
	*/
	public ResultSetChunk(Vector rows)
	{
		rsRows = rows;
	}
	
	/**
	 * This method gets the returns the next row from the current data chunk
	 * if it is available otherwise it returns null.
	 */
	public Object[] getNextRow()
	{
		Object []row = null;
		if(curIndex < rsRows.size())
		{
			row = (Object[])rsRows.elementAt(curIndex);
			curIndex++;
		}
		
		return row;
	}
}