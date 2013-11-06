package org.pjdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

public interface ConnectionAware extends Connection {
    public Driver getDriver ();
    public String getUrl ();
    public Properties getInfo ();}
