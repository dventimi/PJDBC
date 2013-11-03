package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.pjdbc.util.AbstractProxyDriver;
import org.pjdbc.util.AbstractProxyConnection;

public class TeeDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new TeeDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    // private class TeeConnection extends AbstractProxyConnection {
    // 	public TeeConnection (String url, String tee) {
    // 	    super(DriverManager.getConnection(url));}
    // 	public TeeConnection (String url, String tee, Properties info) {
    // 	    super(DriverManager.getConnection(url, info));}
    // 	public TeeConnection (String url, String user, String password) {
    // 	    super(DriverManager.getConnection(url, usr, password));}}

    // private class TeeStatement extends AbstractProxyStatement {
    // 	public TeeStatement (String url, String tee, Properties info) {}
    // }

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "tee".equals(subprotocol);}

    // protected Connection proxyConnection (Driver driver, Connection conn) {
    // 	return new AbstractProxyConnection(driver, DriverManager.getConnection(url)) {};}

    // public Connection connect (String url, Properties info) throws SQLException {
    // 	if (!acceptsURL(url)) throw new SQLException("Invalid url:  " + url);
    // 	String subname = subname(url);
    // 	String target = subname.split(";")[0];
    // 	String tee = subname.split(";")[1];
    // 	tee = tee.contains(":") ? tee.split(":")[0] : tee;
    // 	return new TeeConnection(target, tee, info);}}
}
	

