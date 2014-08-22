package org.pjdbc.rmi;

import java.rmi.*;

public interface SavepointRemoteProxy extends Remote {
    int getSavepointId () throws RemoteException;
    String getSavepointName () throws RemoteException;}
