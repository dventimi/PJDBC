package org.pjdbc.test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.junit.Ignore;
import org.junit.runners.Suite;
import org.pjdbc.lib.*;
import org.pjdbc.*;

public class InterceptingDriverTest extends AutoTest {
    public static String DB = "derby:memory:testdb";
    public static String CREATE_DB = "jdbc:" + DB + ";create=true";
    public static String REMOVE_DB = "jdbc:" + DB + ";drop=true";
    
    public static void main (String[] args){
	autorun(new Exception());}

    public void setUp () throws ClassNotFoundException {
	Class.forName("org.pjdbc.test.IdentityDriver");
	Class.forName("org.pjdbc.test.DevNullDriver");
	Class.forName("org.pjdbc.ProxyDriver");
	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	try {DriverManager.getConnection(CREATE_DB);} catch (Throwable t) {}}

    public void tearDown () {
	try {DriverManager.getConnection(REMOVE_DB);} catch (Throwable t) {}}

    // public void testInterceptingDriverAcceptsURL () {
    // 	new Script () {
    // 	    public void run () throws Exception {
    // 		assertTrue((new IdentityDriver()).acceptsURL("jdbc:identity-intercepting:jdbc:" + DB));}};}

    // public void testInterceptingDriverAcceptsRealisticURL () {
    // 	new Script () {
    // 	    public void run () throws Exception {
    // 		Driver driver = new IdentityDriver();
    // 		assertTrue(driver.acceptsURL("jdbc:identity-intercepting:jdbc:" + DB));}};}

    public void testIdentityDriverGetConnection () {
    	new Script () {
    	    public void run () throws Exception {
    		DriverManager
    		    .getConnection("jdbc:identity-intercepting:jdbc:" + DB);}};}


    public void testProxyDriverWithNoHandlersExecutesPassedInQuery () {
    	new Script () {
    	    public void run () throws Exception {
    		DriverManager
    		    .getConnection("jdbc:pjdbc:" + DB)
    		    .createStatement()
    		    .execute("create table person (name varchar(30))");}};}

    private static class CollectingHandler 
	implements SQLHandler {
	private List<String> log = new ArrayList<String>();
	public String[] getSQLStatements () {
	    return log.toArray(new String[]{});}
	public ResultSet execute (String sql, Connection connection) {
	    log.add(sql);
	    return null;}}}

    // public void testProxyDriverAddCollectingHandlerAndTryToCollect () {
    // 	new Script () {
    // 	    public void run () throws Exception {
    // 		CollectingHandler collector = new CollectingHandler();
    // 		ProxyDriver.setHandler(collector);
    // 		DriverManager
    // 		    .getConnection("jdbc:pjdbc:" + DB)
    // 		    .createStatement()
    // 		    .execute("create table person (name varchar(30))");
    // 		assertNotNull(collector.getSQLStatements());
    // 		assertEquals(collector.getSQLStatements().length, 1);
    // 		assertEquals(collector.getSQLStatements()[0], "create table person (name varchar(30))");}};}}
