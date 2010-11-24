package com.omnicorps.global.pjdbc; // Generated package name

import java.lang.reflect.InvocationHandler;

/**
 * Describe interface <code>DelegatingInvocationHandler</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public interface DelegatingInvocationHandler<T>
    extends InvocationHandler {
    /**
     * Describe <code>getDelegate</code> method here.
     *
     * @return a <code>T</code> value
     */
    public <T> T getDelegate ();
}