package com.omnicorps.global.pjdbc; // Generated package name

/**
 * Describe class <code>NullInterceptingDriver</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public class NullInterceptingDriver
    extends AbstractInterceptingDriver {

    /**
     * Describe <code>getSubProtocol</code> method here.
     *
     * @return a <code>String</code> value
     */
    public final String getSubProtocol () {
	return "null-intercepting";
    }
    
    static {
	registerDriver(new NullInterceptingDriver());
	// hooks.add(new HookFunction<String>() {
	// 	public String[] eval (String[] sql) {
	// 	    return new String[]{""};
	// 	}
	//     });
    }
}