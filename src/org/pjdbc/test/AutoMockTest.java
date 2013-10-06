package org.pjdbc.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.pjdbc.lib.AbstractProxyDriver;

public abstract class AutoMockTest extends AutoTest {
    protected interface ScratchLog {
	public List getLog ();}

    protected interface MockDriver extends Driver, ScratchLog {}

    protected interface MockConnection extends Connection, ScratchLog {}

    protected MockDriver getMockDriver (final String subprotocol) {
	return (MockDriver)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{MockDriver.class}, new InvocationHandler () {
		private List<String> log = new ArrayList<String>();
		private boolean acceptsURL (String url) {return (subprotocol+"").equals(AbstractProxyDriver.subprotocol(url));}
		private Connection connect (String url) {return getMockConnection(subprotocol);}
		public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
		    if ("getLog".equals(method.getName())) return log;
		    log.add(method.getName());
		    if ("acceptsURL".equals(method.getName())) return acceptsURL(""+args[0]);
		    if ("connect".equals(method.getName())) return connect(""+args[0]);
		    return null;}});}

    protected MockConnection getMockConnection (final String catalog) {
	return (MockConnection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{MockConnection.class}, new InvocationHandler () {
		private List<String> log = new ArrayList<String>();
		public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
		    if ("getLog".equals(method.getName())) return log;
		    if ("getCatalog".equals(method.getName())) return catalog;
		    log.add(method.getName());
		    return null;}});}}
