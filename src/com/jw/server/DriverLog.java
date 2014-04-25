/**
 * DriverLog - This class logs the SQL statements into a JWDriver.log file for 
 * tracking any issue 
 */
package com.jw.server;

import java.io.*;
import java.util.Date;

public class DriverLog
{
	//Singleton instance of DriverLog
	private static DriverLog singleLogInstance = null;
		
	private String logFile = "C:\\TEMP\\JWDriver.log";
		
	/**
    * Reference to the Java PrintWriter that handles appending string 
    * messages to the log file.
    */
    private static FileWriter logFileWriter = null;
	
	/**
    * Reference to the Java PrintWriter that handles appending string 
    * messages to the log file.
    */
    private static PrintWriter logPrintWriter = null;
	
	/**
	 * Private constructore for DriverLog to keep it single instance
	 */
	private DriverLog()
	{
		try
		{
			logFileWriter = new FileWriter(logFile,true);
			logPrintWriter = new PrintWriter(logFileWriter,true);
		}
		catch(Exception ex)
		{
			logFileWriter = null;
			logPrintWriter = null;
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method returns the singleton instance of DriverLog
	 */
	public static DriverLog getInstance()
	{
		if(singleLogInstance == null)
			singleLogInstance = new DriverLog();
		
		return singleLogInstance;
	}
	
	/**
	 * This method logs the query into the JWDriver.log file along with the current
	 * timestamp
	 */
	public void logQuery(String query)
	{
		try
		{
			if(RemoteDriverImpl.queryLog == 1)
			{
				if(logPrintWriter != null)
				{	
					synchronized(logPrintWriter)
					{	
						Date theDate = new Date();
						String fullMsg = "Logged at:" + theDate.toString();
						logPrintWriter.println("-------------------------------------------------------------------------------------------");
						logPrintWriter.println(fullMsg);
						logPrintWriter.println(query);
					}
				}	
			}	
		}
		catch(Exception ex){}
	}
	
	/**
	 * This method is called during the termination of the Driver Server. It 
	 * closes the log file.
	 */
	public void closeLog()
	{
		try
		{
			logPrintWriter.close();
			logFileWriter.close();
		}
		catch(Exception ex) { }			
	}	
}
