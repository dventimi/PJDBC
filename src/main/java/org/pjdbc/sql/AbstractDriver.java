package org.pjdbc.sql;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

public abstract class AbstractDriver implements Driver {
    protected String protocol (String url) {
	return (""+url).split(":").length > 0 ? (""+url).split(":")[0].trim() : null;}

    protected String subprotocol (String url) {
	return (""+url).split(":").length > 1 ? (""+url).split(":")[1].trim() : null;}

    protected String subname (String url) {
	return (""+url).split(":").length > 2 ? join(slice(Arrays.asList((""+url).split(":")), 2), ":") : null;}

    protected String join (List<String> items, String delimiter) {
	return new ArrayList<String>(items).toString().replace("[", "").replace("]","").replace(", ", delimiter);}

    protected List<String> slice (List<String> items, int... interval) throws IllegalArgumentException {
	if (interval.length==1) return items.subList(interval[0], items.size());
	if (interval.length==2) return items.subList(interval[0], interval[1]);
	throw new IllegalArgumentException("Wrong interval:  " + interval);}

    protected boolean acceptsProtocol (String protocol) {return "jdbc".equals(protocol);}

    protected abstract boolean acceptsSubProtocol (String subprotocol);

    protected abstract boolean acceptsSubName (String subname);

    @Override
    public boolean acceptsURL (String url) {
	String clean = (""+url).trim().replaceAll("(?s)\\s","").toLowerCase();
	if (!clean.matches("(?is)jdbc\\s*:.*:.*")) return false;
	if (!acceptsProtocol(protocol(clean))) return false;
	if (!acceptsSubProtocol(subprotocol(clean))) return false;
	if (!acceptsSubName(subname(clean))) return false;
	return true;}

    @Override
    public int getMajorVersion () {return 1;}

    @Override
    public int getMinorVersion () {return 0;}

    @Override
    public Logger getParentLogger () throws SQLFeatureNotSupportedException {throw new SQLFeatureNotSupportedException();}

    @Override
    public DriverPropertyInfo[] getPropertyInfo (String url, Properties info) throws SQLException {return new DriverPropertyInfo[]{};}

    @Override
    public boolean jdbcCompliant () {return false;}}
