package org.pjdbc.util;

import java.sql.Connection;
import java.util.Properties;

public class ConnectionInfo {
    private Connection conn;
    private String url;
    private Properties info;
    public ConnectionInfo (Connection conn, String url, Properties info) {this.conn = conn; this.url = url; this.info = info;}
    public Connection getConnection () {return this.conn;}
    public String getUrl () {return this.url;}
    public Properties getInfo () {return this.info;}}
