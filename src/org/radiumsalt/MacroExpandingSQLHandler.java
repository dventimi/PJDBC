package org.radiumsalt; // Generated package name

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.pjdbc.SQLHandler;

public class MacroExpandingSQLHandler implements SQLHandler {
    public ResultSet execute (String sql, Connection connection) throws SQLException {
	Macro macro = MacroManager.getMacro(sql);
	if (macro==null) return null;
	return macro.expand(sql, connection);}
}