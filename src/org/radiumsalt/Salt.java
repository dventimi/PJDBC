package org.radiumsalt; // Generated package name

import java.util.regex.Pattern;
import java.sql.Connection;

public interface Salt {
    public Pattern getPattern ();

    public String[] getSQL (Connection conn);
}