package org.pjdbc.util;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AutoMockTest extends AutoTest {
    private final StringBuffer logger;

    public AutoMockTest () {
	this.logger = new StringBuffer();}

    public String getLog () {
	return this.logger.toString().trim();}

    public Driver getMockDriver (String subprotocol) {
	return (Driver)Proxy.newProxyInstance(getClass().getClassLoader(), 
					      new Class[]{Driver.class}, 
					      new DriverHandler(subprotocol, this.logger));}

    protected abstract class LoggingHandler implements InvocationHandler {
	protected StringBuffer logger;
	public LoggingHandler (StringBuffer logger) {this.logger = logger;}
	protected void log (Object proxy, Method method, Object[] args) {
	    this.logger.append(method.getName() + (args!=null ? Arrays.asList(args) : new ArrayList()) + "\n");}}

    protected class DriverHandler extends LoggingHandler {
	private String subprotocol = "";
	private boolean acceptsURL (String url) {
	    return (subprotocol+"").equals(AbstractProxyDriver.subprotocol(url));}
	private Connection connect (String url) {
	    return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), 
						      new Class[]{Connection.class}, 
						      new ConnectionHandler(url, this.logger));}
	public DriverHandler (String subprotocol, StringBuffer logger) {
	    super(logger);
	    this.subprotocol = subprotocol;}
	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
	    log(proxy, method, args);
	    if ("acceptsURL".equals(method.getName())) return acceptsURL(""+args[0]);
	    if ("connect".equals(method.getName())) return connect(""+args[0]);
	    return null;}}

    protected class ConnectionHandler extends LoggingHandler {
	private String url = "";
	private Statement createStatement () {
	    return (Statement)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Statement.class}, new StatementHandler(this.logger));}
	public ConnectionHandler (String url, StringBuffer logger) {
	    super(logger);
	    this.url = url;}
	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
	    log(proxy, method, args);
	    if ("createStatement".equals(method.getName())) return createStatement();
	    return null;}}

    private class StatementHandler extends LoggingHandler {
	public StatementHandler (StringBuffer logger) {
	    super(logger);}
	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
	    log(proxy, method, args);
	    return null;}}
}
