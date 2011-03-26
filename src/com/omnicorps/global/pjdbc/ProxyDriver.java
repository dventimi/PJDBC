package com.omnicorps.global.pjdbc; // Generated package name

public class ProxyDriver extends AbstractInterceptingDriver {
    static {registerDriver(new ProxyDriver());}

    public String getSubProtocol () {
	return "pjdbc";}

}