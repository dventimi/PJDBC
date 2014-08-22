package com.jw.server;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.*;

public class RemoteStatementImpl extends UnicastRemoteObject implements IRemoteStatement {
    private Statement sqlStatment;

    public RemoteStatementImpl(Statement sqlStmt) throws RemoteException {
	super();
	sqlStatment = sqlStmt;}

    public IRemoteResultSet executeQuery(String Query) throws RemoteException, SQLException {
	DriverLog.getInstance().logQuery(Query);
	ResultSet rs = sqlStatment.executeQuery(Query);
	RemoteResultSetImpl remoteRs = new RemoteResultSetImpl(rs);
	return (IRemoteResultSet)remoteRs;}

    public int executeUpdate(String Query) throws RemoteException, SQLException {
	DriverLog.getInstance().logQuery(Query);
	return sqlStatment.executeUpdate(Query);}

    public void close() throws RemoteException, SQLException {
	sqlStatment.close();}}
