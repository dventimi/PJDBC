package org.pjdbc.util;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public abstract class AbstractProxyStatement implements Statement {
    private Connection conn;
    private Statement delegate;
    private List<Statement> delegates = new ArrayList<Statement>();

    protected Statement getDelegate() {return this.delegate;}

    public void addBatch (String sql) throws SQLException {for (Statement d : delegates) d.addBatch(sql);}
    public void cancel () throws SQLException {for (Statement d : delegates) d.cancel();}
    public void clearBatch () throws SQLException {for (Statement d : delegates) d.clearBatch();}
    public void clearWarnings () throws SQLException {for (Statement d : delegates) d.clearWarnings();}
    public void close () throws SQLException {for (Statement d : delegates) d.close();}
    public void closeOnCompletion () throws SQLException {for (Statement d : delegates) d.closeOnCompletion();}
    public void setCursorName (String name) throws SQLException {for (Statement d : delegates) d.setCursorName(name);}
    public void setEscapeProcessing (boolean enable) throws SQLException {for (Statement d : delegates) d.setEscapeProcessing(enable);}
    public void setFetchDirection (int direction) throws SQLException {for (Statement d : delegates) d.setFetchDirection(direction);}
    public void setFetchSize (int rows) throws SQLException {for (Statement d : delegates) d.setFetchSize(rows);}
    public void setMaxFieldSize (int max) throws SQLException {for (Statement d : delegates) d.setMaxFieldSize(max);}
    public void setMaxRows (int max) throws SQLException {for (Statement d : delegates) d.setMaxRows(max);}
    public void setPoolable (boolean poolable) throws SQLException {for (Statement d : delegates) d.setPoolable(poolable);}
    public void setQueryTimeout (int seconds) throws SQLException {for (Statement d : delegates) d.setQueryTimeout(seconds);}
    public Connection getConnection () throws SQLException {return this.conn;}
    public ResultSet executeQuery (String sql) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.executeQuery(sql);
	return delegates.get(0).executeQuery(sql);}
    public ResultSet getGeneratedKeys () throws SQLException {return delegates.get(0).getGeneratedKeys();}
    public ResultSet getResultSet () throws SQLException {return delegates.get(0).getResultSet();}
    public SQLWarning getWarnings () throws SQLException {return delegates.get(0).getWarnings();}
    public boolean execute (String sql) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.execute(sql);
	return delegates.get(0).execute(sql);}
    public boolean execute (String sql, String[] columnNames) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.execute(sql, columnNames);
	return delegates.get(0).execute(sql, columnNames);}
    public boolean execute (String sql, int autoGeneratedKeys) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.execute(sql, autoGeneratedKeys);
	return delegates.get(0).execute(sql, autoGeneratedKeys);}
    public boolean execute (String sql, int[] columnIndexes) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.execute(sql, columnIndexes);
	return delegates.get(0).execute(sql, columnIndexes);}
    public boolean getMoreResults () throws SQLException {return delegates.get(0).getMoreResults();}
    public boolean getMoreResults (int current) throws SQLException {return delegates.get(0).getMoreResults(current);}
    public boolean isCloseOnCompletion () throws SQLException {return delegates.get(0).isCloseOnCompletion();}
    public boolean isClosed () throws SQLException {return delegates.get(0).isClosed();}
    public boolean isPoolable () throws SQLException {return delegates.get(0).isPoolable();}
    public int executeUpdate (String sql) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.executeUpdate(sql);
	return delegates.get(0).executeUpdate(sql);}
    public int executeUpdate (String sql, String[] columnNames) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.executeUpdate(sql, columnNames);
	return delegates.get(0).executeUpdate(sql, columnNames);}
    public int executeUpdate (String sql, int autoGeneratedKeys) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.executeUpdate(sql, autoGeneratedKeys);
	return delegates.get(0).executeUpdate(sql, autoGeneratedKeys);}
    public int executeUpdate (String sql, int[] columnIndexes) throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.executeUpdate(sql, columnIndexes);
	return delegates.get(0).executeUpdate(sql, columnIndexes);}
    public int getFetchDirection () throws SQLException {return delegates.get(0).getFetchDirection();}
    public int getFetchSize () throws SQLException {return delegates.get(0).getFetchSize();}
    public int getMaxFieldSize () throws SQLException {return delegates.get(0).getMaxFieldSize();}
    public int getMaxRows () throws SQLException {return delegates.get(0).getMaxRows();}
    public int getQueryTimeout () throws SQLException {return delegates.get(0).getQueryTimeout();}
    public int getResultSetConcurrency () throws SQLException {return delegates.get(0).getResultSetConcurrency();}
    public int getResultSetHoldability () throws SQLException {return delegates.get(0).getResultSetHoldability();}
    public int getResultSetType () throws SQLException {return delegates.get(0).getResultSetType();}
    public int getUpdateCount () throws SQLException {return delegates.get(0).getUpdateCount();}
    public int[] executeBatch () throws SQLException {
	if (delegates.size()>1) for (Statement s : delegates.subList(1,delegates.size())) s.executeBatch();
	return delegates.get(0).executeBatch();}
    public boolean isWrapperFor (Class<?> iface) {return false;}
    public <T> T unwrap (Class<T> iface) throws SQLException {throw new SQLException();}

    public AbstractProxyStatement (Statement delegate, Connection conn)
	throws SQLException {
	this.delegates.add(delegate);
	this.conn = conn;}

    public AbstractProxyStatement (Statement delegate, Connection conn, List<Statement> delegates) 
	throws SQLException {
	this(delegate, conn);
	for (Statement s : delegates) addDelegate(s);}

    public AbstractProxyStatement (Statement delegate, Connection conn, Statement... delegates) 
	throws SQLException {
	this(delegate, conn);
	for (Statement s : delegates) addDelegate(s);}

    public boolean addDelegate (Statement stmt) 
	throws SQLException {
	return this.delegates.add(stmt);}

    public boolean removeDelegate (Statement stmt) 
	throws SQLException {
	return this.delegates.remove(stmt);}
}
