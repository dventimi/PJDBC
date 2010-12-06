package com.omnicorps.global.pjdbc; // Generated package name

import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SQLMacroProcessor {
    public static Pattern PATTERN = 
	Pattern.compile("create domain key on (\\w+).(\\w+) references (\\w+).(\\w+) with message '(.+)'");

    public static boolean acceptsStatement (String sql) {
	return PATTERN.matcher(sql).matches();}

    public static String[] expand (String sql) 
	throws Exception {
	if (!SQLMacroProcessor.acceptsStatement(sql)) throw new Exception();
	Matcher m = PATTERN.matcher(sql);
	m.matches();
	String table = m.group(1);
	String tableColumn = m.group(2);
	String view = m.group(3);
	String viewColumn = m.group(4);
	String message = m.group(5);
	// Need:
	// - table we're constraining (eg., phone).  not named in view
	// - column we're constraining (eg., phone.person_id).  not named in view
	// - domain key source column (eg., adult.id).  this is viewAndColumn.column
	// - domain key source view (eg., adult).  this is viewAndColumn.view
	// - domain key source view UNDERLYING TABLES (eg., person).  get this by PARSING the view SQL.  ugh.
	// Create:
	// - complementary view
	// - domain key trigger on constrained (referencing) table
	// - complementary triggers on all source view tables (eg., person)
	String[] stmts = new String[]{
	    String.format("drop view if exists %1$s_broken_key", table),
	    String.format("create view if not exists %1$s_broken_key as\n" + 
			  "select * from %1$s p\n" + 
			  "left outer join %3$s a on p.%2$s = a.%4$s\n" + 
			  "where a.%4$s is null", table, tableColumn, view, viewColumn),
	    String.format("drop trigger if exists %1$s_insert", table),
	    String.format("create trigger if not exists %1$s_insert after insert on %1$s when exists (select * from %1$s_broken_key)\n" +
			  "begin\n" +
			  "  select raise(rollback, '%5$s')\n" +
			  "end",
			  "drop trigger if exists person_update",
			  "create trigger if not exists %1$s_update after update on %1$s when exists (select * from %1$s_broken_key)\n" +
			  "begin\n" +
			  "  select raise(rollback, '%5$s')\n" + 
			  "end\n", table, tableColumn, view, viewColumn, message)};
	return stmts;
    }
}
