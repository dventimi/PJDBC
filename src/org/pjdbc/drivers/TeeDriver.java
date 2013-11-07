package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.pjdbc.util.AbstractProxyConnection;
import org.pjdbc.util.AbstractProxyDriver;
import org.pjdbc.util.AbstractProxyStatement;

public class TeeDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new TeeDriver());} catch (Exception e) {throw new RuntimeException(e);}}
    
    protected boolean acceptsSubName (String subname) {
	if ((""+subname).split(";").length!=2) return false;
	try{for (String url : subname.split(";")) if (DriverManager.getDriver(subname.trim())==null) return false;} catch (Exception e) {return false;}
	return true;}

    protected boolean acceptsSubProtocol (String subprotocol) {
	return "tee".equals(subprotocol);}

    public Connection connect (String url, Properties info) throws SQLException {
    	if (!acceptsURL(url)) return null;
	ArrayList<String> urls = new ArrayList<String>(Arrays.asList(subname(url).split(";")));
	ArrayList<Connection> listeners = new ArrayList<Connection>();
	Connection delegate = DriverManager.getConnection(urls.remove(0), info);
	for (String s : urls) listeners.add(DriverManager.getConnection(s, info));
	return proxyConnection(delegate, urls.get(0), info, listeners);}}


