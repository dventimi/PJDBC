package org.pjdbc.drivers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.pjdbc.util.AbstractDriver;

public class MockDriver extends AbstractDriver {
    static {try {DriverManager.registerDriver(new MockDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    private static final Logger LOGGER = Logger.getLogger(LoggingDriver.class.getName());
    
    static {LOGGER.setLevel(Level.INFO);}

    public Connection connect (String url, Properties info) throws SQLException {
    	if (!acceptsURL(url)) return null;
	return (Connection)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Driver.class}, new InvocationHandler () {
		public Object invoke (Object proxy, Method method, Object[] args) {
		    LOGGER.info(method.getName()+Arrays.asList(args));
		    return null;}});}}
		    
