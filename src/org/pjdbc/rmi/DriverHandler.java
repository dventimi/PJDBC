package org.pjdbc.rmi;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public interface DriverHandler extends Remote {
    boolean acceptsURL (String url) throws RemoteException;
    Connection connect (String url, Properties info) throws RemoteException;
    int getMajorVersion () throws RemoteException;
    int getMinorVersion () throws RemoteException;
    Logger getParentLogger () throws RemoteException;
    DriverPropertyInfo[] getPropertyInfo (String url, Properties info) throws RemoteException;
    boolean jdbcCompliant () throws RemoteException;}
