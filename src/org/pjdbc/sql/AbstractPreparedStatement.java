package org.pjdbc.sql;

import java.sql.*;
import java.util.*;

public abstract class AbstractPreparedStatement extends AbstractStatement implements PreparedStatement {
    private List<PreparedStatement> delegates;

    AbstractPreparedStatement (Connection conn, PreparedStatement... stmts) throws SQLException {
	super(conn, stmts);
	if (stmts.length==0) throw new SQLException();
	this.delegates = Arrays.asList(stmts);}}
