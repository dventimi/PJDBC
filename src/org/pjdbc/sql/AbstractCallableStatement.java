package org.pjdbc.sql;

import java.io.*;
import java.math.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public abstract class AbstractCallableStatement extends AbstractPreparedStatement implements CallableStatement {

    // Data

    private CallableStatement d;

    // Constructors

    AbstractCallableStatement (Connection conn, CallableStatement stmt) throws SQLException {
	super(conn, stmt);
	this.d = stmt;}

    // CallableStatement API

    @Deprecated public BigDecimal getBigDecimal (int parameterIndex) throws SQLException {return d.getBigDecimal(parameterIndex);}
    @Deprecated public BigDecimal getBigDecimal (int parameterIndex, int scale) throws SQLException {return d.getBigDecimal(parameterIndex, scale);}
    public <T> T getObject (String parameterName, Class<T> type) throws SQLException {return d.getObject(parameterName, type);}
    public <T> T getObject (int parameterIndex, Class<T> type) throws SQLException {return d.getObject(parameterIndex, type);}
    public Array getArray (String parameterName) throws SQLException {return d.getArray(parameterName);}
    public Array getArray (int parameterIndex) throws SQLException {return d.getArray(parameterIndex);}
    public Blob getBlob (String parameterName) throws SQLException {return d.getBlob(parameterName);}
    public Blob getBlob (int parameterIndex) throws SQLException {return d.getBlob(parameterIndex);}
    public Clob getClob (String parameterName) throws SQLException {return d.getClob(parameterName);}
    public Clob getClob (int parameterIndex) throws SQLException {return d.getClob(parameterIndex);}
    public NClob getNClob (String parameterName) throws SQLException {return d.getNClob(parameterName);}
    public NClob getNClob (int parameterIndex) throws SQLException {return d.getNClob(parameterIndex);}
    public Object getObject (String parameterName) throws SQLException {return d.getObject(parameterName);}
    public Object getObject (String parameterName, Map<String,Class<?>> map) throws SQLException {return d.getObject(parameterName, map);}
    public Object getObject (int parameterIndex) throws SQLException {return d.getObject(parameterIndex);}
    public Object getObject (int parameterIndex, Map<String,Class<?>> map) throws SQLException {return d.getObject(parameterIndex, map);}
    public Reader getCharacterStream (String parameterName) throws SQLException {return d.getCharacterStream(parameterName);}
    public Reader getCharacterStream (int parameterIndex) throws SQLException {return d.getCharacterStream(parameterIndex);}
    public Reader getNCharacterStream (String parameterName) throws SQLException {return d.getNCharacterStream(parameterName);}
    public Reader getNCharacterStream (int parameterIndex) throws SQLException {return d.getNCharacterStream(parameterIndex);}
    public Ref getRef (String parameterName) throws SQLException {return d.getRef(parameterName);}
    public Ref getRef (int parameterIndex) throws SQLException {return d.getRef(parameterIndex);}
    public RowId getRowId (String parameterName) throws SQLException {return d.getRowId(parameterName);}
    public RowId getRowId (int parameterIndex) throws SQLException {return d.getRowId(parameterIndex);}
    public SQLXML getSQLXML (String parameterName) throws SQLException {return d.getSQLXML(parameterName);}
    public SQLXML getSQLXML (int parameterIndex) throws SQLException {return d.getSQLXML(parameterIndex);}
    public String getNString (String parameterName) throws SQLException {return d.getNString(parameterName);}
    public String getNString (int parameterIndex) throws SQLException {return d.getNString(parameterIndex);}
    public String getString (String parameterName) throws SQLException {return d.getString(parameterName);}
    public String getString (int parameterIndex) throws SQLException {return d.getString(parameterIndex);}
    public Time getTime (String parameterName) throws SQLException {return d.getTime(parameterName);}
    public Time getTime (String parameterName, Calendar cal) throws SQLException {return d.getTime(parameterName, cal);}
    public Time getTime (int parameterIndex) throws SQLException {return d.getTime(parameterIndex);}
    public Time getTime (int parameterIndex, Calendar cal) throws SQLException {return d.getTime(parameterIndex, cal);}
    public Timestamp getTimestamp (String parameterName) throws SQLException {return d.getTimestamp(parameterName);}
    public Timestamp getTimestamp (String parameterName, Calendar cal) throws SQLException {return d.getTimestamp(parameterName, cal);}
    public Timestamp getTimestamp (int parameterIndex) throws SQLException {return d.getTimestamp(parameterIndex);}
    public Timestamp getTimestamp (int parameterIndex, Calendar cal) throws SQLException {return d.getTimestamp(parameterIndex, cal);}
    public URL getURL (String parameterName) throws SQLException {return d.getURL(parameterName);}
    public URL getURL (int parameterIndex) throws SQLException {return d.getURL(parameterIndex);}
    public boolean getBoolean (String parameterName) throws SQLException {return d.getBoolean(parameterName);}
    public boolean getBoolean (int parameterIndex) throws SQLException {return d.getBoolean(parameterIndex);}
    public boolean wasNull () throws SQLException {return d.wasNull();}
    public byte getByte (String parameterName) throws SQLException {return d.getByte(parameterName);}
    public byte getByte (int parameterIndex) throws SQLException {return d.getByte(parameterIndex);}
    public byte[] getBytes (String parameterName) throws SQLException {return d.getBytes(parameterName);}
    public byte[] getBytes (int parameterIndex) throws SQLException {return d.getBytes(parameterIndex);}
    public double getDouble (String parameterName) throws SQLException {return d.getDouble(parameterName);}
    public double getDouble (int parameterIndex) throws SQLException {return d.getDouble(parameterIndex);}
    public float getFloat (String parameterName) throws SQLException {return d.getFloat(parameterName);}
    public float getFloat (int parameterIndex) throws SQLException {return d.getFloat(parameterIndex);}
    public int getInt (String parameterName) throws SQLException {return d.getInt(parameterName);}
    public int getInt (int parameterIndex) throws SQLException {return d.getInt(parameterIndex);}
    public java.sql.Date getDate (String parameterName) throws SQLException {return d.getDate(parameterName);}
    public java.sql.Date getDate (String parameterName, Calendar cal) throws SQLException {return d.getDate(parameterName, cal);}
    public java.sql.Date getDate (int parameterIndex) throws SQLException {return d.getDate(parameterIndex);}
    public java.sql.Date getDate (int parameterIndex, Calendar cal) throws SQLException {return d.getDate(parameterIndex, cal);}
    public long getLong (String parameterName) throws SQLException {return d.getLong(parameterName);}
    public long getLong (int parameterIndex) throws SQLException {return d.getLong(parameterIndex);}
    public short getShort (String parameterName) throws SQLException {return d.getShort(parameterName);}
    public short getShort (int parameterIndex) throws SQLException {return d.getShort(parameterIndex);}
    public void registerOutParameter (String parameterName, int sqlType) throws SQLException {d.registerOutParameter(parameterName, sqlType);}
    public void registerOutParameter (String parameterName, int sqlType, String typeName) throws SQLException {d.registerOutParameter(parameterName, sqlType, typeName);}
    public void registerOutParameter (String parameterName, int sqlType, int scale) throws SQLException {d.registerOutParameter(parameterName, sqlType, scale);}
    public void registerOutParameter (int parameterIndex, int sqlType) throws SQLException {d.registerOutParameter(parameterIndex, sqlType);}
    public void registerOutParameter (int parameterIndex, int sqlType, String typeName) throws SQLException {d.registerOutParameter(parameterIndex, sqlType, typeName);}
    public void registerOutParameter (int parameterIndex, int sqlType, int scale) throws SQLException {d.registerOutParameter(parameterIndex, sqlType, scale);}
    public void setAsciiStream (String parameterName, InputStream x) throws SQLException {d.setAsciiStream(parameterName, x);}
    public void setAsciiStream (String parameterName, InputStream x, int length) throws SQLException {d.setAsciiStream(parameterName, x, length);}
    public void setAsciiStream (String parameterName, InputStream x, long length) throws SQLException {d.setAsciiStream(parameterName, x, length);}
    public void setBigDecimal (String parameterName, BigDecimal x) throws SQLException {d.setBigDecimal(parameterName, x);}
    public void setBinaryStream (String parameterName, InputStream x) throws SQLException {d.setBinaryStream(parameterName, x);}
    public void setBinaryStream (String parameterName, InputStream x, int length) throws SQLException {d.setBinaryStream(parameterName, x, length);}
    public void setBinaryStream (String parameterName, InputStream x, long length) throws SQLException {d.setBinaryStream(parameterName, x, length);}
    public void setBlob (String parameterName, Blob x) throws SQLException {d.setBlob(parameterName, x);}
    public void setBlob (String parameterName, InputStream inputStream) throws SQLException {d.setBlob(parameterName, inputStream);}
    public void setBlob (String parameterName, InputStream inputStream, long length) throws SQLException {d.setBlob(parameterName, inputStream, length);}
    public void setBoolean (String parameterName, boolean x) throws SQLException {d.setBoolean(parameterName, x);}
    public void setByte (String parameterName, byte x) throws SQLException {d.setByte(parameterName, x);}
    public void setBytes (String parameterName, byte[] x) throws SQLException {d.setBytes(parameterName, x);}
    public void setCharacterStream (String parameterName, Reader reader) throws SQLException {d.setCharacterStream(parameterName, reader);}
    public void setCharacterStream (String parameterName, Reader reader, int length) throws SQLException {d.setCharacterStream(parameterName, reader, length);}
    public void setCharacterStream (String parameterName, Reader reader, long length) throws SQLException {d.setCharacterStream(parameterName, reader, length);}
    public void setClob (String parameterName, Clob x) throws SQLException {d.setClob(parameterName, x);}
    public void setClob (String parameterName, Reader reader) throws SQLException {d.setClob(parameterName, reader);}
    public void setClob (String parameterName, Reader reader, long length) throws SQLException {d.setClob(parameterName, reader, length);}
    public void setDate (String parameterName, java.sql.Date x) throws SQLException {d.setDate(parameterName, x);}
    public void setDate (String parameterName, java.sql.Date x, Calendar cal) throws SQLException {d.setDate(parameterName, x, cal);}
    public void setDouble (String parameterName, double x) throws SQLException {d.setDouble(parameterName, x);}
    public void setFloat (String parameterName, float x) throws SQLException {d.setFloat(parameterName, x);}
    public void setInt (String parameterName, int x) throws SQLException {d.setInt(parameterName, x);}
    public void setLong (String parameterName, long x) throws SQLException {d.setLong(parameterName, x);}
    public void setNCharacterStream (String parameterName, Reader value) throws SQLException {d.setNCharacterStream(parameterName, value);}
    public void setNCharacterStream (String parameterName, Reader value, long length) throws SQLException {d.setNCharacterStream(parameterName, value, length);}
    public void setNClob (String parameterName, NClob value) throws SQLException {d.setNClob(parameterName, value);}
    public void setNClob (String parameterName, Reader reader) throws SQLException {d.setNClob(parameterName, reader);}
    public void setNClob (String parameterName, Reader reader, long length) throws SQLException {d.setNClob(parameterName, reader, length);}
    public void setNString (String parameterName, String value) throws SQLException {d.setNString(parameterName, value);}
    public void setNull (String parameterName, int sqlType) throws SQLException {d.setNull(parameterName, sqlType);}
    public void setNull (String parameterName, int sqlType, String typeName) throws SQLException {d.setNull(parameterName, sqlType, typeName);}
    public void setObject (String parameterName, Object x) throws SQLException {d.setObject(parameterName, x);}
    public void setObject (String parameterName, Object x, int targetSqlType) throws SQLException {d.setObject(parameterName, x, targetSqlType);}
    public void setObject (String parameterName, Object x, int targetSqlType, int scale) throws SQLException {d.setObject(parameterName, x, targetSqlType, scale);}
    public void setRowId (String parameterName, RowId x) throws SQLException {d.setRowId(parameterName, x);}
    public void setSQLXML (String parameterName, SQLXML xmlObject) throws SQLException {d.setSQLXML(parameterName, xmlObject);}
    public void setShort (String parameterName, short x) throws SQLException {d.setShort(parameterName, x);}
    public void setString (String parameterName, String x) throws SQLException {d.setString(parameterName, x);}
    public void setTime (String parameterName, Time x) throws SQLException {d.setTime(parameterName, x);}
    public void setTime (String parameterName, Time x, Calendar cal) throws SQLException {d.setTime(parameterName, x, cal);}
    public void setTimestamp (String parameterName, Timestamp x) throws SQLException {d.setTimestamp(parameterName, x);}
    public void setTimestamp (String parameterName, Timestamp x, Calendar cal) throws SQLException {d.setTimestamp(parameterName, x, cal);}
    public void setURL (String parameterName, URL val) throws SQLException {d.setURL(parameterName, val);}
}
