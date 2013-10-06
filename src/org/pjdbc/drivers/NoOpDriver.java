package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.lib.AbstractTranslatingDriver;
import org.pjdbc.lib.Translator;

public class NoOpDriver extends AbstractTranslatingDriver {
    private final int majorVersion = 1;
    private final int minorVersion = 1;
    private final boolean jdbcCompliant = false;

    public boolean acceptsSubProtocol (String subprotocol) {
	return "identity".equals(subprotocol);}

    public Translator getTranslator () {
	return new Translator () {
	    public ResultSet execute (String sql, Connection conn) throws SQLException {
		return conn.createStatement().executeQuery("");}};}}
