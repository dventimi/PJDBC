package org.pjdbc.util;

import java.sql.Driver;

public interface ProxyDriver extends Driver {
    public boolean acceptsProtocol (String protocol);
    public boolean acceptsSubProtocol (String subprotocol);}
