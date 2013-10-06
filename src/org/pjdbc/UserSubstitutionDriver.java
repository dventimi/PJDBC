package org.pjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.pjdbc.lib.AbstractProxyDriver;

public class UserSubstitutionDriver extends AbstractProxyDriver {
    public boolean acceptsSubProtocol (String subprotocol) {
	return "substitute".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	return DriverManager.getConnection(subname(url), info);}

    public int getMajorVersion () {
	return 1;}

    public int getMinorVersion () {
	return 0;}

    public boolean jdbcCompliant () {
	return false;}}
