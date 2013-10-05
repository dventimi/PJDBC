package org.pjdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import org.pjdbc.lib.AbstractProxyDriver;

public class PassThroughDriver extends AbstractProxyDriver {
    public boolean acceptsSubProtocol (String subprotocol) {
	return "passthrough".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	return proxyConnection(new ConnectionInvocationHandler(DriverManager.getConnection(subname(url), info)));}

    public DriverPropertyInfo[] getPropertyInfo (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) throw new SQLException("Invalid url:  " + url);
	Driver driver = DriverManager.getDriver(subname(url));
	if (driver==null) throw new SQLException("No valid target driver registered for:  " + subname(url));
	return driver.getPropertyInfo(subname(url), info);}

    public int getMajorVersion () {
	return 1;}

    public int getMinorVersion () {
	return 0;}

    public boolean jdbcCompliant () {
	return false;}
    
    public Logger getParentLogger () throws SQLFeatureNotSupportedException {
	throw new SQLFeatureNotSupportedException();}

    private class ConnectionInvocationHandler implements InvocationHandler {
    	private Connection delegate = null;
    	public ConnectionInvocationHandler (Connection delegate) {this.delegate = delegate;}
    	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {return method.invoke(delegate, args);}}}
