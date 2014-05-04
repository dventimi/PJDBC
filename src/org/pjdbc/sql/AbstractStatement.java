package org.pjdbc.sql;

import java.sql.*;
import java.util.*;

public abstract class AbstractStatement extends AbstractWrapper implements Statement {

    // Data

    private Connection conn;
    private Statement delegate;

    // Constructors

    AbstractStatement (Connection conn, Statement stmt) throws SQLException {
	super(stmt);
	this.conn = conn;
	this.delegate = stmt;}

    // Proxying machinery

    protected ResultSet wrap (ResultSet r) {
	return r;}

    // Statement API

    public Connection getConnection () throws SQLException {return delegate.getConnection();}
    public ResultSet executeQuery (String sql) throws SQLException {return wrap(delegate.executeQuery(sql));}
    public ResultSet getGeneratedKeys () throws SQLException {return wrap(delegate.getGeneratedKeys());}
    public ResultSet getResultSet () throws SQLException {return wrap(delegate.getResultSet());}
    public SQLWarning getWarnings () throws SQLException {return delegate.getWarnings();}
    public boolean execute (String sql) throws SQLException {return delegate.execute(sql);}
    public boolean execute (String sql, String[] columnNames) throws SQLException {return delegate.execute(sql, columnNames);}
    public boolean execute (String sql, int autoGeneratedKeys) throws SQLException {return delegate.execute(sql, autoGeneratedKeys);}
    public boolean execute (String sql, int[] columnIndexes) throws SQLException {return delegate.execute(sql, columnIndexes);}
    public boolean getMoreResults () throws SQLException {return delegate.getMoreResults();}
    public boolean getMoreResults (int current) throws SQLException {return delegate.getMoreResults(current);}
    public boolean isCloseOnCompletion () throws SQLException {return delegate.isCloseOnCompletion();}
    public boolean isClosed () throws SQLException {return delegate.isClosed();}
    public boolean isPoolable () throws SQLException {return delegate.isPoolable();}
    public int executeUpdate (String sql) throws SQLException {return delegate.executeUpdate(sql);}
    public int executeUpdate (String sql, String[] columnNames) throws SQLException {return delegate.executeUpdate(sql, columnNames);}
    public int executeUpdate (String sql, int autoGeneratedKeys) throws SQLException {return delegate.executeUpdate(sql, autoGeneratedKeys);}
    public int executeUpdate (String sql, int[] columnIndexes) throws SQLException {return delegate.executeUpdate(sql, columnIndexes);}
    public int getFetchDirection () throws SQLException {return delegate.getFetchDirection();}
    public int getFetchSize () throws SQLException {return delegate.getFetchSize();}
    public int getMaxFieldSize () throws SQLException {return delegate.getMaxFieldSize();}
    public int getMaxRows () throws SQLException {return delegate.getMaxRows();}
    public int getQueryTimeout () throws SQLException {return delegate.getQueryTimeout();}
    public int getResultSetConcurrency () throws SQLException {return delegate.getResultSetConcurrency();}
    public int getResultSetHoldability () throws SQLException {return delegate.getResultSetHoldability();}
    public int getResultSetType () throws SQLException {return delegate.getResultSetType();}
    public int getUpdateCount () throws SQLException {return delegate.getUpdateCount();}
    public int[] executeBatch () throws SQLException {return delegate.executeBatch();}
    public void addBatch (String sql) throws SQLException {delegate.addBatch(sql);}
    public void cancel () throws SQLException {delegate.cancel();}
    public void clearBatch () throws SQLException {delegate.clearBatch();}
    public void clearWarnings () throws SQLException {delegate.clearWarnings();}
    public void close () throws SQLException {delegate.close();}
    public void closeOnCompletion () throws SQLException {delegate.closeOnCompletion();}
    public void setCursorName (String name) throws SQLException {delegate.setCursorName(name);}
    public void setEscapeProcessing (boolean enable) throws SQLException {delegate.setEscapeProcessing(enable);}
    public void setFetchDirection (int direction) throws SQLException {delegate.setFetchDirection(direction);}
    public void setFetchSize (int rows) throws SQLException {delegate.setFetchSize(rows);}
    public void setMaxFieldSize (int max) throws SQLException {delegate.setMaxFieldSize(max);}
    public void setMaxRows (int max) throws SQLException {delegate.setMaxRows(max);}
    public void setPoolable (boolean poolable) throws SQLException {delegate.setPoolable(poolable);}
    public void setQueryTimeout (int seconds) throws SQLException {delegate.setQueryTimeout(seconds);}}