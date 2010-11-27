package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.sql.SQLException;

public class MacroDriver
    extends ProxyDriver {
    
    static {registerDriver(new MacroDriver());}

    public MacroDriver () {
	this.addHook(new SQLHook () {
		public String[] eval (String[] sql, Connection connection) 
		    throws SQLException {
		    sql = SQLMacroProcessor.expand(sql);
		    return sql;}});}

    public final String getSubProtocol () {return "mjdbc";}
}