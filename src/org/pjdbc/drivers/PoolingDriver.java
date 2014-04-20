package org.pjdbc.drivers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.pjdbc.util.AbstractProxyDriver;

public class PoolingDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new PoolingDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    private static final ConcurrentHashMap<Properties, BlockingQueue<Connection>> pools = new ConcurrentHashMap<Properties, BlockingQueue<Connection>>();

    protected Properties getPoolKey (String url, Properties info) throws SQLException {
	Properties key = new Properties(info);
	key.setProperty("url", url);
	return key;}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "pool".equals(subprotocol);}

    protected Connection proxyConnection (final Connection conn, final String url, final Properties info, Driver driver) throws SQLException {
    	return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
    		public Object invoke (Object proxy, Method method, Object[] args) throws SQLException {
		    if ("close".equals(method.getName())) {
			pools.get(getPoolKey(url, info)).add(conn); 
			System.out.println("\nadding\n");
			System.out.println(pools.get(getPoolKey(url, info)).contains(conn));
			return proxy;}
		    if (!"close".equals(method.getName())) 
			if (pools.get(getPoolKey(url, info)).contains(conn)) {
			    System.out.println(method.getName());
			    System.out.println(pools.get(getPoolKey(url, info)).contains(conn));
			    System.out.println("About to throw a SQLException");
			    throw new SQLException("here it comes");}
		    if ("toString".equals(method.getName())) return conn.toString();
		    if ("equals".equals(method.getName())) return proxy==args[0];
		    try {return method.invoke(conn, args);} catch (Exception e) {throw new SQLException();}}});}

    public Connection connect (String url, Properties info) throws SQLException {
    	if (!acceptsURL(url)) return null;
	Properties key = getPoolKey(subname(url), info);
	Connection conn = null;
	if (!pools.containsKey(key)) pools.putIfAbsent(key, new LinkedBlockingQueue<Connection>());
	try {conn = pools.get(key).poll(1L, TimeUnit.SECONDS);} catch (InterruptedException e) {}
	if (conn!=null) return conn;
	return proxyConnection(conn!=null ? conn : DriverManager.getConnection(subname(url)), subname(url), info, this);}}

