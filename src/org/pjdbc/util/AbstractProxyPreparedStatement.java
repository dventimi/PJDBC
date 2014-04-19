package org.pjdbc.util;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.Driver;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

@SuppressWarnings("deprecation")
public abstract class AbstractProxyPreparedStatement extends AbstractProxyStatement implements PreparedStatement {
    private Connection conn;
    private PreparedStatement delegate;
    private List<PreparedStatement> delegates = new ArrayList<PreparedStatement>();

    protected PreparedStatement getDelegate() {return this.delegate;}

    public AbstractProxyPreparedStatement (PreparedStatement delegate, Connection conn) throws SQLException {
	super(delegate, conn);
	this.delegates.add(delegate);
	this.conn = conn;}

    public AbstractProxyPreparedStatement (PreparedStatement delegate, Connection conn, List<PreparedStatement> delegates) throws SQLException {
	this(delegate, conn);
	for (PreparedStatement s : delegates) addDelegate(s);}

    public AbstractProxyPreparedStatement (PreparedStatement delegate, Connection conn, PreparedStatement... delegates) throws SQLException {
	this(delegate, conn);
	for (PreparedStatement s : delegates) addDelegate(s);}

    public void addBatch () throws SQLException {
	for (PreparedStatement s : delegates) s.addBatch();}
    public void clearParameters () throws SQLException {
	for (PreparedStatement s : delegates) s.clearParameters();}
    public boolean execute () throws SQLException {
	return delegates.get(0).execute();}
    public ResultSet executeQuery () throws SQLException {
	return delegates.get(0).executeQuery();}
    public int executeUpdate () throws SQLException {
	return delegates.get(0).executeUpdate();}
    public ResultSetMetaData getMetaData () throws SQLException {
	return delegates.get(0).getMetaData();}
    public ParameterMetaData getParameterMetaData () throws SQLException {
	return delegates.get(0).getParameterMetaData();}
    public void setArray (int parameterIndex, Array x) throws SQLException {
	for (PreparedStatement s : delegates) s.setArray(parameterIndex, x);}
    public void setAsciiStream (int parameterIndex, InputStream x) throws SQLException {
	for (PreparedStatement s : delegates) s.setAsciiStream(parameterIndex, x);}
    public void setAsciiStream (int parameterIndex, InputStream x, int length) throws SQLException {
	for (PreparedStatement s : delegates) s.setAsciiStream(parameterIndex, x, length);}
    public void setAsciiStream (int parameterIndex, InputStream x, long length) throws SQLException {
	for (PreparedStatement s : delegates) s.setAsciiStream(parameterIndex, x, length);}
    public void setBigDecimal (int parameterIndex, BigDecimal x) throws SQLException {
	for (PreparedStatement s : delegates) s.setBigDecimal(parameterIndex, x);}
    public void setBinaryStream (int parameterIndex, InputStream x) throws SQLException {
	for (PreparedStatement s : delegates) s.setBinaryStream(parameterIndex, x);}
    public void setBinaryStream (int parameterIndex, InputStream x, int length) throws SQLException {
	for (PreparedStatement s : delegates) s.setBinaryStream(parameterIndex, x, length);}
    public void setBinaryStream (int parameterIndex, InputStream x, long length) throws SQLException {
	for (PreparedStatement s : delegates) s.setBinaryStream(parameterIndex, x, length);}
    public void setBlob (int parameterIndex, Blob x) throws SQLException {
	for (PreparedStatement s : delegates) s.setBlob(parameterIndex, x);}
    public void setBlob (int parameterIndex, InputStream inputStream) throws SQLException {
	for (PreparedStatement s : delegates) s.setBlob(parameterIndex, inputStream);}
    public void setBlob (int parameterIndex, InputStream inputStream, long length) throws SQLException {
	for (PreparedStatement s : delegates) s.setBlob(parameterIndex, inputStream, length);}
    public void setBoolean (int parameterIndex, boolean x) throws SQLException {
	for (PreparedStatement s : delegates) s.setBoolean(parameterIndex, x);}
    public void setByte (int parameterIndex, byte x) throws SQLException {
	for (PreparedStatement s : delegates) s.setByte(parameterIndex, x);}
    public void setBytes (int parameterIndex, byte[] x) throws SQLException {
	for (PreparedStatement s : delegates) s.setBytes(parameterIndex, x);}
    public void setCharacterStream (int parameterIndex, Reader reader) throws SQLException {
	for (PreparedStatement s : delegates) s.setCharacterStream(parameterIndex, reader);}
    public void setCharacterStream (int parameterIndex, Reader reader, int length) throws SQLException {
	for (PreparedStatement s : delegates) s.setCharacterStream(parameterIndex, reader, length);}
    public void setCharacterStream (int parameterIndex, Reader reader, long length) throws SQLException {
	for (PreparedStatement s : delegates) s.setCharacterStream(parameterIndex, reader, length);}
    public void setClob (int parameterIndex, Clob x) throws SQLException {
	for (PreparedStatement s : delegates) s.setClob(parameterIndex, x);}
    public void setClob (int parameterIndex, Reader reader) throws SQLException {
	for (PreparedStatement s : delegates) s.setClob(parameterIndex, reader);}
    public void setClob (int parameterIndex, Reader reader, long length) throws SQLException {
	for (PreparedStatement s : delegates) s.setClob(parameterIndex, reader, length);}
    public void setDate (int parameterIndex, Date x) throws SQLException {
	for (PreparedStatement s : delegates) s.setDate(parameterIndex, x);}
    public void setDate (int parameterIndex, Date x, Calendar cal) throws SQLException {
	for (PreparedStatement s : delegates) s.setDate(parameterIndex, x, cal);}
    public void setDouble (int parameterIndex, double x) throws SQLException {
	for (PreparedStatement s : delegates) s.setDouble(parameterIndex, x);}
    public void setFloat (int parameterIndex, float x) throws SQLException {
	for (PreparedStatement s : delegates) s.setFloat(parameterIndex, x);}
    public void setInt (int parameterIndex, int x) throws SQLException {
	for (PreparedStatement s : delegates) s.setInt(parameterIndex, x);}
    public void setLong (int parameterIndex, long x) throws SQLException {
	for (PreparedStatement s : delegates) s.setLong(parameterIndex, x);}
    public void setNCharacterStream (int parameterIndex, Reader value) throws SQLException {
	for (PreparedStatement s : delegates) s.setNCharacterStream(parameterIndex, value);}
    public void setNCharacterStream (int parameterIndex, Reader value, long length) throws SQLException {
	for (PreparedStatement s : delegates) s.setNCharacterStream(parameterIndex, value, length);}
    public void setNClob (int parameterIndex, NClob value) throws SQLException {
	for (PreparedStatement s : delegates) s.setNClob(parameterIndex, value);}
    public void setNClob (int parameterIndex, Reader reader) throws SQLException {
	for (PreparedStatement s : delegates) s.setNClob(parameterIndex, reader);}
    public void setNClob (int parameterIndex, Reader reader, long length) throws SQLException {
	for (PreparedStatement s : delegates) s.setNClob(parameterIndex, reader, length);}
    public void setNString (int parameterIndex, String value) throws SQLException {
	for (PreparedStatement s : delegates) s.setNString(parameterIndex, value);}
    public void setNull (int parameterIndex, int sqlType) throws SQLException {
	for (PreparedStatement s : delegates) s.setNull(parameterIndex, sqlType);}
    public void setNull (int parameterIndex, int sqlType, String typeName) throws SQLException {
	for (PreparedStatement s : delegates) s.setNull(parameterIndex, sqlType, typeName);}
    public void setObject (int parameterIndex, Object x) throws SQLException {
	for (PreparedStatement s : delegates) s.setObject(parameterIndex, x);}
    public void setObject (int parameterIndex, Object x, int targetSqlType) throws SQLException {
	for (PreparedStatement s : delegates) s.setObject(parameterIndex, x, targetSqlType);}
    public void setObject (int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
	for (PreparedStatement s : delegates) s.setObject(parameterIndex, x, targetSqlType, scaleOrLength);}
    public void setRef (int parameterIndex, Ref x) throws SQLException {
	for (PreparedStatement s : delegates) s.setRef(parameterIndex, x);}
    public void setRowId (int parameterIndex, RowId x) throws SQLException {
	for (PreparedStatement s : delegates) s.setRowId(parameterIndex, x);}
    public void setShort (int parameterIndex, short x) throws SQLException {
	for (PreparedStatement s : delegates) s.setShort(parameterIndex, x);}
    public void setSQLXML (int parameterIndex, SQLXML xmlObject) throws SQLException {
	for (PreparedStatement s : delegates) s.setSQLXML(parameterIndex, xmlObject);}
    public void setString (int parameterIndex, String x) throws SQLException {
	for (PreparedStatement s : delegates) s.setString(parameterIndex, x);}
    public void setTime (int parameterIndex, Time x) throws SQLException {
	for (PreparedStatement s : delegates) s.setTime(parameterIndex, x);}
    public void setTime (int parameterIndex, Time x, Calendar cal) throws SQLException {
	for (PreparedStatement s : delegates) s.setTime(parameterIndex, x, cal);}
    public void setTimestamp (int parameterIndex, Timestamp x) throws SQLException {
	for (PreparedStatement s : delegates) s.setTimestamp(parameterIndex, x);}
    public void setTimestamp (int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
	for (PreparedStatement s : delegates) s.setTimestamp(parameterIndex, x, cal);}
    public void setUnicodeStream (int parameterIndex, InputStream x, int length) throws SQLException {
	for (PreparedStatement s : delegates) s.setUnicodeStream(parameterIndex, x, length);}
    public void setURL (int parameterIndex, URL x) throws SQLException {
	for (PreparedStatement s : delegates) s.setURL(parameterIndex, x);}}




