package com.omnicorps.global.pjdbc; // Generated package name

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Describe class <code>GenericProxyFactory</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public class GenericProxyFactory<T> {
    /**
     * Describe <code>createProxy</code> method here.
     *
     * @param ifaces a <code>Class<T></code> value
     * @param handler an <code>InvocationHandler</code> value
     * @return a <code>T</code> value
     */
    public static<T> T createProxy (Class<T>[] ifaces, 
				    InvocationHandler handler) {
	return (T)
	    Proxy.newProxyInstance(ifaces.getClass().getClassLoader(),
				   ifaces,
				   handler);
    }

    /**
     * Describe <code>createProxy</code> method here.
     *
     * @param clazz a <code>Class<T></code> value
     * @param handler an <code>InvocationHandler</code> value
     * @return a <code>T</code> value
     */
    public static<T> T createProxy (Class<T> clazz, 
				    InvocationHandler handler) {
	return (T)
	    Proxy.newProxyInstance(clazz.getClass().getClassLoader(),
				   clazz.getInterfaces(),
				   handler);
    }
}