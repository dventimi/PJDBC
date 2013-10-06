package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.pjdbc.lib.AbstractProxyDriver;

public class BasicProxyDriver extends AbstractProxyDriver {
    private final int majorVersion = 1;
    private final int minorVersion = 1;
    private final boolean jdbcCompliant = false;

    public boolean acceptsSubProtocol (String subprotocol) {
	return "basic".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	return DriverManager.getConnection(subname(url));}}
