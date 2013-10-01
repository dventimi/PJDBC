package org.pjdbc.lib;

import java.sql.Driver;

public abstract class AbstractProxyDriver implements Driver {
    public boolean acceptsURL (String URL) {
	String[] parts = URL.split(":");
	return parts[0].equals("jdbc") && parts[1].equals("pjdbc");}}

    // public final Connection connect (final String URL, final Properties properties) throws SQLException {
    // 	Class[] api = new Class[]{Connection.class};
    // 	Connection connection = DriverManager.getConnection("jdbc:" + (new ProxyUrl(URL)).getSubName(), properties);
    // 	InvocationHandler handler = new ConnectionInvocationHandler(connection);
    // 	return (Connection)Proxy.newProxyInstance(this.getClass().getClassLoader(), api, handler);}

    // private class ConnectionInvocationHandler implements InvocationHandler {
    // 	private Connection delegate = null;
    // 	public ConnectionInvocationHandler (Connection delegate) {this.delegate = delegate;}
    // 	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
    // 	    if (method.getName().equals("createStatement")) {
    // 		Class[] api = new Class[]{Statement.class};
    // 		Statement statement = (Statement)method.invoke(this.delegate, args);
    // 		InvocationHandler handler = new StatementInvocationHandler(statement);
    // 		return Proxy.newProxyInstance(this.getClass().getClassLoader(), api, getHandler(method.getName());}
    // 	    return method.invoke(delegate, args);}}

    // private class StatementInvocationHandler implements InvocationHandler {
    // 	private Statement delegate = null;
    // 	public StatementInvocationHandler (Statement delegate) {this.delegate = delegate;}
    // 	public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
    // 	    if (method.getName().equals("execute")) getHandler().execute((String)args[0], this.delegate.getConnection());
    // 	    return method.invoke(delegate, args);}}}
