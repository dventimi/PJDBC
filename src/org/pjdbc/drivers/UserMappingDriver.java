package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.pjdbc.lib.AbstractProxyDriver;

public class UserMappingDriver extends AbstractProxyDriver {
    private final int majorVersion = 1;
    private final int minorVersion = 1;
    private final boolean jdbcCompliant = false;

    private Properties p = new Properties();

    public UserMappingDriver () {
    	try {p.load(getClass().getClassLoader().getResourceAsStream("org.pjdbc.UserMappingDriver.UserMapFile"));}
	catch (Exception e) {throw new RuntimeException(e);}}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "substitute".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	String user = info.getProperty("user");
	String[] mapping = p.getProperty(user).split("/");
	info.setProperty("user", mapping[0]);
	info.setProperty("password", mapping[1]);
	return DriverManager.getConnection(subname(url), info);}}
