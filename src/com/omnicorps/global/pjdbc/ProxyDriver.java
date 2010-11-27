package com.omnicorps.global.pjdbc; // Generated package name

import java.util.List;
import java.util.ArrayList;

public class ProxyDriver
    extends AbstractInterceptingDriver {

    private static List<SQLHook> hooks = new ArrayList<SQLHook>();

    static {registerDriver(new ProxyDriver());}

    public String getSubProtocol () {
	return "pjdbc";}

    public SQLHook[] getHooks () {
	return hooks.toArray(new SQLHook[]{});}

    public static void addHook (SQLHook hook) {
	hooks.add(hook);}

    public static void init () {
	hooks = new ArrayList<SQLHook>();}
}