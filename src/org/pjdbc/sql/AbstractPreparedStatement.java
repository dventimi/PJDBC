package org.pjdbc.sql;

import java.sql.*;
import java.util.*;

public abstract class AbstractPreparedStatement extends AbstractStatement implements PreparedStatement {
    private PreparedStatement d;

    AbstractPreparedStatement (Connection conn, PreparedStatement stmt) throws SQLException {
	super(conn, stmt);
	this.d = stmt;}}
