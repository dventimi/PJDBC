package com.jw.server;

import java.io.*;
import java.util.Date;

public class DriverLog {
    private static DriverLog singleLogInstance = null;
    private String logFile = "JWDriver.log";
    private static FileWriter logFileWriter = null;
    private static PrintWriter logPrintWriter = null;

    private DriverLog () {
	try {
	    logFileWriter = new FileWriter(logFile,true);
	    logPrintWriter = new PrintWriter(logFileWriter,true);}
	catch (Exception ex) {
	    logFileWriter = null;
	    logPrintWriter = null;
	    ex.printStackTrace();}}

    public static DriverLog getInstance() {
	if (singleLogInstance == null) singleLogInstance = new DriverLog();
	return singleLogInstance;}

    public void logQuery(String query) {
	try {
	    if (RemoteDriverImpl.queryLog==1) 
		if(logPrintWriter != null)
		    synchronized (logPrintWriter) {
			Date theDate = new Date();
			String fullMsg = "Logged at:" + theDate.toString();
			logPrintWriter.println("-------------------------------------------------------------------------------------------");
			logPrintWriter.println(fullMsg);
			logPrintWriter.println(query);}}
	catch (Exception ex) {}}

    public void closeLog () {
	try {
	    logPrintWriter.close();
	    logFileWriter.close();}
	catch (Exception ex) {}}}
