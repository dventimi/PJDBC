package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Describe class <code>IdentityInterceptingDriver</code> here.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public class IdentityDriver extends AbstractInterceptingDriver {
    static {registerDriver(new IdentityDriver());}

    /**
     * Describe <code>getSubProtocol</code> method here.
     *
     * @return a <code>String</code> value
     */
    public final String getSubProtocol () {
	return "identity-intercepting";}

    public SQLHandler getHandler () {
	return new SQLHandler() {
	    public ResultSet execute (String sql, Connection connection) {
		return null;}};}

}