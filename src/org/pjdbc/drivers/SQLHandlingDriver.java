package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.util.AbstractSQLHandlingDriver;
import org.pjdbc.util.SQLHandler;

public class SQLHandlingDriver extends AbstractSQLHandlingDriver {
    static {try {DriverManager.registerDriver(new SQLHandlingDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "handle".equals(subprotocol);}

    public SQLHandler getSQLHandler () throws Exception {
	return (SQLHandler)Class.forName(System.getProperty("org.pjdbc.SQLHandlingDriver.SQLHandler")).newInstance();}}



