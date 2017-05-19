package org.pjdbc.rmi;

import java.lang.reflect.*;
import java.rmi.registry.*;
import java.sql.*;

public class Bootstrap {
    public static void main (String[] args) {
	try {
	    Class.forName(args[0]);
	    final Driver targetDriver = DriverManager.getDriver(args[1]);
	    LocateRegistry.getRegistry().bind(args[2], (DriverRemoteProxy)Proxy.newProxyInstance(Bootstrap.class.getClassLoader(), new Class[]{DriverRemoteProxy.class}, new InvocationHandler() {
		    public Object invoke (Object proxy, Method method, Object[] args) throws SQLException {
			try {return method.invoke(targetDriver, args);} catch (Exception e) {throw new SQLException(e);}}}));}
	catch (Exception e) {e.printStackTrace(System.err);}}}
