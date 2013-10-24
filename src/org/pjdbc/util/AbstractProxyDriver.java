package org.pjdbc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class AbstractProxyDriver implements ProxyDriver {
    public static String protocol (String url) {
	return (""+url).split(":").length > 0 ? (""+url).split(":")[0] : null;}

    public static String subprotocol (String url) {
	return (""+url).split(":").length > 1 ? (""+url).split(":")[1] : null;}

    public static String subname (String url) {
	return (""+url).split(":").length > 2 ? join(slice(Arrays.asList((""+url).split(":")), 2), ":") : null;}

    public static String join (List items, String delimiter) {
	return new ArrayList(items).toString().replace("[", "").replace("]","").replace(", ", delimiter);}

    public static List slice (List items, int... interval) throws IllegalArgumentException {
	if (interval.length==1) return items.subList(interval[0], items.size());
	if (interval.length==2) return items.subList(interval[0], interval[1]);
	throw new IllegalArgumentException("Wrong interval:  " + interval);}

    protected Connection proxyConnection (InvocationHandler h) {
	return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Connection.class}, h);}

    protected Statement proxyStatement (InvocationHandler h) {
	return (Statement)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Statement.class}, h);}

    public int getMajorVersion () {
	return 1;}

    public int getMinorVersion () {
	return 0;}

    public boolean jdbcCompliant () {
	return false;}

    public boolean acceptsProtocol (String protocol) {
	return "jdbc".equals(protocol);}

    public boolean acceptsURL (String url) {
	if (!acceptsProtocol(protocol(""+url))) return false;
	if (!acceptsSubProtocol(subprotocol(""+url))) return false;
	if (!(""+url).matches("jdbc:.*:.*")) return false;
	return true;}

    public DriverPropertyInfo[] getPropertyInfo (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) throw new SQLException("Invalid url:  " + url);
	Driver driver = DriverManager.getDriver(subname(url));
	if (driver==null) throw new SQLException("No valid target driver registered for:  " + subname(url));
	return driver.getPropertyInfo(subname(url), info);}

    public Logger getParentLogger () throws SQLFeatureNotSupportedException {
	throw new SQLFeatureNotSupportedException();}}


