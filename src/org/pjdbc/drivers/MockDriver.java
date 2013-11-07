package org.pjdbc.drivers;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
import org.pjdbc.util.AbstractDriver;

public class MockDriver extends AbstractDriver {
    static {try {DriverManager.registerDriver(new MockDriver());} catch (Exception e) {throw new RuntimeException(e);}}
    static {System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s\n");}

    public static class MyPrintWriter extends PrintWriter {
	private OutputStream out;
	public MyPrintWriter (OutputStream out) {super(out); this.out = out;}
	public OutputStream getStream () {return this.out;}}

    private static class LoggingInvocationHandler implements InvocationHandler {
	private PrintWriter l;
	public LoggingInvocationHandler (PrintWriter log) {
	    this.l = log;}
	public Object invoke (Object proxy, Method method, Object[] args) {
	    l.println(method.getName() + (args!=null && args.length>0 ? Arrays.asList(args) : new ArrayList()));
	    return null;}}

    private static Map<String, MyPrintWriter> logs = new HashMap<String, MyPrintWriter>();
    
    public static String getLog (String url) {
	if (logs.containsKey(url)) {
	    logs.get(url).flush();
	    return logs.get(url).getStream().toString().trim();}
	return "";}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "mock".equals(subprotocol);}

    protected boolean acceptsSubName (String subname) {
	return true;}

    public Connection connect (final String url, Properties info) throws SQLException {
    	if (!acceptsURL(url)) return null;
	logs.put(url, new MyPrintWriter(new ByteArrayOutputStream()));
	final PrintWriter l = logs.get(url);
	return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler () {
		public Object invoke (Object proxy, Method method, Object[] args) {
		    if ("createStatement".equals(method.getName()))
			return (Statement) 
			    Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Statement.class}, new LoggingInvocationHandler(l));
		    if ("prepareCall".equals(method.getName()))
			return (Statement)
			    Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{CallableStatement.class}, new LoggingInvocationHandler(l));
		    if ("prepareStatement".equals(method.getName()))
			return (Statement)
			    Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{PreparedStatement.class}, new LoggingInvocationHandler(l));
		    if ("getMetaData".equals(method.getName()))
			return (DatabaseMetaData)
			    Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{DatabaseMetaData.class}, new InvocationHandler() {
				    public Object invoke (Object proxy, Method method, Object[] args) {
					return ("getURL".equals(method.getName())) ? url : null;}});
		    return null;}});}}
