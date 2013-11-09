package org.pjdbc.drivers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import org.pjdbc.util.AbstractProxyDriver;
import org.pjdbc.util.SmartMultiMap;

public class PoolingDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new PoolingDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    private final Map<Properties, Queue<Connection>> pools = new HashMap<Properties, Queue<Connection>>();

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
			pools.get(getPoolKey(url, info)).add((Connection)proxy);
    			System.out.println("close method was called!");
    			System.out.println("key = " + getPoolKey(url, info));
    			return null;}
    		    try {return method.invoke(conn, args);} catch (Exception e) {throw new SQLException();}}});}

    public Connection connect (String url, Properties info) throws SQLException {
    	if (!acceptsURL(url)) return null;
	Properties key = getPoolKey(subname(url), info);
	if (!pools.containsKey(key)) pools.put(key, new LinkedList<Connection>());
	Queue<Connection> pool = pools.get(key);
	Connection conn = pool.poll();
    	return (conn!=null) ? conn : super.connect(url, info);}}

