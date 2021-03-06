package com.jw.client;

import com.jw.server.*;
import java.rmi.*;
import java.sql.*;
import java.util.*;

public abstract class JWStatement implements Statement {
    private IRemoteStatement remoteStmt;

    public JWStatement (IRemoteStatement stmt) {
	remoteStmt = stmt;}

    // public ResultSet executeQuery (String sqlQuery) throws SQLException {
    // 	try {return new JWResultSet((IRemoteResultSet)remoteStmt.executeQuery(sqlQuery));} catch (RemoteException ex) {throw new SQLException(ex.getMessage());}}

    public int executeUpdate (String sqlQuery) throws SQLException {
	try {return remoteStmt.executeUpdate(sqlQuery);} catch (RemoteException ex) {throw new SQLException(ex.getMessage());}}

    public void close () throws SQLException {
	try {remoteStmt.close();} catch (RemoteException ex) {throw new SQLException(ex.getMessage());}}

    public int getMaxFieldSize () throws SQLException {throw new SQLException("Not Supported");}
    public void setMaxFieldSize(int max) throws SQLException {throw new SQLException("Not Supported");}
    public int getMaxRows() throws SQLException {throw new SQLException("Not Supported");}
    public void setMaxRows(int max) throws SQLException {throw new SQLException("Not Supported");}
    public void setEscapeProcessing(boolean enable) throws SQLException {throw new SQLException("Not Supported");}
    public int getQueryTimeout() throws SQLException {throw new SQLException("Not Supported");}
    public void setQueryTimeout(int seconds) throws SQLException {throw new SQLException("Not Supported");}
    public void cancel() throws SQLException {throw new SQLException("Not Supported");}
    public SQLWarning getWarnings() throws SQLException {throw new SQLException("Not Supported");}
    public void clearWarnings() throws SQLException {throw new SQLException("Not Supported");}
    public void setCursorName(String name) throws SQLException {throw new SQLException("Not Supported");}
    public boolean execute(String sql) throws SQLException {throw new SQLException("Not Supported");}
    public ResultSet getResultSet() throws SQLException {throw new SQLException("Not Supported");}
    public int getUpdateCount() throws SQLException {throw new SQLException("Not Supported");}
    public boolean getMoreResults() throws SQLException {throw new SQLException("Not Supported");}
    public void setFetchDirection(int direction) throws SQLException {throw new SQLException("Not Supported");}
    public int getFetchDirection() throws SQLException {throw new SQLException("Not Supported");}
    public void setFetchSize(int rows) throws SQLException {throw new SQLException("Not Supported");}
    public int getFetchSize() throws SQLException {throw new SQLException("Not Supported");}
    public int getResultSetConcurrency() throws SQLException {throw new SQLException("Not Supported");}
    public int getResultSetType() throws SQLException {throw new SQLException("Not Supported");}
    public void addBatch(String sql) throws SQLException {throw new SQLException("Not Supported");}
    public void clearBatch() throws SQLException {throw new SQLException("Not Supported");}
    public int[] executeBatch() throws SQLException {throw new SQLException("Not Supported");}
    public Connection getConnection() throws SQLException {throw new SQLException("Not Supported");}}
