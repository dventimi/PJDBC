package com.jw.server;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.*;

public class RemoteResultSetImpl extends UnicastRemoteObject implements IRemoteResultSet {
    private ResultSet sqlRs;
    private ResultSetMetaData rsmd;
    int colNum;
    Map<String, Integer> columnList;
    private static int CHUNK_SIZE = 50;

    public RemoteResultSetImpl (ResultSet rs) throws RemoteException, SQLException {
	super();
	sqlRs = rs;
	rsmd = sqlRs.getMetaData();
	colNum = rsmd.getColumnCount();
	columnList = new HashMap<String, Integer>();
	for (int i = 1; i <= colNum; i++) {
	    String columnName = rsmd.getColumnName(i);
	    Integer columnIndex = new Integer(i);
	    columnList.put(columnName, columnIndex);}}

    public ResultSetChunk getNextChunk () throws RemoteException, SQLException {
	List<Object> rsDataChunk = new ArrayList<Object>();
	for (int curRowIndex = 0; curRowIndex < CHUNK_SIZE; curRowIndex++) {
	    if (sqlRs.next() == false) break;
	    Object []row = new Object[colNum];
	    for (int i = 1; i <= colNum; i++) row[i-1] = sqlRs.getString(i);
	    rsDataChunk.add(row);}
	if (rsDataChunk.size()==0) return null;
	ResultSetChunk rsChunk = new ResultSetChunk(rsDataChunk);
	return rsChunk;}

    public void close () throws RemoteException, SQLException {
	sqlRs.close();}

    public Map<String, Integer> getColumnList () throws RemoteException, SQLException {
	return columnList;}}
