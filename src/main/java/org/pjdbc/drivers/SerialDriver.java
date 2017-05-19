package org.pjdbc.drivers;

import java.sql.*;
import org.pjdbc.sql.*;

public class SerialDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new SerialDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "serial".equals(subprotocol);}}
