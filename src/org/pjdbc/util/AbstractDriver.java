package org.pjdbc.util;

import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class AbstractDriver implements Driver {
    public static String protocol (String url) {
	return (""+url).split(":").length > 0 ? (""+url).split(":")[0] : null;}

    public static String subprotocol (String url) {
	return (""+url).split(":").length > 1 ? (""+url).split(":")[1] : null;}

    public static String subname (String url) {
	return (""+url).split(":").length > 2 ? join(slice(Arrays.asList((""+url).split(":")), 2), ":") : null;}

    public static String join (List<String> items, String delimiter) {
	return new ArrayList<String>(items).toString().replace("[", "").replace("]","").replace(", ", delimiter);}

    public static List<String> slice (List<String> items, int... interval) throws IllegalArgumentException {
	if (interval.length==1) return items.subList(interval[0], items.size());
	if (interval.length==2) return items.subList(interval[0], interval[1]);
	throw new IllegalArgumentException("Wrong interval:  " + interval);}

    protected boolean acceptsProtocol (String protocol) {return "jdbc".equals(protocol);}

    protected abstract boolean acceptsSubProtocol (String subprotocol);

    protected abstract boolean acceptsSubName (String subname);

    public boolean acceptsURL (String url) {
	if (!(""+url).matches("jdbc:.*:.*")) return false;
	if (!acceptsProtocol(protocol(""+url))) return false;
	if (!acceptsSubProtocol(subprotocol(""+url))) return false;
	if (!acceptsSubName(subname(""+url))) return false;
	return true;}

    public int getMajorVersion () {return 1;}

    public int getMinorVersion () {return 0;}

    public Logger getParentLogger () throws SQLFeatureNotSupportedException {throw new SQLFeatureNotSupportedException();}

    public DriverPropertyInfo[] getPropertyInfo (String url, Properties info) throws SQLException {return null;}

    public boolean jdbcCompliant () {return false;}}

