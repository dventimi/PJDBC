package org.pjdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <code>ProxyDriver</code> is a concrete
 * <code>InterceptingDriver</code> implementation.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public class ProxyDriver extends org.pjdbc.lib.AbstractInterceptingDriver {
    static {try{DriverManager.registerDriver(new ProxyDriver());} catch(SQLException e) {throw new RuntimeException();}}

    /**
     * <code>getSubProtocol</code> should return "pjdbc".
     *
     * @return a <code>String</code> value
     */
    public String getSubProtocol () {
	return "pjdbc";}}
