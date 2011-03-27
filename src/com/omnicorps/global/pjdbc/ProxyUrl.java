package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * <code>ProxyUrl</code> is a <code>JDBCUrl</code> specifically used
 * for handling proxied URLs, where essentially another
 * subprotocol--the proxy subprotocol--is inserted into a URL in
 * between the first component (the protocol) and the second component
 * (the unproxied subprotocol).  The rest of the URL shifts down, so
 * that the unproxied protocol essentially becomes part of the subname
 * for the new URL.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public class ProxyUrl extends JDBCUrl {
    /**
     * Creates a new <code>ProxyUrl</code> instance, parsing the URL
     * as it does.
     *
     * @param url a <code>String</code> value
     * @exception SQLException if an error occurs
     */
    public ProxyUrl (final String url) throws SQLException {
	super(url);}

    /**
     * <code>getUrl</code> reports the proxied Url, which of course is
     * the subname for this URL.
     *
     * @return a <code>String</code> value
     */
    public String getUrl () {
	return super.getSubName();}

    /**
     * <code>parseURL</code> is a convenience method for parsing the
     * URL, paying special attention to dealing with the fact that the
     * proxy subprotocol has been inserted into the URL.  This method
     * knows how to detect that, store it, and manage the subname into
     * a proper URL unto itself.
     *
     * @param URL a <code>String</code> value
     * @return a <code>String[]</code> value
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