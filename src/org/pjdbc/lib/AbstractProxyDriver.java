package org.pjdbc.lib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractProxyDriver implements ProxyDriver {
    public static String protocol (String url) {
	return url.split(":")[0];}

    public static String subprotocol (String url) {
	return url.split(":")[1];}

    public static String subname (String url) {
	return join(slice(Arrays.asList(url.split(":")), 2), ":");}

    public static String join (List items, String delimiter) {
	return new ArrayList(items).toString().replace("[", "").replace("]","").replace(", ", delimiter);}

    public static List slice (List items, int... interval) throws IllegalArgumentException {
	if (interval.length==1) return items.subList(interval[0], items.size());
	if (interval.length==2) return items.subList(interval[0], interval[1]);
	throw new IllegalArgumentException("Wrong interval:  " + interval);}

    protected Connection proxyConnection (InvocationHandler h) {
	return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Connection.class}, h);}

    public boolean acceptsProtocol (String protocol) {
	return "jdbc".equals(protocol);}

    public boolean acceptsURL (String url) {
	return acceptsProtocol(protocol(url)) && acceptsSubProtocol(subprotocol(url));}}
