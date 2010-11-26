package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;

/**
 * Describe class <code>IdentityInterceptingDriver</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public class IdentityInterceptingDriver 
    extends AbstractInterceptingDriver {

    /**
     * Describe <code>getSubProtocol</code> method here.
     *
     * @return a <code>String</code> value
     */
    public final String getSubProtocol () {
	return "identity-intercepting";
    }

    static {
	registerDriver(new IdentityInterceptingDriver());
	// hooks.add(new HookFunction<String>() {
	// 	public String[] eval (String[] sql) {
	// 	    return sql;
	// 	}
	//     });
    }
}