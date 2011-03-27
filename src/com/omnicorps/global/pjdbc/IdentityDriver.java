package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * <code>IdentityInterceptingDriver</code> sets a
 * <code>SQLHandler</code> that transparently passes the
 * <code>execute</code> on to the delegate, unmolested.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public class IdentityDriver extends AbstractInterceptingDriver {
    static {registerDriver(new IdentityDriver());}

    /**
     * <code>getSubProtocol</code> should return
     * "identity-intercepting".
     *
     * @return a <code>String</code> value
     */
    public final String getSubProtocol () {
	return "identity-intercepting";}

    /**
     * <code>getHandler</code> supplies a handler that doesn't touch
     * the <code>execute</code> call.
     *
     * @return a <code>SQLHandler</code> value
     */
    public SQLHandler getHandler () {
	return new SQLHandler() {
	    public ResultSet execute (String sql, Connection connection) {
		return null;}};}

}