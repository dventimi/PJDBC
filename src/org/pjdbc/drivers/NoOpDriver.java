package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.util.AbstractTranslatingDriver;
import org.pjdbc.util.Translator;

public class NoOpDriver extends AbstractTranslatingDriver {
    static {try {DriverManager.registerDriver(new NoOpDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "identity".equals(subprotocol);}

    public Translator getTranslator () {
	return new Translator () {
	    public ResultSet execute (String sql, Connection conn) throws SQLException {
		return conn.createStatement().executeQuery("");}};}}
