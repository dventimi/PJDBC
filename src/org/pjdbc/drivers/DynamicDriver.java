package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pjdbc.lib.AbstractTranslatingDriver;
import org.pjdbc.lib.Translator;

public class DynamicDriver extends AbstractTranslatingDriver {
    public int getMajorVersion () {
	return 1;}

    public int getMinorVersion () {
	return 0;}

    public boolean jdbcCompliant () {
	return false;}

    public boolean acceptsSubProtocol (String subprotocol) {
	return "identity".equals(subprotocol);}

    public Translator getTranslator () throws Exception {
	return (Translator)Class.forName(System.getProperty("org.pjdbc.DynamicDriver.Translator")).newInstance();}}



