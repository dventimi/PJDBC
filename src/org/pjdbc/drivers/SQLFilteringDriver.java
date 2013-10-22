package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.util.AbstractFilteringDriver;
import org.pjdbc.util.Filter;

public class SQLFilteringDriver extends AbstractFilteringDriver {
    static {try {DriverManager.registerDriver(new SQLFilteringDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "filter".equals(subprotocol);}

    public Filter getFilter () throws Exception {
	return (Filter)Class.forName(System.getProperty("org.pjdbc.SQLFilteringDriver.Filter")).newInstance();}}



