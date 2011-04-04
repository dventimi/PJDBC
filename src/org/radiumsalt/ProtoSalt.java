package org.radiumsalt; // Generated package name

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.ST;

public class ProtoSalt extends AbstractSalt {
    public ProtoSalt () {}


    public Pattern getPattern () {
	return Pattern.compile("\\s*create\\s+domain\\s+key\\s+on\\s+(\\w+).(\\w+)\\s+references\\s+(\\w+).(\\w+)\\s+with\\s+message\\s+'(.+)'\\s*");}


    public String[] getSQL (Connection conn, String input) {
	Matcher m = this.getPattern().matcher(input);
	m.matches();
	String table = m.group(1);
	String tableColumn = m.group(2);
	String view = m.group(3);
	String viewColumn = m.group(4);
	String message = m.group(5);

	STGroup g = AbstractSalt.getSTGroup();
	ST dropTrigger = g.getInstanceOf(DROP_TRIGGER);
	ST createInsertTrigger = g.getInstanceOf(CREATE_INSERT_TRIGGER);
	ST createUpdateTrigger = g.getInstanceOf(CREATE_UPDATE_TRIGGER);

	dropTrigger.add("name", table + "_broken_key");
	
	String[] stmts = new String[]{
	    String.format("drop view if exists %1$s_broken_key", 
			  table),
	    String.format("create view if not exists %1$s_broken_key as\n" + 
			  "select * from %1$s p\n" + 
			  "left outer join %3$s a on p.%2$s = a.%4$s\n" + 
			  "where a.%4$s is null", 
			  table, tableColumn, view, viewColumn),
	    dropTrigger.render(),
	    // String.format("drop trigger if exists %1$s_insert", 
	    // 		  table),
	    String.format("create trigger if not exists %1$s_insert\n" + 
			  "after insert on %1$s\n" + 
			  "when exists (select * from %1$s_broken_key)\n" +
			  "begin\n" +
			  "  select raise(rollback, '%5$s')\n" +
			  "end",
			  "drop trigger if exists person_update",
			  "create trigger if not exists %1$s_update\n" + 
			  "after update on %1$s\n" + 
			  "when exists (select * from %1$s_broken_key)\n" +
			  "begin\n" +
			  "  select raise(rollback, '%5$s')\n" + 
			  "end\n", 
			  table, tableColumn, view, viewColumn, message)};
	return stmts;}
}
