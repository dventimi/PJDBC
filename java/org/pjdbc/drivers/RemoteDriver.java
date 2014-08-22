package org.pjdbc.drivers;

import java.rmi.registry.*;
import java.sql.*;
import java.util.*;
import org.pjdbc.rmi.*;
import org.pjdbc.util.*;
import java.lang.reflect.*;

public class RemoteDriver extends AbstractDriver {
    private static class DriverHandler implements InvocationHandler {
	private DriverRemoteProxy driverDelegate;
	DriverHandler (DriverRemoteProxy delegate) {driverDelegate = delegate;}
	public Object invoke (Object proxy, Method method, Object[] args) throws SQLException {
	    try {
		return method.invoke(driverDelegate, args);}
	    catch (Exception e) {throw new SQLException(e);}}}

    private static class ConnectionHandler implements InvocationHandler {
	private ConnectionRemoteProxy connectionDelegate;
	ConnectionHandler (ConnectionRemoteProxy delegate) {connectionDelegate = delegate;}
	public Object invoke (Object proxy, Method method, Object[] args) throws SQLException {
	    try {
		if (method.getReturnType().isInstance(Statement.class)) return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Statement.class}, new StatementHandler((StatementRemoteProxy)ConnectionRemoteProxy.class.getMethod(method.getName(), method.getParameterTypes()).invoke(connectionDelegate, args)));
		if (method.getReturnType().isInstance(PreparedStatement.class)) return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{PreparedStatement.class}, new PreparedStatementHandler((PreparedStatementRemoteProxy)ConnectionRemoteProxy.class.getMethod(method.getName(), method.getParameterTypes()).invoke(connectionDelegate, args)));
		return method.invoke(connectionDelegate, args);}
	    catch (Exception e) {throw new SQLException(e);}}}

    private static class StatementHandler implements InvocationHandler {
	private StatementRemoteProxy statementDelegate;
	StatementHandler (StatementRemoteProxy delegate) {statementDelegate = delegate;}
	public Object invoke (Object proxy, Method method, Object[] args) throws SQLException {
	    try {
		return method.invoke(statementDelegate, args);}
	    catch (Exception e) {throw new SQLException(e);}}}

    private static class PreparedStatementHandler implements InvocationHandler {
	private PreparedStatementRemoteProxy statementDelegate;
	PreparedStatementHandler (PreparedStatementRemoteProxy delegate) {statementDelegate = delegate;}
	public Object invoke (Object proxy, Method method, Object[] args) throws SQLException {
	    try {
		return method.invoke(statementDelegate, args);}
	    catch (Exception e) {throw new SQLException(e);}}}

    private static class ResultSetHandler implements InvocationHandler {
	private ResultSetRemoteProxy resultSetDelegate;
	ResultSetHandler (ResultSetRemoteProxy delegate) {resultSetDelegate = delegate;}
	public Object invoke (Object proxy, Method method, Object[] args) throws SQLException {
	    try {
		return method.invoke(resultSetDelegate, args);}
	    catch (Exception e) {throw new SQLException(e);}}}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "remote".equals(subprotocol);}

    protected boolean acceptsSubName (String subName) {
	return true;}

    public Connection connect (final String url, final Properties info) throws SQLException {
	try {
	    DriverRemoteProxy driverDelegate = (DriverRemoteProxy)LocateRegistry.getRegistry().lookup("get the binding name from the url");
	    Driver driver = (Driver)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Driver.class}, new DriverHandler(driverDelegate));
	    return driver.connect("get the destination jdbc url on the other side, which should be nested within the url we just received", info);}
	catch (Exception e) {throw new SQLException();}}}
