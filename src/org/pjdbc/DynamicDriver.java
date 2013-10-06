package org.pjdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.lib.AbstractTranslatingDriver;
import org.pjdbc.lib.Translator;

public class DynamicDriver extends AbstractTranslatingDriver {
    private final int majorVersion = 1;
    private final int minorVersion = 1;
    private final boolean jdbcCompliant = false;

    public boolean acceptsSubProtocol (String subprotocol) {
	return "identity".equals(subprotocol);}

    public Translator getTranslator () throws Exception {
	return (Translator)Class.forName(System.getProperty("org.pjdbc.DynamicDriver.Translator")).newInstance();}}



