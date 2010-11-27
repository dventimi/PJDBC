package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLHook {
    public String[] eval (String[] sql, Connection connection)
	throws SQLException;
}