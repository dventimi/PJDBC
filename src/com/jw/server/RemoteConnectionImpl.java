package com.jw.server;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.*;

public class RemoteConnectionImpl extends UnicastRemoteObject implements IRemoteConnection {
    private Connection sqlConnection;

    public RemoteConnectionImpl(Connection sqlCon) throws RemoteException {
        super();
        sqlConnection = sqlCon;}

    public IRemoteStatement createStatement () throws RemoteException, SQLException {
        RemoteStatementImpl StmtImplInstance =  new RemoteStatementImpl(sqlConnection.createStatement());
        return  (IRemoteStatement)StmtImplInstance;}

    public void closeConnection() throws RemoteException,SQLException {
        ConnectionPool.getInstance().addConnection(sqlConnection);}}
