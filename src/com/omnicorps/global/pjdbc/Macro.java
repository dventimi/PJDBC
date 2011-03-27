package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class Macro {
    public boolean accepts (String macro) {
	return true;}

    public String parse (String macro) {
	return macro;}

    public ResultSet expand (String macro, Connection connection) throws UnsupportedOperationException {
	if (!this.accepts(macro)) throw new UnsupportedOperationException();
	return this.expand(this.parse(macro), connection);}
}