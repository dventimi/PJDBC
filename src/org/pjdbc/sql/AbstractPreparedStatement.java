package org.pjdbc.sql;

import java.sql.*;
import java.util.*;

public abstract class AbstractPreparedStatement extends AbstractStatement implements PreparedStatement {
    private List<PreparedStatement> delegates;

    AbstractPreparedStatement (Connection conn, PreparedStatement... stmts) throws SQLException {
	super(conn, stmts);
	if (stmts.length==0) throw new SQLException();
	this.delegates = Arrays.asList(stmts);}

    // public ParameterMetaData getParameterMetaData () throws SQLException {
    // 	return delegates.get(0).getParameterMetaData();}
    // public ResultSet executeQuery () throws SQLException {
    // 	return delegates.get(0).executeQuery();}
    // public ResultSetMetaData getMetaData () throws SQLException {
    // 	return delegates.get(0).getMetaData();}
    // public boolean execute () throws SQLException {
    // 	return delegates.get(0).execute();}
    // public int executeUpdate () throws SQLException {
    // 	return delegates.get(0).executeUpdate();}
    // public void addBatch () throws SQLException {
    // 	return delegates.get(0).addBatch();}
    // public void clearParameters () throws SQLException {
    // 	return delegates.get(0).clearParameters();}
    // public void setArray (int parameterIndex, Array x) throws SQLException {
    // 	return delegates.get(0).setArray(parameterIndex, Array x);}
    // public void setAsciiStream (int parameterIndex, InputStream x) throws SQLException {
    // 	return delegates.get(0).setAsciiStream(parameterIndex, InputStream x);}
    // public void setAsciiStream (int parameterIndex, InputStream x, length) throws SQLException {
    // 	return delegates.get(0).setAsciiStream(parameterIndex, InputStream x, length);}
    // public void setAsciiStream (int parameterIndex, InputStream x, long length) throws SQLException {
    // 	return delegates.get(0).setAsciiStream(parameterIndex, InputStream x, long length);}
    // public void setBigDecimal (int parameterIndex, BigDecimal x) throws SQLException {
    // 	return delegates.get(0).setBigDecimal(parameterIndex, BigDecimal x);}
    // public void setBinaryStream (int parameterIndex, InputStream x) throws SQLException {
    // 	return delegates.get(0).setBinaryStream(parameterIndex, InputStream x);}
    // public void setBinaryStream (int parameterIndex, InputStream x, int length) throws SQLException {
    // 	return delegates.get(0).setBinaryStream(parameterIndex, InputStream x, length);}
    // public void setBinaryStream (int parameterIndex, InputStream x, long length) throws SQLException {
    // 	return delegates.get(0).setBinaryStream(parameterIndex, InputStream x, long length);}
    // public void setBlob (int parameterIndex, Blob x) throws SQLException {
    // 	return delegates.get(0).setBlob(parameterIndex, Blob x);}
    // public void setBlob (int parameterIndex, InputStream inputStream) throws SQLException {
    // 	return delegates.get(0).setBlob(parameterIndex, InputStream inputStream);}
    // public void setBlob (int parameterIndex, InputStream inputStream, long length) throws SQLException {
    // 	return delegates.get(0).setBlob(parameterIndex, InputStream inputStream, long length);}
    // public void setBoolean (int parameterIndex, boolean x) throws SQLException {
    // 	return delegates.get(0).setBoolean(parameterIndex, boolean x);}
    // public void setByte (int parameterIndex, byte x) throws SQLException {
    // 	return delegates.get(0).setByte(parameterIndex, byte x);}
    // public void setBytes (int parameterIndex, byte[] x) throws SQLException {
    // 	return delegates.get(0).setBytes(parameterIndex, byte[] x);}
    // public void setCharacterStream (int parameterIndex, Reader reader) throws SQLException {
    // 	return delegates.get(0).setCharacterStream(parameterIndex, Reader reader);}
    // public void setCharacterStream (int parameterIndex, Reader reader, int length) throws SQLException {
    // 	return delegates.get(0).setCharacterStream(parameterIndex, Reader reader, length);}
    // public void setCharacterStream (int parameterIndex, Reader reader, long length) throws SQLException {
    // 	return delegates.get(0).setCharacterStream(parameterIndex, Reader reader, long length);}
    // public void setClob (int parameterIndex, Clob x) throws SQLException {
    // 	return delegates.get(0).setClob(parameterIndex, Clob x);}
    // public void setClob (int parameterIndex, Reader reader) throws SQLException {
    // 	return delegates.get(0).setClob(parameterIndex, Reader reader);}
    // public void setClob (int parameterIndex, Reader reader, long length) throws SQLException {
    // 	return delegates.get(0).setClob(parameterIndex, Reader reader, long length);}
    // public void setDate (int parameterIndex, Date x) throws SQLException {
    // 	return delegates.get(0).setDate(parameterIndex, Date x);}
    // public void setDate (int parameterIndex, Date x, Calendar cal) throws SQLException {
    // 	return delegates.get(0).setDate(parameterIndex, Date x, Calendar cal);}
    // public void setDouble (int parameterIndex, double x) throws SQLException {
    // 	return delegates.get(0).setDouble(parameterIndex, double x);}
    // public void setFloat (int parameterIndex, float x) throws SQLException {
    // 	return delegates.get(0).setFloat(parameterIndex, float x);}
    // public void setInt (int parameterIndex, int x) throws SQLException {
    // 	return delegates.get(0).setInt(parameterIndex, x);}
    // public void setLong (int parameterIndex, long x) throws SQLException {
    // 	return delegates.get(0).setLong(parameterIndex, long x);}
    // public void setNCharacterStream (int parameterIndex, Reader value) throws SQLException {
    // 	return delegates.get(0).setNCharacterStream(parameterIndex, Reader value);}
    // public void setNCharacterStream (int parameterIndex, Reader value, long length) throws SQLException {
    // 	return delegates.get(0).setNCharacterStream(parameterIndex, Reader value, long length);}
    // public void setNClob (int parameterIndex, NClob value) throws SQLException {
    // 	return delegates.get(0).setNClob(parameterIndex, NClob value);}
    // public void setNClob (int parameterIndex, Reader reader) throws SQLException {
    // 	return delegates.get(0).setNClob(parameterIndex, Reader reader);}
    // public void setNClob (int parameterIndex, Reader reader, long length) throws SQLException {
    // 	return delegates.get(0).setNClob(parameterIndex, Reader reader, long length);}
    // public void setNString (int parameterIndex, String value) throws SQLException {
    // 	return delegates.get(0).setNString(parameterIndex, String value);}
    // public void setNull (int parameterIndex, int sqlType) throws SQLException {
    // 	return delegates.get(0).setNull(parameterIndex, sqlType);}
    // public void setNull (int parameterIndex, int sqlType, String typeName) throws SQLException {
    // 	return delegates.get(0).setNull(parameterIndex, sqlType, String typeName);}
    // public void setObject (int parameterIndex, Object x) throws SQLException {
    // 	return delegates.get(0).setObject(parameterIndex, Object x);}
    // public void setObject (int parameterIndex, Object x, int targetSqlType) throws SQLException {
    // 	return delegates.get(0).setObject(parameterIndex, Object x, targetSqlType);}
    // public void setObject (int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
    // 	return delegates.get(0).setObject(parameterIndex, Object x, targetSqlType, scaleOrLength);}
    // public void setRef (int parameterIndex, Ref x) throws SQLException {
    // 	return delegates.get(0).setRef(parameterIndex, Ref x);}
    // public void setRowId (int parameterIndex, RowId x) throws SQLException {
    // 	return delegates.get(0).setRowId(parameterIndex, RowId x);}
    // public void setSQLXML (int parameterIndex, SQLXML xmlObject) throws SQLException {
    // 	return delegates.get(0).setSQLXML(parameterIndex, SQLXML xmlObject);}
    // public void setShort (int parameterIndex, short x) throws SQLException {
    // 	return delegates.get(0).setShort(parameterIndex, short x);}
    // public void setString (int parameterIndex, String x) throws SQLException {
    // 	return delegates.get(0).setString(parameterIndex, String x);}
    // public void setTime (int parameterIndex, Time x) throws SQLException {
    // 	return delegates.get(0).setTime(parameterIndex, Time x);}
    // public void setTime (int parameterIndex, Time x, Calendar cal) throws SQLException {
    // 	return delegates.get(0).setTime(parameterIndex, Time x, Calendar cal);}
    // public void setTimestamp (int parameterIndex, Timestamp x) throws SQLException {
    // 	return delegates.get(0).setTimestamp(parameterIndex, Timestamp x);}
    // public void setTimestamp (int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
    // 	return delegates.get(0).setTimestamp(parameterIndex, Timestamp x, Calendar cal);}
    // public void setURL (int parameterIndex, URL x) throws SQLException {
    // 	return delegates.get(0).setURL(parameterIndex, URL x);}
    // public void setUnicodeStream (int parameterIndex, InputStream x, int length) throws SQLException {
    // 	return delegates.get(0).setUnicodeStream(parameterIndex, InputStream x, length);}
}
