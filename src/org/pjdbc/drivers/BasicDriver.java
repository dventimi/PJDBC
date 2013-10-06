package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.pjdbc.util.AbstractProxyDriver;

public class BasicDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new BasicDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    public int getMajorVersion () {
	return 1;}

    public int getMinorVersion () {
	return 0;}

    public boolean jdbcCompliant () {
	return false;}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "basic".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	return DriverManager.getConnection(subname(url));}}
