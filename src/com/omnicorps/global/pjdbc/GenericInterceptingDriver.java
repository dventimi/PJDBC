package com.omnicorps.global.pjdbc; // Generated package name

import java.util.ArrayList;

/**
 * Describe class <code>GenericInterceptingDriver</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public class GenericInterceptingDriver 
    extends AbstractInterceptingDriver {

    /**
     * Describe <code>getSubProtocol</code> method here.
     *
     * @return a <code>String</code> value
     */
    public final String getSubProtocol () {
	return "generic-intercepting";
    }

    static {
	registerDriver(new GenericInterceptingDriver());
    }
}