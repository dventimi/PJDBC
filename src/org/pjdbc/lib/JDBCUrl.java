package org.pjdbc.lib;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

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
	String[] parts = parseURL(URL);
	this.protocol = parts[0];
	this.subProtocol = parts[1];
	this.subName = parts[2];}
	
    /**
     * <code>parseURL</code> is a convenience method for parsing URL.
     *
     * @param URL a <code>String</code> value
     * @exception SQLException if an error occurs
     */
    public String[] parseURL (final String URL) throws SQLException {
	String[] tokens = URL.split(SEPARATOR);
	if (tokens.length<3) throw new SQLException("Invalid JDBC URL: " + URL);
	if (!tokens[0].equals(PROTOCOL)) throw new SQLException("Invalid JDBC URL. Protocol: " + URL);
	String[] parts = new String[]{"", "", ""};
	parts[0] = tokens[0];
	parts[1] = tokens[1];
	for (int i = 2; i<parts.length; i++) parts[3] += SEPARATOR + parts[i];
	return parts;}}


