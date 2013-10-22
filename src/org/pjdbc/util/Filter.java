package org.pjdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Filter {
    public ResultSet apply (String sql, Connection connection) throws SQLException;}
