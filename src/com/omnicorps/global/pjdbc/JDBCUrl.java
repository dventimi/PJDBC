package com.omnicorps.global.pjdbc; // Generated package name

import java.util.LinkedList;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * <code>JDBCUrl</code> represents a JDBC Url.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public class JDBCUrl {
    /**
     * <code>PROTOCOL</code> the protocol for JDBC, which always is
     * "jdbc".
     *
     */
    public static final String PROTOCOL = "jdbc";

    /**
     * <code>SEPARATOR</code> the component separator for JDBC, which
     * always is a colon (:).
     *
     */
    public static final String SEPARATOR = ":";

    /**
     * <code>protocol</code>
     *
     */
    protected String protocol;

    /**
     * <code>subProtocol</code>
     *
     */
    protected String subProtocol;

    /**
     * <code>subName</code>
     *
     */
    protected String subName;

    /**
     * <code>components</code>
     *
     */
    protected String[] components;

    /**
     * <code>getComponents</code> returns the individual componenents
     * of the URL.
     *
     * @return a <code>String[]</code> value
     */
    public String[] getComponents () {
	return this.components;}

    /**
     * <code>getProtocol</code> reports the URL's protocol component.
     *
     * @return a <code>String</code> value
     */
    public String getProtocol () {
	return this.protocol;}

    /**
     * <code>getSubProtocol</code> reports the URL's subprotocol
     * component.
     *
     * @return a <code>String</code> value
     */
    public String getSubProtocol () {
	return this.subProtocol;}

    /**
     * <code>getSubName</code> reports the URL's subname component.
     *
     * @return a <code>String</code> value
     */
    public String getSubName () {
	return this.subName;}

    /**
     * <code>getUrl</code> reports the URL's whole JDBC URL, in String
     * form, complete with separators.
     *
     * @return a <code>String</code> value
     */
    public String getUrl () {
	return protocol + SEPARATOR + subProtocol + SEPARATOR + subName;}


    /**
     * Creates a <code>JDBCUrl</code> instance, but since no URL is
     * passed in, it will be empty, with no protocol, subprotocol, or
     * subname.
     *
     */
    public JDBCUrl () {}

    /**
     * Creates a <code>JDBCUrl</code> instance, parsing the passed in
     * JDBC URL in the process.  Note that if the URL cannot be
     * parsed, a <code>SQLException</code> will be thrown.
     *
     * @param URL a <code>String</code> value
     * @exception SQLException if an error occurs
     */
    public JDBCUrl (final String URL) throws SQLException {
	parseURL(URL);}
	
    /**
     * <code>parseURL</code> is a convenience method for parsing URL.
     *
     * @param URL a <code>String</code> value
     * @exception SQLException if an error occurs
     */
    public void parseURL (final String URL) throws SQLException {
	LinkedList<String> components = new LinkedList<String>(Arrays.asList(("" + URL).split(SEPARATOR)));
	this.components = components.toArray(new String[0]);
	if (components.size() < 3) throw new SQLException("Invalid JDBC URL:  " + URL);
	String[] parts = new String[3];
	parts[0] = components.poll();
	parts[1] = components.poll();
	parts[2] = components.poll();
	for (String token : components) parts[2] += SEPARATOR + token;
	this.protocol = parts[0];
	if (!this.protocol.equals(PROTOCOL)) 
	    throw new SQLException("Invalid JDBC URL. Protocol in " + URL + " is " + this.protocol + " instead of jdbc.");
	this.subProtocol = parts[1];
	this.subName = parts[2];}
}

