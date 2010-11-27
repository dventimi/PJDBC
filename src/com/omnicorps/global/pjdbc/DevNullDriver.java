package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;

/**
 * Describe class <code>DevNullDriver</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public class DevNullDriver
    extends AbstractInterceptingDriver {

    static {registerDriver(new DevNullDriver());}

    /**
     * Describe <code>getSubProtocol</code> method here.
     *
     * @return a <code>String</code> value
     */
    public final String getSubProtocol () {
	return "null-intercepting";}

    public SQLHook[] getHooks () {
	return new SQLHook[]{
	    new SQLHook () {
		public String eval (String sql, Connection connection) {
		    return null;}}};}
}