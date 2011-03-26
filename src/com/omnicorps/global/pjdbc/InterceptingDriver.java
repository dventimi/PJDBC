package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Driver;

/**
 * Describe interface <code>InterceptingDriver</code> here.
 *
 * @author <a href="mailto:dventimi@gmail.com">David A. Ventimiglia</a>
 * @version 1.0
 */
public interface InterceptingDriver extends Driver {
    public SQLHandler getHandler ();

    public String getSubProtocol ();
}
