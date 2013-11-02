package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.util.AbstractProxyDriver;

public class FilteringDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new FilteringDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "filter".equals(subprotocol);}}




