package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.util.AbstractSQLHandlingDriver;
import org.pjdbc.util.SQLHandler;

public class CatDriver extends AbstractSQLHandlingDriver {
    static {try {DriverManager.registerDriver(new CatDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "cat".equals(subprotocol);}

    public SQLHandler getSQLHandler () {
	return new SQLHandler () {
	    public ResultSet handle (String sql, Connection conn) throws SQLException {
		return conn.createStatement().executeQuery(sql);}};}}
