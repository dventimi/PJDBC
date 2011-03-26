package com.omnicorps.global.pjdbc; // Generated package name

import java.util.LinkedList;
import java.sql.SQLException;
import java.util.Arrays;

public class JDBCUrl {
    public static final String PROTOCOL = "jdbc";
    public static final String SEPARATOR = ":";

    private String protocol;
    private String subProtocol;
    private String subName;

    public String getProtocol () {
	return this.protocol;}

    public String getSubProtocol () {
	return this.subProtocol;}

    public String getSubName () {
	return this.subName;}

    public String getUrl () {
	return protocol + SEPARATOR + subProtocol + SEPARATOR + subName;}

    public JDBCUrl (final String URL) throws SQLException {
	String[] parts = parseURL(URL);
	this.protocol = parts[0];
	if (!this.protocol.equals(PROTOCOL)) 
	    throw new SQLException("Invalid JDBC URL. Protocol in " + URL + " is " + this.protocol + " instead of jdbc.");
	this.subProtocol = parts[1];
	this.subName = parts[2];}
	
    public static String[] parseURL (final String URL) throws SQLException {
	LinkedList<String> components = new LinkedList<String>(Arrays.asList(("" + URL).split(SEPARATOR)));
	if (components.size() < 3) throw new SQLException("Invalid JDBC URL:  " + URL);
	String[] parts = new String[3];
	parts[0] = components.poll();
	parts[1] = components.poll();
	parts[2] = components.poll();
	for (String token : components) parts[2] += SEPARATOR + token;
	return parts;}
}

