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
    private Driver driver;
    private String url;
    private Properties info;
    private List<Connection> delegates = new ArrayList<Connection>();

    protected Connection getDelegate() {return this.delegates.get(0);}

    public Array createArrayOf (String typeName, Object[] elements) throws SQLException {return delegates.get(0).createArrayOf(typeName, elements);}
    public Blob createBlob () throws SQLException {return delegates.get(0).createBlob();}
    public Clob createClob () throws SQLException {return delegates.get(0).createClob();}
    public DatabaseMetaData getMetaData () throws SQLException {return delegates.get(0).getMetaData();}
    public Map<String,Class<?>> getTypeMap () throws SQLException {return delegates.get(0).getTypeMap();}
    public NClob createNClob () throws SQLException {return delegates.get(0).createNClob();}
    public Properties getClientInfo () throws SQLException {return delegates.get(0).getClientInfo();}
    public SQLWarning getWarnings () throws SQLException {return delegates.get(0).getWarnings();}
    public SQLXML createSQLXML () throws SQLException {return delegates.get(0).createSQLXML();}
    public Savepoint setSavepoint () throws SQLException {return delegates.get(0).setSavepoint();}
    public Savepoint setSavepoint (String name) throws SQLException {return delegates.get(0).setSavepoint(name);}
    public String getCatalog () throws SQLException {return delegates.get(0).getCatalog();}
    public String getClientInfo (String name) throws SQLException {return delegates.get(0).getClientInfo(name);}
    public String getSchema () throws SQLException {return delegates.get(0).getSchema();}
    public String nativeSQL (String sql) throws SQLException {return delegates.get(0).nativeSQL(sql);}
    public Struct createStruct (String typeName, Object[] attributes) throws SQLException {return delegates.get(0).createStruct(typeName, attributes);}
    public boolean getAutoCommit () throws SQLException {return delegates.get(0).getAutoCommit();}
    public boolean isClosed () throws SQLException {return delegates.get(0).isClosed();}
    public boolean isReadOnly () throws SQLException {return delegates.get(0).isReadOnly();}
    public boolean isValid (int timeout) throws SQLException {return delegates.get(0).isValid(timeout);}
    public int getHoldability () throws SQLException {return delegates.get(0).getHoldability();}
    public int getNetworkTimeout () throws SQLException {return delegates.get(0).getNetworkTimeout();}
    public int getTransactionIsolation () throws SQLException {return delegates.get(0).getTransactionIsolation();}
    public void abort (Executor executor) throws SQLException {delegates.get(0).abort(executor);}
    public void clearWarnings () throws SQLException {delegates.get(0).clearWarnings();}
    public void close () throws SQLException {delegates.get(0).close();}
    public void commit () throws SQLException {delegates.get(0).commit();}
    public void releaseSavepoint (Savepoint savepoint) throws SQLException {delegates.get(0).releaseSavepoint(savepoint);}
    public void rollback () throws SQLException {delegates.get(0).rollback();}
    public void rollback (Savepoint savepoint) throws SQLException {delegates.get(0).rollback(savepoint);}
    public void setAutoCommit (boolean autoCommit) throws SQLException {delegates.get(0).setAutoCommit(autoCommit);}
    public void setCatalog (String catalog) throws SQLException {delegates.get(0).setCatalog(catalog);}
    public void setClientInfo (Properties properties) throws SQLClientInfoException {delegates.get(0).setClientInfo(properties);}
    public void setClientInfo (String name, String value) throws SQLClientInfoException {delegates.get(0).setClientInfo(name, value);}
    public void setHoldability (int holdability) throws SQLException {delegates.get(0).setHoldability(holdability);}
    public void setNetworkTimeout (Executor executor, int milliseconds) throws SQLException {delegates.get(0).setNetworkTimeout(executor, milliseconds);}
    public void setReadOnly (boolean readOnly) throws SQLException {delegates.get(0).setReadOnly(readOnly);}
    public void setSchema (String schema) throws SQLException {delegates.get(0).setSchema(schema);}
    public void setTransactionIsolation (int level) throws SQLException {delegates.get(0).setTransactionIsolation(level);}
    public void setTypeMap (Map<String,Class<?>> map) throws SQLException {delegates.get(0).setTypeMap(map);}
    public boolean isWrapperFor (Class<?> iface) {return iface.isInstance(this.delegates.get(0));}
    public <T> T unwrap(Class<T> iface) throws SQLException {return isWrapperFor(iface) ? iface.cast(this.delegates.get(0)) : null;}    

    public AbstractProxyConnection (Connection delegate, Driver driver, String url, Properties info)
	throws SQLException {
	this.delegates.add(delegate);
	this.driver = driver;
	this.url = url;
	this.info = info;}

    public PreparedStatement prepareStatement (String sql) 
	throws SQLException {
	return delegates.get(0).prepareStatement(sql);}

    public PreparedStatement prepareStatement (String sql, String[] columnNames) 
	throws SQLException {
	return delegates.get(0).prepareStatement(sql, columnNames);}

    public PreparedStatement prepareStatement (String sql, int autoGeneratedKeys) 
	throws SQLException {
	return delegates.get(0).prepareStatement(sql, autoGeneratedKeys);}

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return delegates.get(0).prepareStatement(sql, resultSetType, resultSetConcurrency);}

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return delegates.get(0).prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);}

    public PreparedStatement prepareStatement (String sql, int[] columnIndexes) 
	throws SQLException {
	return delegates.get(0).prepareStatement(sql, columnIndexes);}

    public CallableStatement prepareCall (String sql) 
	throws SQLException {
	return delegates.get(0).prepareCall(sql);}

    public CallableStatement prepareCall (String sql, int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return delegates.get(0).prepareCall(sql, resultSetType, resultSetConcurrency);}

    public CallableStatement prepareCall (String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return delegates.get(0).prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);}

    public Statement createStatement () 
	throws SQLException {
	return new AbstractProxyStatement(delegates.get(0).createStatement(), this){};}

    public Statement createStatement (int resultSetType, int resultSetConcurrency) 
	throws SQLException {
	return new AbstractProxyStatement(delegates.get(0).createStatement(resultSetType, resultSetConcurrency), this){};}

    public Statement createStatement (int resultSetType, int resultSetConcurrency, int resultSetHoldability) 
	throws SQLException {
	return new AbstractProxyStatement(delegates.get(0).createStatement(resultSetType, resultSetConcurrency, resultSetHoldability), this){};}}
