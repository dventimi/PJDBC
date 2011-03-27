package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MacroExpandingSQLHandler implements SQLHandler {
    public ResultSet execute (String sql, Connection connection) throws SQLException {
	SQLMacro macro = SQLMacroManager.getSQLMacro(sql);
	if (macro==null) return null;
	return macro.run(connection, sql);}
}