package org.pjdbc.lib;

import java.sql.Driver;

/**
 * <code>InterceptingDriver</code> is a JDBC Driver that can interpose
 * between the user and a target JDBC Driver.  Such a driver act as a
 * factory for <code>Connection</code> and <code>Statement</code>
 * objects that intercept calls to the underlying datasource.
 * Specifically, the <code>Statement</code> this driver provides
 * should intercept the <code>Execute</code> method in order to run
 * generic operations against the intercepted datasource, possibly
 * using the passed-in query.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public interface InterceptingDriver extends Driver {
    /**
     * <code>getSubProtocol</code> reports the Driver's supported
     * sub-protocol.  The driver should accept URLs whose protocol is
     * "jdbc", whose subprotocol is the value returned by this
     * method, and whose subname is itself a complete, valid JDBC URL
     * for another registered Driver that is not an
     * <code>InterceptingDriver</code>.  I.e., this driver should
     * accept URLs with the following form.
     *
     *   <em>protocol:subprotocol:subname</em>
     *
     * <em>protocol</em> must be "jdbc".
     * <em>subprotocol</em> must be the value returned by <code>getSubProtocol</code>
     * <em>subname</em> must be an ordinary JDBC URL for a registered Driver.
     *
     * For example, suppose a fictitious <code>FooDriver</code> is
     * registered, whose URLs are of the form
     * <code>jdbc:foo:dbname</code>. Suppose further that an
     * <code>InterceptingDriver</code> is registered whose subprotocol
     * is "magic".  Under these circumstances, this particular
     * <code>InterceptingDriver</code> should accept the following
     * URL.
     *
     *   <code>jdbc:magic:jdbc:foo:dbname</code>
     *
     * @return a <code>String</code> value
     */
    public String getSubProtocol ();}
