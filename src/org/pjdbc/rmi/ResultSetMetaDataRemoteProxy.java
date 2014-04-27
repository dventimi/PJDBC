package org.pjdbc.rmi;

import java.rmi.*;

public interface ResultSetMetaDataRemoteProxy {
    String getCatalogName (int column) throws RemoteException;
    String getColumnClassName (int column) throws RemoteException;
    int getColumnCount () throws RemoteException;
    int getColumnDisplaySize (int column) throws RemoteException;
    String getColumnLabel (int column) throws RemoteException;
    String getColumnName (int column) throws RemoteException;
    int getColumnType (int column) throws RemoteException;
    String getColumnTypeName (int column) throws RemoteException;
    int getPrecision (int column) throws RemoteException;
    int getScale (int column) throws RemoteException;
    String getSchemaName (int column) throws RemoteException;
    String getTableName (int column) throws RemoteException;
    boolean isAutoIncrement (int column) throws RemoteException;
    boolean isCaseSensitive (int column) throws RemoteException;
    boolean isCurrency (int column) throws RemoteException;
    boolean isDefinitelyWritable (int column) throws RemoteException;
    int isNullable (int column) throws RemoteException;
    boolean isReadOnly (int column) throws RemoteException;
    boolean isSearchable (int column) throws RemoteException;
    boolean isSigned (int column) throws RemoteException;
    boolean isWritable (int column) throws RemoteException;}
