package org.pjdbc.util;

import java.sql.Driver;

public interface FilteringDriver extends ProxyDriver {
    public Filter getFilter () throws Exception;}
