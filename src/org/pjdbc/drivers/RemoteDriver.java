package org.pjdbc.drivers;

import java.rmi.registry.*;
import java.sql.*;
import java.util.*;
import org.pjdbc.rmi.*;
import org.pjdbc.util.*;

public class RemoteDriver extends AbstractDriver {
    protected boolean acceptsSubProtocol (String subprotocol) {
	return "remote".equals(subprotocol);}

    protected boolean acceptsSubName (String subName) {
	return true;}

    public Connection connect (String url, Properties info) throws SQLException {
	try {
	    Registry registry = LocateRegistry.getRegistry();
	    DriverHandler dh = (DriverHandler)registry.lookup("foo");
	    return dh.connect(url, info);}
	catch (Exception e) {throw new SQLException();}}}
