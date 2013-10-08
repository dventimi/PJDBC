package org.pjdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLHandler {
    public ResultSet handle (String sql, Connection connection) throws SQLException;}
