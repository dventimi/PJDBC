package com.jw.server;

import java.rmi.*;
import java.sql.*;
import java.util.*;

public interface IRemoteResultSet extends Remote {
    ResultSetChunk getNextChunk() throws RemoteException, SQLException;
    void close() throws RemoteException, SQLException;
    Map<String, Integer> getColumnList() throws RemoteException, SQLException;}
