package org.pjdbc.drivers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.pjdbc.util.AbstractProxyDriver;
import org.pjdbc.util.Pool;

public class PoolingDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new PoolingDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    private final Pool<Properties, Connection> pool = new Pool<Properties, Connection>();

    protected Properties getPoolKey (String url, Properties info) throws SQLException {
	Properties key = new Properties(info);
	key.setProperty("url", url);
	return key;}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "pool".equals(subprotocol);}

    protected Connection proxyConnection (final Connection conn, final String url, final Properties info, Driver driver) throws SQLException {
	return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
		public Object invoke (Object proxy, Method method, Object[] args) throws SQLException {
		    if (pool.containsValue(conn)) throw new SQLException();
		    if ("close".equals(method.getName())) {
			System.out.println("close method was called!");
			pool.put(getPoolKey(url, info), conn);
			System.out.println("key = " + getPoolKey(url, info));
			System.out.println("pool contains connection? " + pool.containsValue(conn));
			return null;}
		    try {return method.invoke(conn, args);} catch (Exception e) {throw new SQLException();}}});}

    public Connection connect (String url, Properties info) throws SQLException {
    	if (!acceptsURL(url)) return null;
    	Connection conn = pool.take(getPoolKey(subname(url), info));
    	return (conn!=null) ? conn : super.connect(url, info);}}

