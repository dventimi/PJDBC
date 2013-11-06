package org.pjdbc.util;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public abstract class AbstractProxyConnection implements Connection {
    protected List<ConnectionInfo> listeners = new ArrayList<ConnectionInfo>();
    protected ConnectionInfo delegate;

    public Array createArrayOf (String typeName, Object[] elements) throws SQLException {return delegate.getConnection().createArrayOf(typeName, elements);}
    public Blob createBlob () throws SQLException {return delegate.getConnection().createBlob();}
    public Clob createClob () throws SQLException {return delegate.getConnection().createClob();}
    public DatabaseMetaData getMetaData () throws SQLException {return delegate.getConnection().getMetaData();}
    public Map<String,Class<?>> getTypeMap () throws SQLException {return delegate.getConnection().getTypeMap();}
    public NClob createNClob () throws SQLException {return delegate.getConnection().createNClob();}
    public Properties getClientInfo () throws SQLException {return delegate.getConnection().getClientInfo();}
    public SQLWarning getWarnings () throws SQLException {return delegate.getConnection().getWarnings();}
    public SQLXML createSQLXML () throws SQLException {return delegate.getConnection().createSQLXML();}
    public Savepoint setSavepoint () throws SQLException {return delegate.getConnection().setSavepoint();}
    public Savepoint setSavepoint (String name) throws SQLException {return delegate.getConnection().setSavepoint(name);}
    public String getCatalog () throws SQLException {return delegate.getConnection().getCatalog();}
    public String getClientInfo (String name) throws SQLException {return delegate.getConnection().getClientInfo(name);}
    public String getSchema () throws SQLException {return delegate.getConnection().getSchema();}
    public String nativeSQL (String sql) throws SQLException {return delegate.getConnection().nativeSQL(sql);}
    public Struct createStruct (String typeName, Object[] attributes) throws SQLException {return delegate.getConnection().createStruct(typeName, attributes);}
    public boolean getAutoCommit () throws SQLException {return delegate.getConnection().getAutoCommit();}
    public boolean isClosed () throws SQLException {return delegate.getConnection().isClosed();}
    public boolean isReadOnly () throws SQLException {return delegate.getConnection().isReadOnly();}
    public boolean isValid (int timeout) throws SQLException {return delegate.getConnection().isValid(timeout);}
    public int getHoldability () throws SQLException {return delegate.getConnection().getHoldability();}
    public int getNetworkTimeout () throws SQLException {return delegate.getConnection().getNetworkTimeout();}
    public int getTransactionIsolation () throws SQLException {return delegate.getConnection().getTransactionIsolation();}
    public void abort (Executor executor) throws SQLException {delegate.getConnection().abort(executor);}
    public void clearWarnings () throws SQLException {delegate.getConnection().clearWarnings();}
    public void close () throws SQLException {delegate.getConnection().close();}
    public void commit () throws SQLException {delegate.getConnection().commit();}
    public void releaseSavepoint (Savepoint savepoint) throws SQLException {delegate.getConnection().releaseSavepoint(savepoint);}
    public void rollback () throws SQLException {delegate.getConnection().rollback();}
    public void rollback (Savepoint savepoint) throws SQLException {delegate.getConnection().rollback(savepoint);}
    public void setAutoCommit (boolean autoCommit) throws SQLException {delegate.getConnection().setAutoCommit(autoCommit);}
    public void setCatalog (String catalog) throws SQLException {delegate.getConnection().setCatalog(catalog);}
    public void setClientInfo (Properties properties) throws SQLClientInfoException {delegate.getConnection().setClientInfo(properties);}
    public void setClientInfo (String name, String value) throws SQLClientInfoException {delegate.getConnection().setClientInfo(name, value);}
    public void setHoldability (int holdability) throws SQLException {delegate.getConnection().setHoldability(holdability);}
    public void setNetworkTimeout (Executor executor, int milliseconds) throws SQLException {delegate.getConnection().setNetworkTimeout(executor, milliseconds);}
    public void setReadOnly (boolean readOnly) throws SQLException {delegate.getConnection().setReadOnly(readOnly);}
    public void setSchema (String schema) throws SQLException {delegate.getConnection().setSchema(schema);}
    public void setTransactionIsolation (int level) throws SQLException {delegate.getConnection().setTransactionIsolation(level);}
    public void setTypeMap (Map<String,Class<?>> map) throws SQLException {delegate.getConnection().setTypeMap(map);}
    public boolean isWrapperFor (Class<?> iface) {
	if ("Connection".equals(iface.getName())) return true;
	return false;}
    public <T> T unwrap (Class<T> iface) throws SQLException {throw new SQLException();}

    public AbstractProxyConnection (ConnectionInfo info)
	throws SQLException {
	this.delegate = info;}

    public AbstractProxyConnection (ConnectionInfo info, ConnectionInfo... listeners) 
	throws SQLException {
	this(info);
	for (ConnectionInfo i : listeners) addListener(i);}

    public boolean addListener (ConnectionInfo info) {
	return this.listeners.add(info);}

    public PreparedStatement prepareStatement (String sql) 
	throws SQLException {
	return delegate.getConnection().prepareStatement(sql);}

    public PreparedStatement prepareStatement (String sql, String[] columnNames) 
	throws SQLException {
	return delegate.getConnection().prepareStatement(sql, columnNames);}

    public PreparedStatement prepareStatement (String sql, int autoGeneratedKeys) 
	throws SQLException {
	return delegate.getConnection().prepareStatement(sql, autoGeneratedKeys);}

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return delegate.getConnection().prepareStatement(sql, resultSetType, resultSetConcurrency);}

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return delegate.getConnection().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);}

    public PreparedStatement prepareStatement (String sql, int[] columnIndexes) 
	throws SQLException {
	return delegate.getConnection().prepareStatement(sql, columnIndexes);}

    public CallableStatement prepareCall (String sql) 
	throws SQLException {
	return delegate.getConnection().prepareCall(sql);}

    public CallableStatement prepareCall (String sql, int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return delegate.getConnection().prepareCall(sql, resultSetType, resultSetConcurrency);}

    public CallableStatement prepareCall (String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return delegate.getConnection().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);}

    public Statement createStatement () 
	throws SQLException {
	return new AbstractProxyStatement(this, delegate.getConnection().createStatement(), this.delegate.getUrl(), this.delegate.getInfo()){};}

    public Statement createStatement (int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return new AbstractProxyStatement(this, delegate.getConnection().createStatement(resultSetType, resultSetConcurrency), this.delegate.getUrl(), this.delegate.getInfo()){};}

    public Statement createStatement (int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return new AbstractProxyStatement(this, delegate.getConnection().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability), this.delegate.getUrl(), this.delegate.getInfo()){};}}
