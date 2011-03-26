package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.SQLException;

public class ProxyUrl extends JDBCUrl {
    public ProxyUrl (final String url) throws SQLException {
	super(url);}

    public String getUrl () {
	// return super.getProtocol() + super.SEPARATOR + super.getSubName();}
	return super.getSubName();}
}