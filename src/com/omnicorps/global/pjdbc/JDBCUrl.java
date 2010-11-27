package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * Describe class <code>JDBCUrl</code> here.
 *
 */
public class JDBCUrl {
    /**
     * Describe variable <code>SEP</code> here.
     *
     */
    public static String SEP = ":";
    private String protocol = "";
    private String subprotocol = "";
    private String subname = "";

    /**
     * Creates a new <code>JDBCUrl</code> instance.
     *
     * @param URL a <code>String</code> value
     * @exception SQLException if an error occurs
     */
    public JDBCUrl (final String URL) 
	throws SQLException {
	if (URL==null) throw new SQLException("JDBC URL cannot be null.");
	String[] parts = parseURL(URL);
	for (String part : parts) 
	    if (part==null) throw new SQLException("Malformed JDBC URL." + parts);
	this.protocol += parts[0];
	this.subprotocol += parts[1];
	this.subname += parts[2];
    }

    /**
     * Describe <code>getProtocol</code> method here.
     *
     * @return a <code>String</code> value
     */
    public String getProtocol () {
	return this.protocol.trim();
    }

    /**
     * Describe <code>getSubprotocol</code> method here.
     *
     * @return a <code>String</code> value
     */
    public String getSubprotocol () {
	return this.subprotocol;
    }

    /**
     * Describe <code>getSubname</code> method here.
     *
     * @return a <code>String</code> value
     */
    public String getSubname () {
	return this.subname;
    }

    /**
     * Describe <code>parseURL</code> method here.
     *
     * @param URL a <code>String</code> value
     * @return a <code>String[]</code> value
     */
    public static String[] parseURL (final String URL) {
	LinkedList<String> tokens = new LinkedList<String>(Arrays.asList(("" + URL).split(SEP)));
	String[] parts = new String[3];
	parts[0] = tokens.poll();
	parts[1] = tokens.poll();
	parts[2] = tokens.poll();
	for (String token : tokens) parts[2] += SEP + token;
	return parts;
    }
}

