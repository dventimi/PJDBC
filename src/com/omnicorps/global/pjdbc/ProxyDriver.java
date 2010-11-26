package com.omnicorps.global.pjdbc; // Generated package name

public class ProxyDriver
    extends AbstractInterceptingDriver {
    
    public final String getSubProtocol () {
	return "pjdbc";
    }

    static {
	registerDriver(new ProxyDriver());
    }
}