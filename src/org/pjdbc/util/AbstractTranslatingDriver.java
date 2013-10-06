package org.pjdbc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

public abstract class AbstractTranslatingDriver extends AbstractProxyDriver implements TranslatingDriver {
    public Connection connect (String url, Properties info) throws SQLException {
	if (!acceptsURL(url)) return null;
	return proxyConnection(new ConnectionHandler(DriverManager.getConnection(subname(url), info)));}

    private class ConnectionHandler implements InvocationHandler {
    	private Connection delegate;
    	public ConnectionHandler (Connection delegate) {delegate = delegate;}
    	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
	    if ("createStatement".equals(method.getName()))
		return proxyStatement(new StatementHandler((Statement)method.invoke(this.delegate, args), getTranslator()));
	    return method.invoke(delegate, args);}}

    private class StatementHandler implements InvocationHandler {
	private Statement delegate;
	private Translator handler;
	public StatementHandler (Statement delegate, Translator handler) {
	    this.delegate = delegate;
	    this.handler = handler;}
	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
	    if ("execute".equals(method.getName())) return handler.execute((String)args[0], this.delegate.getConnection());
	    return method.invoke(delegate, args);}}}
