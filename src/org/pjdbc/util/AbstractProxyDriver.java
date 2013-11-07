package org.pjdbc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class AbstractProxyDriver extends AbstractDriver {
    protected boolean acceptsSubName (String subname) {
	try {return DriverManager.getDriver(subname)!=null;}
	catch (Exception e) {};
	return false;}

    protected Connection proxyConnection (Connection conn, String url, Properties info) throws SQLException {
	return new AbstractProxyConnection(conn, this, url, info) {
	    public Statement createStatement () throws SQLException {
		return proxyStatement(this, getDelegate().createStatement());}
	    public Statement createStatement (int resultSetType, int resultSetConcurrency) throws SQLException {
		return proxyStatement(this, getDelegate().createStatement(resultSetType, resultSetConcurrency));}
	    public Statement createStatement (int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return proxyStatement(this, getDelegate().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));}};}

    protected Statement proxyStatement (Connection conn, Statement delegate) {
	return new AbstractProxyStatement(delegate, conn) {};}

    protected ResultSet proxyResultSet (Statement stmt, ResultSet delegate) {
	return delegate;}

    public Connection connect (String url, Properties info) throws SQLException {
    	if (!acceptsURL(url)) return null;
	return proxyConnection(DriverManager.getConnection(subname(url)), subname(url), info);}

    public DriverPropertyInfo[] getPropertyInfo (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) throw new SQLException("Invalid url:  " + url);
	Driver driver = DriverManager.getDriver(subname(url));
	if (driver==null) throw new SQLException("No valid target driver registered for:  " + subname(url));
	return driver.getPropertyInfo(subname(url), info);}}
