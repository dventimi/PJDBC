package org.pjdbc.rmi;

import java.io.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.util.*;

public interface CallableStatementHandler extends PreparedStatementHandler, Remote {
    Array getArray (int parameterIndex) throws RemoteException;
    Array getArray (String parameterName) throws RemoteException;
    BigDecimal getBigDecimal (int parameterIndex) throws RemoteException;
    BigDecimal getBigDecimal (int parameterIndex, int scale) throws RemoteException;
    BigDecimal getBigDecimal (String parameterName) throws RemoteException;
    Blob getBlob (int parameterIndex) throws RemoteException;
    Blob getBlob (String parameterName) throws RemoteException;
    boolean getBoolean (int parameterIndex) throws RemoteException;
    boolean getBoolean (String parameterName) throws RemoteException;
    byte getByte (int parameterIndex) throws RemoteException;
    byte getByte (String parameterName) throws RemoteException;
    byte[] getBytes (int parameterIndex) throws RemoteException;
    byte[] getBytes (String parameterName) throws RemoteException;
    Reader getCharacterStream (int parameterIndex) throws RemoteException;
    Reader getCharacterStream (String parameterName) throws RemoteException;
    Clob getClob (int parameterIndex) throws RemoteException;
    Clob getClob (String parameterName) throws RemoteException;
    java.sql.Date getDate (int parameterIndex) throws RemoteException;
    java.sql.Date getDate (int parameterIndex, Calendar cal) throws RemoteException;
    java.sql.Date getDate (String parameterName) throws RemoteException;
    java.sql.Date getDate (String parameterName, Calendar cal) throws RemoteException;
    double getDouble (int parameterIndex) throws RemoteException;
    double getDouble (String parameterName) throws RemoteException;
    float getFloat (int parameterIndex) throws RemoteException;
    float getFloat (String parameterName) throws RemoteException;
    int getInt (int parameterIndex) throws RemoteException;
    int getInt (String parameterName) throws RemoteException;
    long getLong (int parameterIndex) throws RemoteException;
    long getLong (String parameterName) throws RemoteException;
    Reader getNCharacterStream (int parameterIndex) throws RemoteException;
    Reader getNCharacterStream (String parameterName) throws RemoteException;
    NClob getNClob (int parameterIndex) throws RemoteException;
    NClob getNClob (String parameterName) throws RemoteException;
    String getNString (int parameterIndex) throws RemoteException;
    String getNString (String parameterName) throws RemoteException;
    Object getObject (int parameterIndex) throws RemoteException;
    <T> T getObject (int parameterIndex, Class<T> type) throws RemoteException;
    Object getObject (int parameterIndex, Map<String,Class<?>> map) throws RemoteException;
    Object getObject (String parameterName) throws RemoteException;
    <T> T getObject (String parameterName, Class<T> type) throws RemoteException;
    Object getObject (String parameterName, Map<String,Class<?>> map) throws RemoteException;
    Ref getRef (int parameterIndex) throws RemoteException;
    Ref getRef (String parameterName) throws RemoteException;
    RowId getRowId (int parameterIndex) throws RemoteException;
    RowId getRowId (String parameterName) throws RemoteException;
    short getShort (int parameterIndex) throws RemoteException;
    short getShort (String parameterName) throws RemoteException;
    SQLXML getSQLXML (int parameterIndex) throws RemoteException;
    SQLXML getSQLXML (String parameterName) throws RemoteException;
    String getString (int parameterIndex) throws RemoteException;
    String getString (String parameterName) throws RemoteException;
    Time getTime (int parameterIndex) throws RemoteException;
    Time getTime (int parameterIndex, Calendar cal) throws RemoteException;
    Time getTime (String parameterName) throws RemoteException;
    Time getTime (String parameterName, Calendar cal) throws RemoteException;
    Timestamp getTimestamp (int parameterIndex) throws RemoteException;
    Timestamp getTimestamp (int parameterIndex, Calendar cal) throws RemoteException;
    Timestamp getTimestamp (String parameterName) throws RemoteException;
    Timestamp getTimestamp (String parameterName, Calendar cal) throws RemoteException;
    URL getURL (int parameterIndex) throws RemoteException;
    URL getURL (String parameterName) throws RemoteException;
    void registerOutParameter (int parameterIndex, int sqlType) throws RemoteException;
    void registerOutParameter (int parameterIndex, int sqlType, int scale) throws RemoteException;
    void registerOutParameter (int parameterIndex, int sqlType, String typeName) throws RemoteException;
    void registerOutParameter (String parameterName, int sqlType) throws RemoteException;
    void registerOutParameter (String parameterName, int sqlType, int scale) throws RemoteException;
    void registerOutParameter (String parameterName, int sqlType, String typeName) throws RemoteException;
    void setAsciiStream (String parameterName, InputStream x) throws RemoteException;
    void setAsciiStream (String parameterName, InputStream x, int length) throws RemoteException;
    void setAsciiStream (String parameterName, InputStream x, long length) throws RemoteException;
    void setBigDecimal (String parameterName, BigDecimal x) throws RemoteException;
    void setBinaryStream (String parameterName, InputStream x) throws RemoteException;
    void setBinaryStream (String parameterName, InputStream x, int length) throws RemoteException;
    void setBinaryStream (String parameterName, InputStream x, long length) throws RemoteException;
    void setBlob (String parameterName, Blob x) throws RemoteException;
    void setBlob (String parameterName, InputStream inputStream) throws RemoteException;
    void setBlob (String parameterName, InputStream inputStream, long length) throws RemoteException;
    void setBoolean (String parameterName, boolean x) throws RemoteException;
    void setByte (String parameterName, byte x) throws RemoteException;
    void setBytes (String parameterName, byte[] x) throws RemoteException;
    void setCharacterStream (String parameterName, Reader reader) throws RemoteException;
    void setCharacterStream (String parameterName, Reader reader, int length) throws RemoteException;
    void setCharacterStream (String parameterName, Reader reader, long length) throws RemoteException;
    void setClob (String parameterName, Clob x) throws RemoteException;
    void setClob (String parameterName, Reader reader) throws RemoteException;
    void setClob (String parameterName, Reader reader, long length) throws RemoteException;
    void setDate (String parameterName, java.sql.Date x) throws RemoteException;
    void setDate (String parameterName, java.sql.Date x, Calendar cal) throws RemoteException;
    void setDouble (String parameterName, double x) throws RemoteException;
    void setFloat (String parameterName, float x) throws RemoteException;
    void setInt (String parameterName, int x) throws RemoteException;
    void setLong (String parameterName, long x) throws RemoteException;
    void setNCharacterStream (String parameterName, Reader value) throws RemoteException;
    void setNCharacterStream (String parameterName, Reader value, long length) throws RemoteException;
    void setNClob (String parameterName, NClob value) throws RemoteException;
    void setNClob (String parameterName, Reader reader) throws RemoteException;
    void setNClob (String parameterName, Reader reader, long length) throws RemoteException;
    void setNString (String parameterName, String value) throws RemoteException;
    void setNull (String parameterName, int sqlType) throws RemoteException;
    void setNull (String parameterName, int sqlType, String typeName) throws RemoteException;
    void setObject (String parameterName, Object x) throws RemoteException;
    void setObject (String parameterName, Object x, int targetSqlType) throws RemoteException;
    void setObject (String parameterName, Object x, int targetSqlType, int scale) throws RemoteException;
    void setRowId (String parameterName, RowId x) throws RemoteException;
    void setShort (String parameterName, short x) throws RemoteException;
    void setSQLXML (String parameterName, SQLXML xmlObject) throws RemoteException;
    void setString (String parameterName, String x) throws RemoteException;
    void setTime (String parameterName, Time x) throws RemoteException;
    void setTime (String parameterName, Time x, Calendar cal) throws RemoteException;
    void setTimestamp (String parameterName, Timestamp x) throws RemoteException;
    void setTimestamp (String parameterName, Timestamp x, Calendar cal) throws RemoteException;
    void setURL (String parameterName, URL val) throws RemoteException;
    boolean wasNull () throws RemoteException;}
