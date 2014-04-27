package org.pjdbc.rmi;

import java.rmi.*;

public interface WrapperRemoteProxy extends Remote {
    boolean isWrapperFor (Class<?> iface) throws RemoteException;
    <T> T unwrap(Class<T> iface) throws RemoteException;}
