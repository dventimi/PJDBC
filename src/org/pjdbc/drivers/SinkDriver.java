package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.util.AbstractProxyDriver;

public class SinkDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new SinkDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "sink".equals(subprotocol);}}

