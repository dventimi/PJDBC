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
public abstract class AbstractProxyCallableStatement extends AbstractProxyPreparedStatement implements CallableStatement {
    private Connection conn;
    private CallableStatement delegate;
    private List<CallableStatement> delegates = new ArrayList<CallableStatement>();

    protected CallableStatement getDelegate() {return this.delegate;}

    public AbstractProxyCallableStatement (CallableStatement delegate, Connection conn)	throws SQLException {
	super(delegate, conn);
	this.delegates.add(delegate);
	this.conn = conn;}

    public AbstractProxyCallableStatement (CallableStatement delegate, Connection conn, List<CallableStatement> delegates) throws SQLException {
	this(delegate, conn);
	for (CallableStatement s : delegates) addDelegate(s);}

    public AbstractProxyCallableStatement (CallableStatement delegate, Connection conn, CallableStatement... delegates) throws SQLException {
	this(delegate, conn);
	for (CallableStatement s : delegates) addDelegate(s);}

    public <T> T getObject (String parameterName, Class<T> type) throws SQLException {
	return delegates.get(0).getObject(parameterName, type);}
    public <T> T getObject (int parameterIndex, Class<T> type) throws SQLException {
	return delegates.get(0).getObject(parameterIndex, type);}
    public Array getArray (String parameterName) throws SQLException {
	return delegates.get(0).getArray(parameterName);}
    public Array getArray (int parameterIndex) throws SQLException {
	return delegates.get(0).getArray(parameterIndex);}
    public BigDecimal getBigDecimal (String parameterName) throws SQLException {
	return delegates.get(0).getBigDecimal(parameterName);}
    public BigDecimal getBigDecimal (int parameterIndex) throws SQLException {
	return delegates.get(0).getBigDecimal(parameterIndex);}
    public BigDecimal getBigDecimal (int parameterIndex, int scale) throws SQLException {
	return delegates.get(0).getBigDecimal(parameterIndex, scale);}
    public Blob getBlob (String parameterName) throws SQLException {
	return delegates.get(0).getBlob(parameterName);}
    public Blob getBlob (int parameterIndex) throws SQLException {
	return delegates.get(0).getBlob(parameterIndex);}
    public Clob getClob (String parameterName) throws SQLException {
	return delegates.get(0).getClob(parameterName);}
    public Clob getClob (int parameterIndex) throws SQLException {
	return delegates.get(0).getClob(parameterIndex);}
    public Date getDate (String parameterName) throws SQLException {
	return delegates.get(0).getDate(parameterName);}
    public Date getDate (String parameterName, Calendar cal) throws SQLException {
	return delegates.get(0).getDate(parameterName, cal);}
    public Date getDate (int parameterIndex) throws SQLException {
	return delegates.get(0).getDate(parameterIndex);}
    public Date getDate (int parameterIndex, Calendar cal) throws SQLException {
	return delegates.get(0).getDate(parameterIndex, cal);}
    public NClob getNClob (String parameterName) throws SQLException {
	return delegates.get(0).getNClob(parameterName);}
    public NClob getNClob (int parameterIndex) throws SQLException {
	return delegates.get(0).getNClob(parameterIndex);}
    public Object getObject (String parameterName) throws SQLException {
	return delegates.get(0).getObject(parameterName);}
    public Object getObject (String parameterName, Map<String,Class<?>> map) throws SQLException {
	return delegates.get(0).getObject(parameterName, map);}
    public Object getObject (int parameterIndex) throws SQLException {
	return delegates.get(0).getObject(parameterIndex);}
    public Object getObject (int parameterIndex, Map<String,Class<?>> map) throws SQLException {
	return delegates.get(0).getObject(parameterIndex, map);}
    public Reader getCharacterStream (String parameterName) throws SQLException {
	return delegates.get(0).getCharacterStream(parameterName);}
    public Reader getCharacterStream (int parameterIndex) throws SQLException {
	return delegates.get(0).getCharacterStream(parameterIndex);}
    public Reader getNCharacterStream (String parameterName) throws SQLException {
	return delegates.get(0).getNCharacterStream(parameterName);}
    public Reader getNCharacterStream (int parameterIndex) throws SQLException {
	return delegates.get(0).getNCharacterStream(parameterIndex);}
    public Ref getRef (String parameterName) throws SQLException {
	return delegates.get(0).getRef(parameterName);}
    public Ref getRef (int parameterIndex) throws SQLException {
	return delegates.get(0).getRef(parameterIndex);}
    public RowId getRowId (String parameterName) throws SQLException {
	return delegates.get(0).getRowId(parameterName);}
    public RowId getRowId (int parameterIndex) throws SQLException {
	return delegates.get(0).getRowId(parameterIndex);}
    public SQLXML getSQLXML (String parameterName) throws SQLException {
	return delegates.get(0).getSQLXML(parameterName);}
    public SQLXML getSQLXML (int parameterIndex) throws SQLException {
	return delegates.get(0).getSQLXML(parameterIndex);}
    public String getNString (String parameterName) throws SQLException {
	return delegates.get(0).getNString(parameterName);}
    public String getNString (int parameterIndex) throws SQLException {
	return delegates.get(0).getNString(parameterIndex);}
    public String getString (String parameterName) throws SQLException {
	return delegates.get(0).getString(parameterName);}
    public String getString (int parameterIndex) throws SQLException {
	return delegates.get(0).getString(parameterIndex);}
    public Time getTime (String parameterName) throws SQLException {
	return delegates.get(0).getTime(parameterName);}
    public Time getTime (String parameterName, Calendar cal) throws SQLException {
	return delegates.get(0).getTime(parameterName, cal);}
    public Time getTime (int parameterIndex) throws SQLException {
	return delegates.get(0).getTime(parameterIndex);}
    public Time getTime (int parameterIndex, Calendar cal) throws SQLException {
	return delegates.get(0).getTime(parameterIndex, cal);}
    public Timestamp getTimestamp (String parameterName) throws SQLException {
	return delegates.get(0).getTimestamp(parameterName);}
    public Timestamp getTimestamp (String parameterName, Calendar cal) throws SQLException {
	return delegates.get(0).getTimestamp(parameterName, cal);}
    public Timestamp getTimestamp (int parameterIndex) throws SQLException {
	return delegates.get(0).getTimestamp(parameterIndex);}
    public Timestamp getTimestamp (int parameterIndex, Calendar cal) throws SQLException {
	return delegates.get(0).getTimestamp(parameterIndex, cal);}
    public URL getURL (String parameterName) throws SQLException {
	return delegates.get(0).getURL(parameterName);}
    public URL getURL (int parameterIndex) throws SQLException {
	return delegates.get(0).getURL(parameterIndex);}
    public boolean getBoolean (String parameterName) throws SQLException {
	return delegates.get(0).getBoolean(parameterName);}
    public boolean getBoolean (int parameterIndex) throws SQLException {
	return delegates.get(0).getBoolean(parameterIndex);}
    public boolean wasNull () throws SQLException {
	return delegates.get(0).wasNull();}
    public byte getByte (String parameterName) throws SQLException {
	return delegates.get(0).getByte(parameterName);}
    public byte getByte (int parameterIndex) throws SQLException {
	return delegates.get(0).getByte(parameterIndex);}
    public byte[] getBytes (String parameterName) throws SQLException {
	return delegates.get(0).getBytes(parameterName);}
    public byte[] getBytes (int parameterIndex) throws SQLException {
	return delegates.get(0).getBytes(parameterIndex);}
    public double getDouble (String parameterName) throws SQLException {
	return delegates.get(0).getDouble(parameterName);}
    public double getDouble (int parameterIndex) throws SQLException {
	return delegates.get(0).getDouble(parameterIndex);}
    public float getFloat (String parameterName) throws SQLException {
	return delegates.get(0).getFloat(parameterName);}
    public float getFloat (int parameterIndex) throws SQLException {
	return delegates.get(0).getFloat(parameterIndex);}
    public int getInt (String parameterName) throws SQLException {
	return delegates.get(0).getInt(parameterName);}
    public int getInt (int parameterIndex) throws SQLException {
	return delegates.get(0).getInt(parameterIndex);}
    public long getLong (String parameterName) throws SQLException {
	return delegates.get(0).getLong(parameterName);}
    public long getLong (int parameterIndex) throws SQLException {
	return delegates.get(0).getLong(parameterIndex);}
    public short getShort (String parameterName) throws SQLException {
	return delegates.get(0).getShort(parameterName);}
    public short getShort (int parameterIndex) throws SQLException {
	return delegates.get(0).getShort(parameterIndex);}
    public void registerOutParameter (String parameterName, int sqlType) throws SQLException {
	for (CallableStatement s : delegates) s.registerOutParameter(parameterName, sqlType);}
    public void registerOutParameter (String parameterName, int sqlType, String typeName) throws SQLException {
	for (CallableStatement s : delegates) s.registerOutParameter(parameterName, sqlType, typeName);}
    public void registerOutParameter (String parameterName, int sqlType, int scale) throws SQLException {
	for (CallableStatement s : delegates) s.registerOutParameter(parameterName, sqlType, scale);}
    public void registerOutParameter (int parameterIndex, int sqlType) throws SQLException {
	for (CallableStatement s : delegates) s.registerOutParameter(parameterIndex, sqlType);}
    public void registerOutParameter (int parameterIndex, int sqlType, String typeName) throws SQLException {
	for (CallableStatement s : delegates) s.registerOutParameter(parameterIndex, sqlType, typeName);}
    public void registerOutParameter (int parameterIndex, int sqlType, int scale) throws SQLException {
	for (CallableStatement s : delegates) s.registerOutParameter(parameterIndex, sqlType, scale);}
    public void setAsciiStream (String parameterName, InputStream x) throws SQLException {
	for (CallableStatement s : delegates) s.setAsciiStream(parameterName, x);}
    public void setAsciiStream (String parameterName, InputStream x, int length) throws SQLException {
	for (CallableStatement s : delegates) s.setAsciiStream(parameterName, x, length);}
    public void setAsciiStream (String parameterName, InputStream x, long length) throws SQLException {
	for (CallableStatement s : delegates) s.setAsciiStream(parameterName, x, length);}
    public void setBigDecimal (String parameterName, BigDecimal x) throws SQLException {
	for (CallableStatement s : delegates) s.setBigDecimal(parameterName, x);}
    public void setBinaryStream (String parameterName, InputStream x) throws SQLException {
	for (CallableStatement s : delegates) s.setBinaryStream(parameterName, x);}
    public void setBinaryStream (String parameterName, InputStream x, int length) throws SQLException {
	for (CallableStatement s : delegates) s.setBinaryStream(parameterName, x, length);}
    public void setBinaryStream (String parameterName, InputStream x, long length) throws SQLException {
	for (CallableStatement s : delegates) s.setBinaryStream(parameterName, x, length);}
    public void setBlob (String parameterName, Blob x) throws SQLException {
	for (CallableStatement s : delegates) s.setBlob(parameterName, x);}
    public void setBlob (String parameterName, InputStream inputStream) throws SQLException {
	for (CallableStatement s : delegates) s.setBlob(parameterName, inputStream);}
    public void setBlob (String parameterName, InputStream inputStream, long length) throws SQLException {
	for (CallableStatement s : delegates) s.setBlob(parameterName, inputStream, length);}
    public void setBoolean (String parameterName, boolean x) throws SQLException {
	for (CallableStatement s : delegates) s.setBoolean(parameterName, x);}
    public void setByte (String parameterName, byte x) throws SQLException {
	for (CallableStatement s : delegates) s.setByte(parameterName, x);}
    public void setBytes (String parameterName, byte[] x) throws SQLException {
	for (CallableStatement s : delegates) s.setBytes(parameterName, x);}
    public void setCharacterStream (String parameterName, Reader reader) throws SQLException {
	for (CallableStatement s : delegates) s.setCharacterStream(parameterName, reader);}
    public void setCharacterStream (String parameterName, Reader reader, int length) throws SQLException {
	for (CallableStatement s : delegates) s.setCharacterStream(parameterName, reader, length);}
    public void setCharacterStream (String parameterName, Reader reader, long length) throws SQLException {
	for (CallableStatement s : delegates) s.setCharacterStream(parameterName, reader, length);}
    public void setClob (String parameterName, Clob x) throws SQLException {
	for (CallableStatement s : delegates) s.setClob(parameterName, x);}
    public void setClob (String parameterName, Reader reader) throws SQLException {
	for (CallableStatement s : delegates) s.setClob(parameterName, reader);}
    public void setClob (String parameterName, Reader reader, long length) throws SQLException {
	for (CallableStatement s : delegates) s.setClob(parameterName, reader, length);}
    public void setDate (String parameterName, Date x) throws SQLException {
	for (CallableStatement s : delegates) s.setDate(parameterName, x);}
    public void setDate (String parameterName, Date x, Calendar cal) throws SQLException {
	for (CallableStatement s : delegates) s.setDate(parameterName, x, cal);}
    public void setDouble (String parameterName, double x) throws SQLException {
	for (CallableStatement s : delegates) s.setDouble(parameterName, x);}
    public void setFloat (String parameterName, float x) throws SQLException {
	for (CallableStatement s : delegates) s.setFloat(parameterName, x);}
    public void setInt (String parameterName, int x) throws SQLException {
	for (CallableStatement s : delegates) s.setInt(parameterName, x);}
    public void setLong (String parameterName, long x) throws SQLException {
	for (CallableStatement s : delegates) s.setLong(parameterName, x);}
    public void setNCharacterStream (String parameterName, Reader value) throws SQLException {
	for (CallableStatement s : delegates) s.setNCharacterStream(parameterName, value);}
    public void setNCharacterStream (String parameterName, Reader value, long length) throws SQLException {
	for (CallableStatement s : delegates) s.setNCharacterStream(parameterName, value, length);}
    public void setNClob (String parameterName, NClob value) throws SQLException {
	for (CallableStatement s : delegates) s.setNClob(parameterName, value);}
    public void setNClob (String parameterName, Reader reader) throws SQLException {
	for (CallableStatement s : delegates) s.setNClob(parameterName, reader);}
    public void setNClob (String parameterName, Reader reader, long length) throws SQLException {
	for (CallableStatement s : delegates) s.setNClob(parameterName, reader, length);}
    public void setNString (String parameterName, String value) throws SQLException {
	for (CallableStatement s : delegates) s.setNString(parameterName, value);}
    public void setNull (String parameterName, int sqlType) throws SQLException {
	for (CallableStatement s : delegates) s.setNull(parameterName, sqlType);}
    public void setNull (String parameterName, int sqlType, String typeName) throws SQLException {
	for (CallableStatement s : delegates) s.setNull(parameterName, sqlType, typeName);}
    public void setObject (String parameterName, Object x) throws SQLException {
	for (CallableStatement s : delegates) s.setObject(parameterName, x);}
    public void setObject (String parameterName, Object x, int targetSqlType) throws SQLException {
	for (CallableStatement s : delegates) s.setObject(parameterName, x, targetSqlType);}
    public void setObject (String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
	for (CallableStatement s : delegates) s.setObject(parameterName, x, targetSqlType, scale);}
    public void setRowId (String parameterName, RowId x) throws SQLException {
	for (CallableStatement s : delegates) s.setRowId(parameterName, x);}
    public void setSQLXML (String parameterName, SQLXML xmlObject) throws SQLException {
	for (CallableStatement s : delegates) s.setSQLXML(parameterName, xmlObject);}
    public void setShort (String parameterName, short x) throws SQLException {
	for (CallableStatement s : delegates) s.setShort(parameterName, x);}
    public void setString (String parameterName, String x) throws SQLException {
	for (CallableStatement s : delegates) s.setString(parameterName, x);}
    public void setTime (String parameterName, Time x) throws SQLException {
	for (CallableStatement s : delegates) s.setTime(parameterName, x);}
    public void setTime (String parameterName, Time x, Calendar cal) throws SQLException {
	for (CallableStatement s : delegates) s.setTime(parameterName, x, cal);}
    public void setTimestamp (String parameterName, Timestamp x) throws SQLException {
	for (CallableStatement s : delegates) s.setTimestamp(parameterName, x);}
    public void setTimestamp (String parameterName, Timestamp x, Calendar cal) throws SQLException {
	for (CallableStatement s : delegates) s.setTimestamp(parameterName, x, cal);}
    public void setURL (String parameterName, URL val) throws SQLException {
	for (CallableStatement s : delegates) s.setURL(parameterName, val);}}
