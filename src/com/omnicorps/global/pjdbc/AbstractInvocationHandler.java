package com.omnicorps.global.pjdbc; // Generated package name

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Describe class <code>AbstractInvocationHandler</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public abstract class AbstractInvocationHandler<T>
    implements DelegatingInvocationHandler<T> {
    /**
     * Describe variable <code>delegate</code> here.
     *
     */
    protected final T delegate;

    /**
     * Creates a new <code>AbstractInvocationHandler</code> instance.
     *
     * @param delegate a <code>T</code> value
     */
    public AbstractInvocationHandler (T delegate) {
	this.delegate = delegate;
    }
	
    /**
     * Describe <code>invoke</code> method here.
     *
     * @param proxy an <code>Object</code> value
     * @param method a <code>Method</code> value
     * @param args an <code>Object</code> value
     * @return an <code>Object</code> value
     * @exception Throwable if an error occurs
     */
    public Object invoke (Object proxy,
			  Method method,
			  Object[] args)
	throws Throwable {
	return method.invoke(delegate, args);
    }

    /**
     * Describe <code>getDelegate</code> method here.
     *
     * @return a <code>T</code> value
     */
    public T getDelegate () {
    	return this.delegate;
    }
}