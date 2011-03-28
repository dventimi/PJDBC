package org.radiumsalt; // Generated package name

import java.util.List;
import java.util.ArrayList;

public class SaltBin {
    private static List<Salt> salts = new ArrayList<Salt>();

    public static void init () {
	salts.clear();}

    public static void register (Salt salt) {
	salts.add(salt);}
    
    public static Salt getSalt (String str) {
	for (Salt salt : salts) if (salt.getPattern().matcher(str).matches()) return salt;
	return null;}
}