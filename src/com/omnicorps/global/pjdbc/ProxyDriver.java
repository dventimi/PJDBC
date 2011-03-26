package com.omnicorps.global.pjdbc; // Generated package name

public class ProxyDriver extends AbstractInterceptingDriver {
    static {registerDriver(new ProxyDriver());}

    private static SQLHandler handler;

    public static void setHandler (SQLHandler handler) {
	ProxyDriver.handler = handler;}

    public String getSubProtocol () {
	return "pjdbc";}

    public SQLHandler getHandler () {
	return ProxyDriver.handler;}

}