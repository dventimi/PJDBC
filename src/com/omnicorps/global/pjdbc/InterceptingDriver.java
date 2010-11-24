package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Driver;

/**
 * Describe class <code>InterceptingDriver</code> here.
 *
 * @author <a href="mailto:dventimi@dventimi-laptop">David A. Ventimiglia</a>
 * @version 1.0
 */
public interface InterceptingDriver 
    extends Driver {

    public String getProtocol ();

    public String getSubProtocol ();
}
