package org.pjdbc.sql;

import java.io.*;
import java.math.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public abstract class AbstractResultSet extends AbstractWrapper implements ResultSet {

    // Data

    private Statement stmt;
    private List<ResultSet> delegates;

    // Constructors

    AbstractResultSet (Statement stmt, ResultSet... rsets) throws SQLException {
	super(rsets);
	if (rsets.length==0) throw new SQLException();
	this.stmt = stmt;
	this.delegates = Arrays.asList(rsets);}

    @Deprecated public BigDecimal getBigDecimal (String columnLabel, int scale) throws SQLException {return delegates.get(0).getBigDecimal(columnLabel, scale);}
    @Deprecated public BigDecimal getBigDecimal (int columnIndex, int scale) throws SQLException {return delegates.get(0).getBigDecimal(columnIndex, scale);}
    @Deprecated public InputStream getUnicodeStream (int columnIndex) throws SQLException {return delegates.get(0).getUnicodeStream(columnIndex);}
    public <T> T getObject (String columnLabel, Class<T> type) throws SQLException {return delegates.get(0).getObject(columnLabel, type);}
    public <T> T getObject (int columnIndex, Class<T> type) throws SQLException {return delegates.get(0).getObject(columnIndex, type);}
    public Array getArray (String columnLabel) throws SQLException {return delegates.get(0).getArray(columnLabel);}
    public Array getArray (int columnIndex) throws SQLException {return delegates.get(0).getArray(columnIndex);}
    public BigDecimal getBigDecimal (String columnLabel) throws SQLException {return delegates.get(0).getBigDecimal(columnLabel);}
    public BigDecimal getBigDecimal (int columnIndex) throws SQLException {return delegates.get(0).getBigDecimal(columnIndex);}
    public Blob getBlob (String columnLabel) throws SQLException {return delegates.get(0).getBlob(columnLabel);}
    public Blob getBlob (int columnIndex) throws SQLException {return delegates.get(0).getBlob(columnIndex);}
    public Clob getClob (String columnLabel) throws SQLException {return delegates.get(0).getClob(columnLabel);}
    public Clob getClob (int columnIndex) throws SQLException {return delegates.get(0).getClob(columnIndex);}
    public InputStream getAsciiStream (String columnLabel) throws SQLException {return delegates.get(0).getAsciiStream(columnLabel);}
    public InputStream getAsciiStream (int columnIndex) throws SQLException {return delegates.get(0).getAsciiStream(columnIndex);}
    public InputStream getBinaryStream (String columnLabel) throws SQLException {return delegates.get(0).getBinaryStream(columnLabel);}
    public InputStream getBinaryStream (int columnIndex) throws SQLException {return delegates.get(0).getBinaryStream(columnIndex);}
    public NClob getNClob (String columnLabel) throws SQLException {return delegates.get(0).getNClob(columnLabel);}
    public NClob getNClob (int columnIndex) throws SQLException {return delegates.get(0).getNClob(columnIndex);}
    public Object getObject (String columnLabel) throws SQLException {return delegates.get(0).getObject(columnLabel);}
    public Object getObject (String columnLabel, Map<String,Class<?>> map) throws SQLException {return delegates.get(0).getObject(columnLabel, map);}
    public Object getObject (int columnIndex) throws SQLException {return delegates.get(0).getObject(columnIndex);}
    public Object getObject (int columnIndex, Map<String,Class<?>> map) throws SQLException {return delegates.get(0).getObject(columnIndex, map);}
    public Reader getCharacterStream (String columnLabel) throws SQLException {return delegates.get(0).getCharacterStream(columnLabel);}
    public Reader getCharacterStream (int columnIndex) throws SQLException {return delegates.get(0).getCharacterStream(columnIndex);}
    public Reader getNCharacterStream (String columnLabel) throws SQLException {return delegates.get(0).getNCharacterStream(columnLabel);}
    public Reader getNCharacterStream (int columnIndex) throws SQLException {return delegates.get(0).getNCharacterStream(columnIndex);}
    public Ref getRef (String columnLabel) throws SQLException {return delegates.get(0).getRef(columnLabel);}
    public Ref getRef (int columnIndex) throws SQLException {return delegates.get(0).getRef(columnIndex);}
    public ResultSetMetaData getMetaData () throws SQLException {return delegates.get(0).getMetaData();}
    public RowId getRowId (String columnLabel) throws SQLException {return delegates.get(0).getRowId(columnLabel);}
    public RowId getRowId (int columnIndex) throws SQLException {return delegates.get(0).getRowId(columnIndex);}
    public SQLWarning getWarnings () throws SQLException {return delegates.get(0).getWarnings();}
    public SQLXML getSQLXML (String columnLabel) throws SQLException {return delegates.get(0).getSQLXML(columnLabel);}
    public SQLXML getSQLXML (int columnIndex) throws SQLException {return delegates.get(0).getSQLXML(columnIndex);}
    public Statement getStatement () throws SQLException {return delegates.get(0).getStatement();}
    public String getCursorName () throws SQLException {return delegates.get(0).getCursorName();}
    public String getNString (String columnLabel) throws SQLException {return delegates.get(0).getNString(columnLabel);}
    public String getNString (int columnIndex) throws SQLException {return delegates.get(0).getNString(columnIndex);}
    public String getString (String columnLabel) throws SQLException {return delegates.get(0).getString(columnLabel);}
    public String getString (int columnIndex) throws SQLException {return delegates.get(0).getString(columnIndex);}
    public Time getTime (String columnLabel) throws SQLException {return delegates.get(0).getTime(columnLabel);}
    public Time getTime (String columnLabel, Calendar cal) throws SQLException {return delegates.get(0).getTime(columnLabel, cal);}
    public Time getTime (int columnIndex) throws SQLException {return delegates.get(0).getTime(columnIndex);}
    public Time getTime (int columnIndex, Calendar cal) throws SQLException {return delegates.get(0).getTime(columnIndex, cal);}
    public Timestamp getTimestamp (String columnLabel) throws SQLException {return delegates.get(0).getTimestamp(columnLabel);}
    public Timestamp getTimestamp (String columnLabel, Calendar cal) throws SQLException {return delegates.get(0).getTimestamp(columnLabel, cal);}
    public Timestamp getTimestamp (int columnIndex) throws SQLException {return delegates.get(0).getTimestamp(columnIndex);}
    public Timestamp getTimestamp (int columnIndex, Calendar cal) throws SQLException {return delegates.get(0).getTimestamp(columnIndex, cal);}
    public URL getURL (String columnLabel) throws SQLException {return delegates.get(0).getURL(columnLabel);}
    public URL getURL (int columnIndex) throws SQLException {return delegates.get(0).getURL(columnIndex);}
    public boolean absolute (int row) throws SQLException {return delegates.get(0).absolute(row);}
    public boolean first () throws SQLException {return delegates.get(0).first();}
    public boolean getBoolean (String columnLabel) throws SQLException {return delegates.get(0).getBoolean(columnLabel);}
    public boolean getBoolean (int columnIndex) throws SQLException {return delegates.get(0).getBoolean(columnIndex);}
    public boolean isAfterLast () throws SQLException {return delegates.get(0).isAfterLast();}
    public boolean isBeforeFirst () throws SQLException {return delegates.get(0).isBeforeFirst();}
    public boolean isClosed () throws SQLException {return delegates.get(0).isClosed();}
    public boolean isFirst () throws SQLException {return delegates.get(0).isFirst();}
    public boolean isLast () throws SQLException {return delegates.get(0).isLast();}
    public boolean last () throws SQLException {return delegates.get(0).last();}
    public boolean next () throws SQLException {return delegates.get(0).next();}
    public boolean previous () throws SQLException {return delegates.get(0).previous();}
    public boolean relative (int rows) throws SQLException {return delegates.get(0).relative(rows);}
    public boolean rowDeleted () throws SQLException {return delegates.get(0).rowDeleted();}
    public boolean rowInserted () throws SQLException {return delegates.get(0).rowInserted();}
    public boolean rowUpdated () throws SQLException {return delegates.get(0).rowUpdated();}
    public boolean wasNull () throws SQLException {return delegates.get(0).wasNull();}
    public byte getByte (String columnLabel) throws SQLException {return delegates.get(0).getByte(columnLabel);}
    public byte getByte (int columnIndex) throws SQLException {return delegates.get(0).getByte(columnIndex);}
    public byte[] getBytes (String columnLabel) throws SQLException {return delegates.get(0).getBytes(columnLabel);}
    public byte[] getBytes (int columnIndex) throws SQLException {return delegates.get(0).getBytes(columnIndex);}
    public double getDouble (String columnLabel) throws SQLException {return delegates.get(0).getDouble(columnLabel);}
    public double getDouble (int columnIndex) throws SQLException {return delegates.get(0).getDouble(columnIndex);}
    public float getFloat (String columnLabel) throws SQLException {return delegates.get(0).getFloat(columnLabel);}
    public float getFloat (int columnIndex) throws SQLException {return delegates.get(0).getFloat(columnIndex);}
    public int findColumn (String columnLabel) throws SQLException {return delegates.get(0).findColumn(columnLabel);}
    public int getConcurrency () throws SQLException {return delegates.get(0).getConcurrency();}
    public int getFetchDirection () throws SQLException {return delegates.get(0).getFetchDirection();}
    public int getFetchSize () throws SQLException {return delegates.get(0).getFetchSize();}
    public int getHoldability () throws SQLException {return delegates.get(0).getHoldability();}
    public int getInt (String columnLabel) throws SQLException {return delegates.get(0).getInt(columnLabel);}
    public int getInt (int columnIndex) throws SQLException {return delegates.get(0).getInt(columnIndex);}
    public int getRow () throws SQLException {return delegates.get(0).getRow();}
    public int getType () throws SQLException {return delegates.get(0).getType();}
    public java.sql.Date getDate (String columnLabel) throws SQLException {return delegates.get(0).getDate(columnLabel);}
    public java.sql.Date getDate (String columnLabel, Calendar cal) throws SQLException {return delegates.get(0).getDate(columnLabel, cal);}
    public java.sql.Date getDate (int columnIndex) throws SQLException {return delegates.get(0).getDate(columnIndex);}
    public java.sql.Date getDate (int columnIndex, Calendar cal) throws SQLException {return delegates.get(0).getDate(columnIndex, cal);}
    public long getLong (String columnLabel) throws SQLException {return delegates.get(0).getLong(columnLabel);}
    public long getLong (int columnIndex) throws SQLException {return delegates.get(0).getLong(columnIndex);}
    public short getShort (String columnLabel) throws SQLException {return delegates.get(0).getShort(columnLabel);}
    public short getShort (int columnIndex) throws SQLException {return delegates.get(0).getShort(columnIndex);}
    public void afterLast () throws SQLException {delegates.get(0).afterLast();}
    public void beforeFirst () throws SQLException {delegates.get(0).beforeFirst();}
    public void cancelRowUpdates () throws SQLException {delegates.get(0).cancelRowUpdates();}
    public void clearWarnings () throws SQLException {delegates.get(0).clearWarnings();}
    public void close () throws SQLException {delegates.get(0).close();}
    public void deleteRow () throws SQLException {delegates.get(0).deleteRow();}
    public void insertRow () throws SQLException {delegates.get(0).insertRow();}
    public void moveToCurrentRow () throws SQLException {delegates.get(0).moveToCurrentRow();}
    public void moveToInsertRow () throws SQLException {delegates.get(0).moveToInsertRow();}
    public void refreshRow () throws SQLException {delegates.get(0).refreshRow();}
    public void setFetchDirection (int direction) throws SQLException {delegates.get(0).setFetchDirection(direction);}
    public void setFetchSize (int rows) throws SQLException {delegates.get(0).setFetchSize(rows);}
    public void updateArray (String columnLabel, Array x) throws SQLException {delegates.get(0).updateArray(columnLabel, x);}
    public void updateArray (int columnIndex, Array x) throws SQLException {delegates.get(0).updateArray(columnIndex, x);}
    public void updateAsciiStream (String columnLabel, InputStream x) throws SQLException {delegates.get(0).updateAsciiStream(columnLabel, x);}
    public void updateAsciiStream (String columnLabel, InputStream x, int length) throws SQLException {delegates.get(0).updateAsciiStream(columnLabel, x, length);}
    public void updateAsciiStream (String columnLabel, InputStream x, long length) throws SQLException {delegates.get(0).updateAsciiStream(columnLabel, x, length);}
    public void updateAsciiStream (int columnIndex, InputStream x) throws SQLException {delegates.get(0).updateAsciiStream(columnIndex, x);}
    public void updateAsciiStream (int columnIndex, InputStream x, int length) throws SQLException {delegates.get(0).updateAsciiStream(columnIndex, x, length);}
    public void updateAsciiStream (int columnIndex, InputStream x, long length) throws SQLException {delegates.get(0).updateAsciiStream(columnIndex, x, length);}
    public void updateBigDecimal (String columnLabel, BigDecimal x) throws SQLException {delegates.get(0).updateBigDecimal(columnLabel, x);}
    public void updateBigDecimal (int columnIndex, BigDecimal x) throws SQLException {delegates.get(0).updateBigDecimal(columnIndex, x);}
    public void updateBinaryStream (String columnLabel, InputStream x) throws SQLException {delegates.get(0).updateBinaryStream(columnLabel, x);}
    public void updateBinaryStream (String columnLabel, InputStream x, int length) throws SQLException {delegates.get(0).updateBinaryStream(columnLabel, x, length);}
    public void updateBinaryStream (String columnLabel, InputStream x, long length) throws SQLException {delegates.get(0).updateBinaryStream(columnLabel, x, length);}
    public void updateBinaryStream (int columnIndex, InputStream x) throws SQLException {delegates.get(0).updateBinaryStream(columnIndex, x);}
    public void updateBinaryStream (int columnIndex, InputStream x, int length) throws SQLException {delegates.get(0).updateBinaryStream(columnIndex, x, length);}
    public void updateBinaryStream (int columnIndex, InputStream x, long length) throws SQLException {delegates.get(0).updateBinaryStream(columnIndex, x, length);}
    public void updateBlob (String columnLabel, Blob x) throws SQLException {delegates.get(0).updateBlob(columnLabel, x);}
    public void updateBlob (String columnLabel, InputStream inputStream) throws SQLException {delegates.get(0).updateBlob(columnLabel, inputStream);}
    public void updateBlob (String columnLabel, InputStream inputStream, long length) throws SQLException {delegates.get(0).updateBlob(columnLabel, inputStream, length);}
    public void updateBlob (int columnIndex, Blob x) throws SQLException {delegates.get(0).updateBlob(columnIndex, x);}
    public void updateBlob (int columnIndex, InputStream inputStream) throws SQLException {delegates.get(0).updateBlob(columnIndex, inputStream);}
    public void updateBlob (int columnIndex, InputStream inputStream, long length) throws SQLException {delegates.get(0).updateBlob(columnIndex, inputStream, length);}
    public void updateBoolean (String columnLabel, boolean x) throws SQLException {delegates.get(0).updateBoolean(columnLabel, x);}
    public void updateBoolean (int columnIndex, boolean x) throws SQLException {delegates.get(0).updateBoolean(columnIndex, x);}
    public void updateByte (String columnLabel, byte x) throws SQLException {delegates.get(0).updateByte(columnLabel, x);}
    public void updateByte (int columnIndex, byte x) throws SQLException {delegates.get(0).updateByte(columnIndex, x);}
    public void updateBytes (String columnLabel, byte[] x) throws SQLException {delegates.get(0).updateBytes(columnLabel, x);}
    public void updateBytes (int columnIndex, byte[] x) throws SQLException {delegates.get(0).updateBytes(columnIndex, x);}
    public void updateCharacterStream (String columnLabel, Reader reader) throws SQLException {delegates.get(0).updateCharacterStream(columnLabel, reader);}
    public void updateCharacterStream (String columnLabel, Reader reader, int length) throws SQLException {delegates.get(0).updateCharacterStream(columnLabel, reader, length);}
    public void updateCharacterStream (String columnLabel, Reader reader, long length) throws SQLException {delegates.get(0).updateCharacterStream(columnLabel, reader, length);}
    public void updateCharacterStream (int columnIndex, Reader x) throws SQLException {delegates.get(0).updateCharacterStream(columnIndex, x);}
    public void updateCharacterStream (int columnIndex, Reader x, int length) throws SQLException {delegates.get(0).updateCharacterStream(columnIndex, x, length);}
    public void updateCharacterStream (int columnIndex, Reader x, long length) throws SQLException {delegates.get(0).updateCharacterStream(columnIndex, x, length);}
    public void updateClob (String columnLabel, Clob x) throws SQLException {delegates.get(0).updateClob(columnLabel, x);}
    public void updateClob (String columnLabel, Reader reader) throws SQLException {delegates.get(0).updateClob(columnLabel, reader);}
    public void updateClob (String columnLabel, Reader reader, long length) throws SQLException {delegates.get(0).updateClob(columnLabel, reader, length);}
    public void updateClob (int columnIndex, Clob x) throws SQLException {delegates.get(0).updateClob(columnIndex, x);}
    public void updateClob (int columnIndex, Reader reader) throws SQLException {delegates.get(0).updateClob(columnIndex, reader);}
    public void updateClob (int columnIndex, Reader reader, long length) throws SQLException {delegates.get(0).updateClob(columnIndex, reader, length);}
    public void updateDate (String columnLabel, java.sql.Date x) throws SQLException {delegates.get(0).updateDate(columnLabel, x);}
    public void updateDate (int columnIndex, java.sql.Date x) throws SQLException {delegates.get(0).updateDate(columnIndex, x);}
    public void updateDouble (String columnLabel, double x) throws SQLException {delegates.get(0).updateDouble(columnLabel, x);}
    public void updateDouble (int columnIndex, double x) throws SQLException {delegates.get(0).updateDouble(columnIndex, x);}
    public void updateFloat (String columnLabel, float x) throws SQLException {delegates.get(0).updateFloat(columnLabel, x);}
    public void updateFloat (int columnIndex, float x) throws SQLException {delegates.get(0).updateFloat(columnIndex, x);}
    public void updateInt (String columnLabel, int x) throws SQLException {delegates.get(0).updateInt(columnLabel, x);}
    public void updateInt (int columnIndex, int x) throws SQLException {delegates.get(0).updateInt(columnIndex, x);}
    public void updateLong (String columnLabel, long x) throws SQLException {delegates.get(0).updateLong(columnLabel, x);}
    public void updateLong (int columnIndex, long x) throws SQLException {delegates.get(0).updateLong(columnIndex, x);}
    public void updateNCharacterStream (String columnLabel, Reader reader) throws SQLException {delegates.get(0).updateNCharacterStream(columnLabel, reader);}
    public void updateNCharacterStream (String columnLabel, Reader reader, long length) throws SQLException {delegates.get(0).updateNCharacterStream(columnLabel, reader, length);}
    public void updateNCharacterStream (int columnIndex, Reader x) throws SQLException {delegates.get(0).updateNCharacterStream(columnIndex, x);}
    public void updateNCharacterStream (int columnIndex, Reader x, long length) throws SQLException {delegates.get(0).updateNCharacterStream(columnIndex, x, length);}
    public void updateNClob (String columnLabel, NClob nClob) throws SQLException {delegates.get(0).updateNClob(columnLabel, nClob);}
    public void updateNClob (String columnLabel, Reader reader) throws SQLException {delegates.get(0).updateNClob(columnLabel, reader);}
    public void updateNClob (String columnLabel, Reader reader, long length) throws SQLException {delegates.get(0).updateNClob(columnLabel, reader, length);}
    public void updateNClob (int columnIndex, NClob nClob) throws SQLException {delegates.get(0).updateNClob(columnIndex, nClob);}
    public void updateNClob (int columnIndex, Reader reader) throws SQLException {delegates.get(0).updateNClob(columnIndex, reader);}
    public void updateNClob (int columnIndex, Reader reader, long length) throws SQLException {delegates.get(0).updateNClob(columnIndex, reader, length);}
    public void updateNString (String columnLabel, String nString) throws SQLException {delegates.get(0).updateNString(columnLabel, nString);}
    public void updateNString (int columnIndex, String nString) throws SQLException {delegates.get(0).updateNString(columnIndex, nString);}
    public void updateNull (String columnLabel) throws SQLException {delegates.get(0).updateNull(columnLabel);}
    public void updateNull (int columnIndex) throws SQLException {delegates.get(0).updateNull(columnIndex);}
    public void updateObject (String columnLabel, Object x) throws SQLException {delegates.get(0).updateObject(columnLabel, x);}
    public void updateObject (String columnLabel, Object x, int scaleOrLength) throws SQLException {delegates.get(0).updateObject(columnLabel, x, scaleOrLength);}
    public void updateObject (int columnIndex, Object x) throws SQLException {delegates.get(0).updateObject(columnIndex, x);}
    public void updateObject (int columnIndex, Object x, int scaleOrLength) throws SQLException {delegates.get(0).updateObject(columnIndex, x, scaleOrLength);}
    public void updateRef (String columnLabel, Ref x) throws SQLException {delegates.get(0).updateRef(columnLabel, x);}
    public void updateRef (int columnIndex, Ref x) throws SQLException {delegates.get(0).updateRef(columnIndex, x);}
    public void updateRow () throws SQLException {delegates.get(0).updateRow();}
    public void updateRowId (String columnLabel, RowId x) throws SQLException {delegates.get(0).updateRowId(columnLabel, x);}
    public void updateRowId (int columnIndex, RowId x) throws SQLException {delegates.get(0).updateRowId(columnIndex, x);}
    public void updateSQLXML (String columnLabel, SQLXML xmlObject) throws SQLException {delegates.get(0).updateSQLXML(columnLabel, xmlObject);}
    public void updateSQLXML (int columnIndex, SQLXML xmlObject) throws SQLException {delegates.get(0).updateSQLXML(columnIndex, xmlObject);}
    public void updateShort (String columnLabel, short x) throws SQLException {delegates.get(0).updateShort(columnLabel, x);}
    public void updateShort (int columnIndex, short x) throws SQLException {delegates.get(0).updateShort(columnIndex, x);}
    public void updateString (String columnLabel, String x) throws SQLException {delegates.get(0).updateString(columnLabel, x);}
    public void updateString (int columnIndex, String x) throws SQLException {delegates.get(0).updateString(columnIndex, x);}
    public void updateTime (String columnLabel, Time x) throws SQLException {delegates.get(0).updateTime(columnLabel, x);}
    public void updateTime (int columnIndex, Time x) throws SQLException {delegates.get(0).updateTime(columnIndex, x);}
    public void updateTimestamp (String columnLabel, Timestamp x) throws SQLException {delegates.get(0).updateTimestamp(columnLabel, x);}
    public void updateTimestamp (int columnIndex, Timestamp x) throws SQLException {delegates.get(0).updateTimestamp(columnIndex, x);}
}
