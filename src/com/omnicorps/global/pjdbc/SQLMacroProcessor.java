package com.omnicorps.global.pjdbc; // Generated package name

import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SQLMacroProcessor {
    public static String[] expand (String sql) {
	LinkedList<String> tokens = new LinkedList<String>(Arrays.asList(("" + sql).split(" ")));
	if (tokens!=null) {
	    // tokens.size()=10 &&
	    // tokens.get(0).equalsIgnoreCase("create") &&
	    // tokens.get(1).equalsIgnoreCase("domain") &&
	    // tokens.get(2).equalsIgnoreCase("key") &&
	    // tokens.get(4).equalsIgnoreCase("on") &&
	    // tokens.get(6).equalsIgnoreCase("references") &&
	    // tokens.get(8).equalsIgnoreCase("with")) {
	    String keyName = tokens.get(3);
	    String tableAndColumn = tokens.get(5);
	    String referencingTable = tableAndColumn.split(".")[0];
	    String referencingColumn = tableAndColumn.split(".")[1];
	    String viewAndColumn = tokens.get(7);
	    String referencedView = viewAndColumn.split(".")[0];
	    String referencedColumn = viewAndColumn.split(".")[1];
	    String errorMsg = tokens.get(9);
	}
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
	    "drop view if exists broken_key",
	    "create view if not exists broken_key as select * from phone p left outer join adult a on p.person_id = a.id where a.id is null",
	    "drop trigger if exists phone_insert",
	    "create trigger if not exists phone_insert after insert on phone when exists (select * from broken_key)\n" +
	    "begin\n" +
	    "  select raise(rollback, 'Only persons older than 18 can have a telephone.')\n" +
	    "end",
	    "drop trigger if exists person_update",
	    "create trigger if not exists person_update after update on person when exists (select * from broken_key)\n" +
	    "begin\n" +
	    "  select raise(rollback, 'Only persons older than 18 can have a telephone.')\n" + 
	    "end\n"};
	return stmts;
    }

    public static String[] expand (String[] sql) {
	List<String> retVal = new ArrayList<String>();
	for (String stmt : sql) retVal.addAll(Arrays.asList(expand(stmt)));
	return retVal.toArray(new String[]{});}
	
}
