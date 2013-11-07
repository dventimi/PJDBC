package org.pjdbc.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.pjdbc.util.AbstractProxyDriver;
import org.pjdbc.util.AbstractProxyStatement;

public class FilteringDriver extends AbstractProxyDriver {
    static {try {DriverManager.registerDriver(new FilteringDriver());} catch (Exception e) {throw new RuntimeException(e);}}

    public static interface Filter {
	public String apply (String sql);}

    public static abstract class AbstractFilter implements Filter {
	public String apply (String sql) {return sql;}}
    
    protected boolean acceptsSubProtocol (String subprotocol) {
	return "filter".equals(subprotocol);}

    protected Filter getFilter () {
	try {return (Filter)Class.forName(System.getProperty("org.pjdbc.drivers.FilteringDriver.Filter")).newInstance();} catch (Exception e) {}
	return new AbstractFilter() {};}

    protected Statement proxyStatement (Connection conn, Statement delegate) {
	return new AbstractProxyStatement(delegate, conn) {
	    public void addBatch (String sql) throws SQLException {
		super.addBatch(getFilter().apply(sql));}
	    public boolean execute (String sql) throws SQLException {
		return super.execute(getFilter().apply(sql));}
	    public boolean execute (String sql, int[] columnIndexes) throws SQLException {
		return super.execute(getFilter().apply(sql), columnIndexes);}
	    public boolean execute (String sql, String[] columnNames) throws SQLException {
		return super.execute(getFilter().apply(sql), columnNames);}
	    public ResultSet executeQuery (String sql) throws SQLException {
		return super.executeQuery(getFilter().apply(sql));}
	    public int executeUpdate (String sql) throws SQLException {
		return super.executeUpdate(getFilter().apply(sql));}
	    public int executeUpdate (String sql, int autoGeneratedKeys) throws SQLException {
		return super.executeUpdate(getFilter().apply(sql), autoGeneratedKeys);}
	    public int executeUpdate (String sql, int[] columnIndexes) throws SQLException {
		return super.executeUpdate(getFilter().apply(sql), columnIndexes);}
	    public int executeUpdate (String sql, String[] columnNames) throws SQLException {
		return super.executeUpdate(getFilter().apply(sql), columnNames);}};}}
