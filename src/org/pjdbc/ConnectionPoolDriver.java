package org.pjdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.pjdbc.lib.AbstractProxyDriver;

public class ConnectionPoolDriver extends AbstractProxyDriver {
    private static final ConcurrentHashMap<Properties, Connection> pool = new ConcurrentHashMap<Properties, Connection>();
    private static final Random rand = new Random();
    private static final String size = System.getProperty("org.pjdbc.ConnectionPoolDriver.size", "10");

    public Connection connect (final String URL, final Properties properties) throws SQLException {
	if (!acceptsURL(URL)) return null;
	String subName = URL.split(":")[2];
	Properties props = new Properties(properties);
	props.setProperty("url", subName);
	props.setProperty("index", "" + rand.nextInt(Integer.parseInt(size)));
	if (pool.get(props)==null) pool.putIfAbsent(props, DriverManager.getConnection(subName, properties));
	return pool.get(props);}

    public DriverPropertyInfo[] getPropertyInfo (String URL, Properties info) throws SQLException {
	if (!acceptsURL(URL)) throw new SQLException("Invalid URL");
	String subName = URL.split(":")[2];
	Driver driver = DriverManager.getDriver(subName);
	if (driver==null) throw new SQLException("No valid target driver registered for:  " + subName);
	return driver.getPropertyInfo(subName, info);}

    public int getMajorVersion () {return 1;}

    public int getMinorVersion () {return 0;}

    public boolean jdbcCompliant () {return false;}
    
    public Logger getParentLogger () throws SQLFeatureNotSupportedException {
	throw new SQLFeatureNotSupportedException();}}
