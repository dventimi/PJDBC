package com.omnicorps.global.pjdbc; // Generated package name

import java.util.List;
import java.util.ArrayList;

public class ProxyDriver
    extends AbstractInterceptingDriver {

    private List<SQLHook> hooks;

    {this.init();}

    static {registerDriver(new ProxyDriver());}

    public final String getSubProtocol () {
	return "pjdbc";}

    public SQLHook[] getHooks () {
	return this.hooks.toArray(new SQLHook[]{});}

    public void addHook (SQLHook hook) {
	this.hooks.add(hook);}

    public void init () {
	this.hooks = new ArrayList<SQLHook>();}
}