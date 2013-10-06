package org.pjdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import org.pjdbc.lib.AbstractProxyDriver;

public class BasicProxyDriver extends AbstractProxyDriver {
    public boolean acceptsSubProtocol (String subprotocol) {
	return "basic".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	return proxyConnection(new ConnectionHandler(DriverManager.getConnection(subname(url), info)));}

    public int getMajorVersion () {
	return 1;}

    public int getMinorVersion () {
	return 0;}

    public boolean jdbcCompliant () {
	return false;}
    
    private class ConnectionHandler implements InvocationHandler {
    	private Connection delegate = null;
    	public ConnectionHandler (Connection delegate) {this.delegate = delegate;}
    	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {return method.invoke(delegate, args);}}}
