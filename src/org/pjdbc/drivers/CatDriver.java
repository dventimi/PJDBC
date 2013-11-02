package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.pjdbc.util.AbstractProxyDriver;
import org.pjdbc.util.AbstractProxyStatement;

public class CatDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new CatDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "cat".equals(subprotocol);}}

