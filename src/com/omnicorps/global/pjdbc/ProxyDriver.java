package com.omnicorps.global.pjdbc; // Generated package name

/**
 * <code>ProxyDriver</code> is a concrete
 * <code>InterceptingDriver</code> implementation.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public class ProxyDriver extends AbstractInterceptingDriver {
    static {registerDriver(new ProxyDriver());}

    /**
     * <code>getSubProtocol</code> should return "pjdbc".
     *
     * @return a <code>String</code> value
     */
    public String getSubProtocol () {
	return "pjdbc";}

}