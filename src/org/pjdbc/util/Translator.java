package org.pjdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Translator {
    public ResultSet execute (String sql, Connection connection) throws SQLException;}
