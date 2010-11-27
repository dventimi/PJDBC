package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;

public interface SQLHook {
    public String eval (String sql, Connection connection);
}