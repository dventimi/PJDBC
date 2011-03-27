package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * <code>SQLHandler</code> determines the contract for a handler that
 * can be used to perform (or handle) some function using a JDBC
 * Connection, along with a SQL query.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public interface SQLHandler {
    /**
     * <code>execute</code> performs some arbitrary activity against
     * the <code>Connection</code> and with the given SQL query.
     *
     * @param sql a <code>String</code> value
     * @param connection a <code>Connection</code> value
     * @return a <code>ResultSet</code> value
     * @exception SQLException if an error occurs
     */
    public ResultSet execute (String sql, Connection connection)
	throws SQLException;
}