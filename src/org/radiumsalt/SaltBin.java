package org.radiumsalt; // Generated package name

import java.util.Set;
import java.util.HashSet;
import java.sql.Connection;

public class SaltBin {
    private static Set<Salt> salts = new HashSet<Salt>();

    public static void init () {
	salts.clear();}

    public static void addSalt (Salt salt) {
	salts.add(salt);}

    public static boolean hasSalt (String str) {
	for (Salt salt : salts) if (salt.acceptsInput(str)) return true;
	return false;}
    
    public static String[] getSaltedSQL (Connection conn, String str) {
	for (Salt salt : salts) 
	    if (salt.acceptsInput(str))
		return salt.getSQL(conn, str);
	return null;}
}