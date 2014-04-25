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

    public String nativeSQL(String sql)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public void setAutoCommit(boolean autoCommit)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public boolean getAutoCommit()
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public void commit() throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public void rollback() throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public boolean isClosed()throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public DatabaseMetaData getMetaData()
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public void setReadOnly(boolean readOnly)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public boolean isReadOnly()
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public void setCatalog(String catalog)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public String getCatalog()
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }
    public void setTransactionIsolation(int level)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public int getTransactionIsolation()
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public SQLWarning getWarnings()
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public void clearWarnings()
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public PreparedStatement prepareStatement(String sql)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public CallableStatement prepareCall(String sql)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public PreparedStatement prepareStatement(String sql,int resultSetType,int resultSetConcurrency)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public CallableStatement prepareCall(String sql,int resultSetType,int resultSetConcurrency)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public Statement createStatement(int resultSetType,int resultSetConcurrency)
	throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public void setTypeMap(Map map) throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

    public Map getTypeMap() throws SQLException
    {
	throw(new SQLException("Not Supported"));
    }

}
