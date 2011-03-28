package org.radiumsalt; // Generated package name

import java.util.List;
import java.util.ArrayList;

public class MacroManager {
    private static List<Macro> macros = new ArrayList<Macro>();

    public static void registerMacro (Macro macro) {
	macros.add(macro);}
    
    public static Macro getMacro (String str) {
	for (Macro macro : macros) if (macro.accepts(str)) return macro;
	return null;}
}