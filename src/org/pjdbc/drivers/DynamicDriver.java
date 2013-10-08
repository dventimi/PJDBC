package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.util.AbstractTranslatingDriver;
import org.pjdbc.util.Translator;

public class DynamicDriver extends AbstractTranslatingDriver {
    static {try {DriverManager.registerDriver(new DynamicDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "identity".equals(subprotocol);}

    public Translator getTranslator () throws Exception {
	return (Translator)Class.forName(System.getProperty("org.pjdbc.DynamicDriver.Translator")).newInstance();}}



