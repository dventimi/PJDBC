package org.radiumsalt; // Generated package name

import java.util.regex.Pattern;

public interface Salt {
    public Pattern getPattern ();

    public String[] dissolve ();
}