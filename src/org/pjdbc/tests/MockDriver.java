package org.pjdbc.tests;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import org.pjdbc.util.AbstractDriver;

public class MockDriver extends AbstractDriver {
    static {try {DriverManager.registerDriver(new MockDriver());} catch (Exception e) {throw new RuntimeException(e);}}
    static {System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s\n");}

    private static class MyStreamHandler extends StreamHandler {
	private OutputStream out;
	private Formatter formatter;
	public MyStreamHandler () {super();}
	public MyStreamHandler (OutputStream out, Formatter formatter) {
	    super(out, formatter);
	    this.formatter = formatter;
	    this.out = out;}
	public OutputStream getStream () {return this.out;}
	public Formatter getFormatter () {return this.formatter;}}

    private static Map<String, MyStreamHandler> handlers = new HashMap<String, MyStreamHandler>();
    
    public static String getLog (String url) {
	if (handlers.containsKey(url)) {
	    handlers.get(url).flush();
	    return (""+handlers.get(url).getStream()).trim();}
	return "";}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "mock".equals(subprotocol);}

    protected boolean acceptsSubName (String subname) {
	return true;}

    public boolean acceptsURL (String url) {
	if (super.acceptsURL(url)) return true;
	return false;}

    private static class LoggingInvocationHandler implements InvocationHandler {
	private Logger l;
	public LoggingInvocationHandler (Logger log) {
	    this.l = log;}
	public Object invoke (Object proxy, Method method, Object[] args) {
	    l.info(method.getName() + (args!=null && args.length>0 ? Arrays.asList(args) : new ArrayList()));
	    return null;}}

    public Connection connect (String url, Properties info) throws SQLException {
    	if (!acceptsURL(url)) return null;
	handlers.put(url, new MyStreamHandler(new ByteArrayOutputStream(), new SimpleFormatter()));
	final Logger l = Logger.getLogger(url);
	l.setLevel(Level.INFO);
	l.setUseParentHandlers(false);
	l.addHandler(handlers.get(url));
	return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler () {
		public Object invoke (Object proxy, Method method, Object[] args) {
		    if ("createStatement".equals(method.getName()))
			return (Statement)Proxy.newProxyInstance(getClass().getClassLoader(),
								 new Class[]{Statement.class},
								 new LoggingInvocationHandler(l));
		    if ("prepareCall".equals(method.getName()))
			return (Statement)Proxy.newProxyInstance(getClass().getClassLoader(), 
								 new Class[]{CallableStatement.class},
								 new LoggingInvocationHandler(l));
		    if ("prepareStatement".equals(method.getName()))
			return (Statement)Proxy.newProxyInstance(getClass().getClassLoader(), 
								 new Class[]{PreparedStatement.class},
								 new LoggingInvocationHandler(l));
		    return null;}});}}
