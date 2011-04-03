package org.radiumsalt; // Generated package name

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.HashMap;

public class SaltBin {
    private static Map<Pattern, Salt> salts = new HashMap<Pattern, Salt>();

    public static void init () {
	salts.clear();}

    public static void addSalt (Salt salt) {
	salts.put(salt.getPattern(), salt);}
    
    public static Salt getSalt (String str) {
	for (Pattern pattern : salts.keySet()) if(pattern.matcher(str).matches()) return salts.get(pattern);
	return null;}
}