package org.pjdbc.rmi;

import java.rmi.*;
import java.sql.*;
import java.util.*;

public interface StatementHandler extends WrapperHandler, Remote {
    public void addBatch (String sql) throws RemoteException;
    public void cancel () throws RemoteException;
    public void clearBatch () throws RemoteException;
    public void clearWarnings () throws RemoteException;
    public void close () throws RemoteException;
    public void closeOnCompletion () throws RemoteException;
    public void setCursorName (String name) throws RemoteException;
    public void setEscapeProcessing (boolean enable) throws RemoteException;
    public void setFetchDirection (int direction) throws RemoteException;
    public void setFetchSize (int rows) throws RemoteException;
    public void setMaxFieldSize (int max) throws RemoteException;
    public void setMaxRows (int max) throws RemoteException;
    public void setPoolable (boolean poolable) throws RemoteException;
    public void setQueryTimeout (int seconds) throws RemoteException;
    public Connection getConnection () throws RemoteException;
    public ResultSet executeQuery (String sql) throws RemoteException;
    public ResultSet getGeneratedKeys () throws RemoteException;
    public ResultSet getResultSet () throws RemoteException;
    public SQLWarning getWarnings () throws RemoteException;
    public boolean execute (String sql) throws RemoteException;
    public boolean execute (String sql, String[] columnNames) throws RemoteException;
    public boolean execute (String sql, int autoGeneratedKeys) throws RemoteException;
    public boolean execute (String sql, int[] columnIndexes) throws RemoteException;
    public boolean getMoreResults () throws RemoteException;
    public boolean getMoreResults (int current) throws RemoteException;
    public boolean isCloseOnCompletion () throws RemoteException;
    public boolean isClosed () throws RemoteException;
    public boolean isPoolable () throws RemoteException;
    public int executeUpdate (String sql) throws RemoteException;
    public int executeUpdate (String sql, String[] columnNames) throws RemoteException;
    public int executeUpdate (String sql, int autoGeneratedKeys) throws RemoteException;
    public int executeUpdate (String sql, int[] columnIndexes) throws RemoteException;
    public int getFetchDirection () throws RemoteException;
    public int getFetchSize () throws RemoteException;
    public int getMaxFieldSize () throws RemoteException;
    public int getMaxRows () throws RemoteException;
    public int getQueryTimeout () throws RemoteException;
    public int getResultSetConcurrency () throws RemoteException;
    public int getResultSetHoldability () throws RemoteException;
    public int getResultSetType () throws RemoteException;
    public int getUpdateCount () throws RemoteException;
    public int[] executeBatch () throws RemoteException;
    public boolean isWrapperFor (Class<?> iface);
    public <T> T unwrap (Class<T> iface) throws RemoteException;}