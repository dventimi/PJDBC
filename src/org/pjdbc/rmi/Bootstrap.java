package org.pjdbc.rmi;

import java.rmi.registry.*;
import java.sql.*;
import org.pjdbc.util.*;

public class Bootstrap {
    public static void main (String[] args) {
	try {
	    Class.forName(args[0]);
	    LocateRegistry.getRegistry().bind(args[2], GenericProxyFactory.getProxy(DriverHandler.class, DriverManager.getDriver(args[1])));}
	catch (Exception e) {e.printStackTrace(System.err);}}}
