package com.jw.client;

import com.jw.server.*;
import java.rmi.*;
import java.sql.*;
import java.util.*;

public abstract class JWConnection implements Connection {
    private IRemoteConnection remoteConnection;

    public JWConnection (IRemoteConnection remCon) {
	remoteConnection = remCon;}

    public Statement createStatement () throws SQLException {
	try {
	    IRemoteStatement remStmt = (IRemoteStatement)remoteConnection.createStatement();
	    // JWStatement localStmtInstance = new JWStatement(remStmt);
	    // return (Statement)localStmtInstance;}
	    return null;}
	catch (RemoteException ex) {throw new SQLException("RemoteException:" + ex.getMessage());}
	catch (Exception ex) {throw new SQLException("RemoteException:" + ex.getMessage());}}

    public void close () throws SQLException {
	try {remoteConnection.closeConnection();}
	catch (RemoteException ex) {throw new SQLException("RemoteException:" + ex.getMessage());}}

    public String nativeSQL(String sql) throws SQLException {throw new SQLException("Not supported");}
    public void setAutoCommit(boolean autoCommit) throws SQLException {throw new SQLException("Not supported");}
    public boolean getAutoCommit() throws SQLException {throw new SQLException("Not supported");}
    public void commit() throws SQLException {throw new SQLException("Not supported");}
    public void rollback() throws SQLException {throw new SQLException("Not supported");}
    public boolean isClosed() throws SQLException {throw new SQLException("Not supported");}
    public DatabaseMetaData getMetaData() throws SQLException {throw new SQLException("Not supported");}
    public void setReadOnly(boolean readOnly) throws SQLException {throw new SQLException("Not supported");}
    public boolean isReadOnly() throws SQLException {throw new SQLException("Not supported");}
    public void setCatalog(String catalog) throws SQLException {throw new SQLException("Not supported");}
    public String getCatalog() throws SQLException {throw new SQLException("Not supported");}
    public void setTransactionIsolation(int level) throws SQLException {throw new SQLException("Not supported");}
    public int getTransactionIsolation() throws SQLException {throw new SQLException("Not supported");}
    public SQLWarning getWarnings() throws SQLException {throw new SQLException("Not supported");}
    public void clearWarnings() throws SQLException {throw new SQLException("Not supported");}
    public PreparedStatement prepareStatement(String sql) throws SQLException {throw new SQLException("Not supported");}
    public CallableStatement prepareCall(String sql) throws SQLException {throw new SQLException("Not supported");}
    public PreparedStatement prepareStatement(String sql,int resultSetType,int resultSetConcurrency) throws SQLException {throw new SQLException("Not supported");}
    public CallableStatement prepareCall(String sql,int resultSetType,int resultSetConcurrency) throws SQLException {throw new SQLException("Not supported");}
    public Statement createStatement(int resultSetType,int resultSetConcurrency) throws SQLException {throw new SQLException("Not supported");}
    public void setTypeMap(Map map) throws SQLException {throw new SQLException("Not supported");}
    public Map getTypeMap() throws SQLException {throw new SQLException("Not supported");}}
