package org.pjdbc.drivers;

import java.sql.*;
import java.util.*;
import org.pjdbc.sql.*;

public class UserMapDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new UserMapDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    private Properties p = new Properties();

    public UserMapDriver () {
	try {p.load(getClass().getClassLoader().getResourceAsStream("org.pjdbc.UserMapDriver.UserMapFile"));}
	catch (Exception e) {throw new RuntimeException(e);}}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "mapuser".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	String user = info.getProperty("user");
	String[] mapping = p.getProperty(user).split("/");
	info.setProperty("user", mapping[0]);
	info.setProperty("password", mapping[1]);
	return DriverManager.getConnection(subname(url), info);}}
