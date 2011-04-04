package org.radiumsalt; // Generated package name

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.pjdbc.SQLHandler;

public class SaltedSQLHandler implements SQLHandler {
    public ResultSet execute (String sql, Connection conn) throws SQLException {
	Statement stmt = conn.createStatement();
	ResultSet res = null;
	for (String query : SaltBin.getSaltedSQL(conn, sql)) res = stmt.executeQuery(query);
	return res;}
}