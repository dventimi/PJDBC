package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class SQLMacro
    implements SQLMacroFrontEnd, SQLMacroBackend {

    public ResultSet run (Connection connection, String macro)
	throws UnsupportedOperationException {
	if (!this.accepts(macro)) throw new UnsupportedOperationException();
	return this.deliver(connection, this.parse(macro));}
}