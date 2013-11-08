package org.pjdbc.drivers;

import java.sql.DriverManager;
import org.pjdbc.util.AbstractProxyDriver;

public class CatDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new CatDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "cat".equals(subprotocol);}}

