package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;

/**
 * Describe class <code>IdentityInterceptingDriver</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public class IdentityDriver 
    extends AbstractInterceptingDriver {

    static {registerDriver(new IdentityDriver());}

    /**
     * Describe <code>getSubProtocol</code> method here.
     *
     * @return a <code>String</code> value
     */
    public final String getSubProtocol () {
	return "identity-intercepting";}

    public SQLHook[] getHooks () {
	return new SQLHook[]{
	    new SQLHook () {
		public String eval (String sql, Connection connection) {
		    return sql;}}};}

}