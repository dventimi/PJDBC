package com.omnicorps.global.pjdbc; // Generated package name

import java.util.List;
import java.util.ArrayList;

public class SQLMacroManager {
    private static List<SQLMacro> macros = new ArrayList<SQLMacro>();

    public static void registerSQLMacro (SQLMacro macro) {
	macros.add(macro);}
    
    public static SQLMacro getSQLMacro (String str) {
	for (SQLMacro macro : macros)
	    if (macro.accepts(str)) return macro;
	return null;}
}