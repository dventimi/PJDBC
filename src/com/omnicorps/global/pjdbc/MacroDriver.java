package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;

public class MacroDriver
    extends ProxyDriver {
    
    static {registerDriver(new MacroDriver());}

    public MacroDriver () {
	this.addHook(new MacroProcessor());}

    private class MacroProcessor
	implements SQLHook {
	public String eval (String sql, Connection connection) {
	    return sql;}}
}