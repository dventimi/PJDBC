package org.pjdbc.util;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public abstract class AbstractProxyConnection implements Connection {
    protected Driver driver;
    protected String url;
    protected Properties info;
    protected Connection delegate;

    public Array createArrayOf (String typeName, Object[] elements) throws SQLException {return delegate.createArrayOf(typeName, elements);}
    public Blob createBlob () throws SQLException {return delegate.createBlob();}
    public Clob createClob () throws SQLException {return delegate.createClob();}
    public DatabaseMetaData getMetaData () throws SQLException {return delegate.getMetaData();}
    public Map<String,Class<?>> getTypeMap () throws SQLException {return delegate.getTypeMap();}
    public NClob createNClob () throws SQLException {return delegate.createNClob();}
    public Properties getClientInfo () throws SQLException {return delegate.getClientInfo();}
    public SQLWarning getWarnings () throws SQLException {return delegate.getWarnings();}
    public SQLXML createSQLXML () throws SQLException {return delegate.createSQLXML();}
    public Savepoint setSavepoint () throws SQLException {return delegate.setSavepoint();}
    public Savepoint setSavepoint (String name) throws SQLException {return delegate.setSavepoint(name);}
    public String getCatalog () throws SQLException {return delegate.getCatalog();}
    public String getClientInfo (String name) throws SQLException {return delegate.getClientInfo(name);}
    public String getSchema () throws SQLException {return delegate.getSchema();}
    public String nativeSQL (String sql) throws SQLException {return delegate.nativeSQL(sql);}
    public Struct createStruct (String typeName, Object[] attributes) throws SQLException {return delegate.createStruct(typeName, attributes);}
    public boolean getAutoCommit () throws SQLException {return delegate.getAutoCommit();}
    public boolean isClosed () throws SQLException {return delegate.isClosed();}
    public boolean isReadOnly () throws SQLException {return delegate.isReadOnly();}
    public boolean isValid (int timeout) throws SQLException {return delegate.isValid(timeout);}
    public int getHoldability () throws SQLException {return delegate.getHoldability();}
    public int getNetworkTimeout () throws SQLException {return delegate.getNetworkTimeout();}
    public int getTransactionIsolation () throws SQLException {return delegate.getTransactionIsolation();}
    public void abort (Executor executor) throws SQLException {delegate.abort(executor);}
    public void clearWarnings () throws SQLException {delegate.clearWarnings();}
    public void close () throws SQLException {delegate.close();}
    public void commit () throws SQLException {delegate.commit();}
    public void releaseSavepoint (Savepoint savepoint) throws SQLException {delegate.releaseSavepoint(savepoint);}
    public void rollback () throws SQLException {delegate.rollback();}
    public void rollback (Savepoint savepoint) throws SQLException {delegate.rollback(savepoint);}
    public void setAutoCommit (boolean autoCommit) throws SQLException {delegate.setAutoCommit(autoCommit);}
    public void setCatalog (String catalog) throws SQLException {delegate.setCatalog(catalog);}
    public void setClientInfo (Properties properties) throws SQLClientInfoException {delegate.setClientInfo(properties);}
    public void setClientInfo (String name, String value) throws SQLClientInfoException {delegate.setClientInfo(name, value);}
    public void setHoldability (int holdability) throws SQLException {delegate.setHoldability(holdability);}
    public void setNetworkTimeout (Executor executor, int milliseconds) throws SQLException {delegate.setNetworkTimeout(executor, milliseconds);}
    public void setReadOnly (boolean readOnly) throws SQLException {delegate.setReadOnly(readOnly);}
    public void setSchema (String schema) throws SQLException {delegate.setSchema(schema);}
    public void setTransactionIsolation (int level) throws SQLException {delegate.setTransactionIsolation(level);}
    public void setTypeMap (Map<String,Class<?>> map) throws SQLException {delegate.setTypeMap(map);}
    public boolean isWrapperFor (Class<?> iface) {return iface.isInstance(this.delegate);}
    public <T> T unwrap(Class<T> iface) throws SQLException {return isWrapperFor(iface) ? iface.cast(this.delegate) : null;}    

    public AbstractProxyConnection (Connection delegate, Driver driver, String url, Properties info)
	throws SQLException {
	this.delegate = delegate;
	this.driver = driver;
	this.url = url;
	this.info = info;}

    public PreparedStatement prepareStatement (String sql) 
	throws SQLException {
	return delegate.prepareStatement(sql);}

    public PreparedStatement prepareStatement (String sql, String[] columnNames) 
	throws SQLException {
	return delegate.prepareStatement(sql, columnNames);}

    public PreparedStatement prepareStatement (String sql, int autoGeneratedKeys) 
	throws SQLException {
	return delegate.prepareStatement(sql, autoGeneratedKeys);}

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return delegate.prepareStatement(sql, resultSetType, resultSetConcurrency);}

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return delegate.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);}

    public PreparedStatement prepareStatement (String sql, int[] columnIndexes) 
	throws SQLException {
	return delegate.prepareStatement(sql, columnIndexes);}

    public CallableStatement prepareCall (String sql) 
	throws SQLException {
	return delegate.prepareCall(sql);}

    public CallableStatement prepareCall (String sql, int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return delegate.prepareCall(sql, resultSetType, resultSetConcurrency);}

    public CallableStatement prepareCall (String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return delegate.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);}

    public Statement createStatement () 
	throws SQLException {
	return new AbstractProxyStatement(delegate.createStatement(), this){};}

    public Statement createStatement (int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return new AbstractProxyStatement(delegate.createStatement(resultSetType, resultSetConcurrency), this){};}

    public Statement createStatement (int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return new AbstractProxyStatement(delegate.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability), this){};}}
