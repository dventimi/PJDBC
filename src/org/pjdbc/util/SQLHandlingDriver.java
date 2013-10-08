package org.pjdbc.util;

import java.sql.Driver;

public interface SQLHandlingDriver extends ProxyDriver {
    public SQLHandler getSQLHandler () throws Exception;}
