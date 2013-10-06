package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.pjdbc.lib.AbstractProxyDriver;

public class UserMappingDriver extends AbstractProxyDriver {
    private Properties p = new Properties();

    public int getMajorVersion () {
	return 1;}

    public int getMinorVersion () {
	return 0;}

    public boolean jdbcCompliant () {
	return false;}

    public UserMappingDriver () {
    	try {p.load(getClass().getClassLoader().getResourceAsStream("org.pjdbc.UserMappingDriver.UserMapFile"));}
	catch (Exception e) {throw new RuntimeException(e);}}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "mapuser".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	String user = info.getProperty("user");
	String[] mapping = p.getProperty(user).split("/");
	info.setProperty("user", mapping[0]);
	info.setProperty("password", mapping[1]);
	return DriverManager.getConnection(subname(url), info);}}
