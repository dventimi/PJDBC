package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class SQLMacro {
    public ResultSet expand (String macro, Connection connection) throws UnsupportedOperationException {
	if (!this.accepts(macro)) throw new UnsupportedOperationException();
	return this.deliver(connection, this.parse(macro));}
}