package org.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * <code>DevNullDriver</code> sets a <code>SQLHandler</code> that
 * swallows the <code>execute</code> without passing it to the
 * delegate.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public class DevNullDriver extends AbstractInterceptingDriver {
    static {registerDriver(new DevNullDriver());}

    /**
     * <code>getSubProtocol</code> should return "null-intercepting".
     *
     * @return a <code>String</code> value
     */
    public final String getSubProtocol () {
	return "null-intercepting";}

    /**
     * <code>getHandler</code> supplies a <code>SQLHandler</code> that
     * swallows calls to <code>execute</code>.
     *
     * @return a <code>SQLHandler</code> value
     */
    public SQLHandler getHandler () {
	return new SQLHandler () {
	    public ResultSet execute (String sql, Connection connection) {
		return null;}};}
}