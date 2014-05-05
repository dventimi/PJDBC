package org.pjdbc.proxy;

import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import org.pjdbc.util.*;

public abstract class AbstractProxyConnection implements Connection {
    private Driver driver;
    private String url;
    private Properties info;
    private List<Connection> delegates = new ArrayList<Connection>();

    protected Connection getDelegate() {return this.delegates.get(0);}

    protected List<Connection> getListeners() {return this.delegates.subList(1, this.delegates.size());}

    public Array createArrayOf (String typeName, Object[] elements) throws SQLException {return delegates.get(0).createArrayOf(typeName, elements);}
    public Blob createBlob () throws SQLException {return getDelegate().createBlob();}
    public Clob createClob () throws SQLException {return getDelegate().createClob();}
    public DatabaseMetaData getMetaData () throws SQLException {return getDelegate().getMetaData();}
    public Map<String, Class<?>> getTypeMap () throws SQLException {return getDelegate().getTypeMap();}
    public NClob createNClob () throws SQLException {return getDelegate().createNClob();}
    public Properties getClientInfo () throws SQLException {return getDelegate().getClientInfo();}
    public SQLWarning getWarnings () throws SQLException {return getDelegate().getWarnings();}
    public SQLXML createSQLXML () throws SQLException {return getDelegate().createSQLXML();}
    public Savepoint setSavepoint () throws SQLException {return getDelegate().setSavepoint();}
    public Savepoint setSavepoint (String name) throws SQLException {return getDelegate().setSavepoint(name);}
    public String getCatalog () throws SQLException {return getDelegate().getCatalog();}
    public String getClientInfo (String name) throws SQLException {return getDelegate().getClientInfo(name);}
    public String getSchema () throws SQLException {return getDelegate().getSchema();}
    public String nativeSQL (String sql) throws SQLException {return getDelegate().nativeSQL(sql);}
    public Struct createStruct (String typeName, Object[] attributes) throws SQLException {return getDelegate().createStruct(typeName, attributes);}
    public boolean getAutoCommit () throws SQLException {return getDelegate().getAutoCommit();}
    public boolean isClosed () throws SQLException {return getDelegate().isClosed();}
    public boolean isReadOnly () throws SQLException {return getDelegate().isReadOnly();}
    public boolean isValid (int timeout) throws SQLException {return getDelegate().isValid(timeout);}
    public int getHoldability () throws SQLException {return getDelegate().getHoldability();}
    public int getNetworkTimeout () throws SQLException {return getDelegate().getNetworkTimeout();}
    public int getTransactionIsolation () throws SQLException {return getDelegate().getTransactionIsolation();}
    public void abort (Executor executor) throws SQLException {for (Connection d : delegates) d.abort(executor);}
    public void clearWarnings () throws SQLException {for (Connection d : delegates) d.clearWarnings();}
    public void close () throws SQLException {for (Connection d : delegates) d.close();}
    public void commit () throws SQLException {for (Connection d : delegates) d.commit();}
    public void releaseSavepoint (Savepoint savepoint) throws SQLException {for (Connection d : delegates) d.releaseSavepoint(savepoint);}
    public void rollback () throws SQLException {for (Connection d : delegates) d.rollback();}
    public void rollback (Savepoint savepoint) throws SQLException {for (Connection d : delegates) d.rollback(savepoint);}
    public void setAutoCommit (boolean autoCommit) throws SQLException {for (Connection d : delegates) d.setAutoCommit(autoCommit);}
    public void setCatalog (String catalog) throws SQLException {for (Connection d : delegates) d.setCatalog(catalog);}
    public void setClientInfo (Properties properties) throws SQLClientInfoException {for (Connection d : delegates) d.setClientInfo(properties);}
    public void setClientInfo (String name, String value) throws SQLClientInfoException {for (Connection d : delegates) d.setClientInfo(name, value);}
    public void setHoldability (int holdability) throws SQLException {for (Connection d : delegates) d.setHoldability(holdability);}
    public void setNetworkTimeout (Executor executor, int milliseconds) throws SQLException {for (Connection d : delegates) d.setNetworkTimeout(executor, milliseconds);}
    public void setReadOnly (boolean readOnly) throws SQLException {for (Connection d : delegates) d.setReadOnly(readOnly);}
    public void setSchema (String schema) throws SQLException {for (Connection d : delegates) d.setSchema(schema);}
    public void setTransactionIsolation (int level) throws SQLException {for (Connection d : delegates) d.setTransactionIsolation(level);}
    public void setTypeMap (Map<String,Class<?>> map) throws SQLException {for (Connection d : delegates) d.setTypeMap(map);}
    public boolean isWrapperFor (Class<?> iface) {return iface.isInstance(this.delegates.get(0));}
    public <T> T unwrap(Class<T> iface) throws SQLException {return isWrapperFor(iface) ? iface.cast(this.delegates.get(0)) : null;}    

    public AbstractProxyConnection (Connection delegate, Driver driver, String url, Properties info) throws SQLException {
	this.delegates.add(delegate);
	this.driver = driver;
	this.url = url;
	this.info = info;}

    public AbstractProxyConnection (Connection delegate, Driver driver, String url, Properties info, List<Connection> delegates) throws SQLException {
	this(delegate, driver, url, info);
	for (Connection c : delegates) addDelegate(c);}

    public AbstractProxyConnection (Connection delegate, Driver driver, String url, Properties info, Connection... delegates) throws SQLException {
	this(delegate, driver, url, info);
	for (Connection c : delegates) addDelegate(c);}

    public boolean addDelegate (Connection conn) throws SQLException {
	return this.delegates.add(conn);}

    public boolean removeDelegate (Connection conn) throws SQLException {
	return this.delegates.remove(conn);}

    public PreparedStatement prepareStatement (String sql) throws SQLException {
	return getDelegate().prepareStatement(sql);}

    public PreparedStatement prepareStatement (String sql, String[] columnNames) throws SQLException {
	return getDelegate().prepareStatement(sql, columnNames);}

    public PreparedStatement prepareStatement (String sql, int autoGeneratedKeys) throws SQLException {
	return getDelegate().prepareStatement(sql, autoGeneratedKeys);}

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
	return getDelegate().prepareStatement(sql, resultSetType, resultSetConcurrency);}

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
	return getDelegate().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);}

    public PreparedStatement prepareStatement (String sql, int[] columnIndexes) throws SQLException {
	return getDelegate().prepareStatement(sql, columnIndexes);}

    public CallableStatement prepareCall (String sql) throws SQLException {
	return getDelegate().prepareCall(sql);}

    public CallableStatement prepareCall (String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
	return getDelegate().prepareCall(sql, resultSetType, resultSetConcurrency);}

    public CallableStatement prepareCall (String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
	return getDelegate().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);}

    public Statement createStatement () throws SQLException {
	List<Statement> stmts = new ArrayList<Statement>();
	for (Connection c : getListeners()) stmts.add(c.createStatement());
	return new AbstractProxyStatement(getDelegate().createStatement(), this, stmts){};}

    public Statement createStatement (int resultSetType, int resultSetConcurrency) throws SQLException {
	return new AbstractProxyStatement(getDelegate().createStatement(resultSetType, resultSetConcurrency), this){};}

    public Statement createStatement (int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
	return new AbstractProxyStatement(getDelegate().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability), this){};}}
