package com.omnicorps.global.pjdbc; // Generated package name

import java.sql.Connection;
import java.util.Map;
import java.sql.ResultSet;

public interface SQLMacroBackend {
    public String getDatabaseProductName ();

    public String getDatabaseProductVersion ();

    public int getDatabaseProductMajorVersion ();

    public int getDatabaseProductMinorVersion ();

    public boolean supportsDatabaseProduct (String productName,
					    String productVersion,
					    int majorVersion,
					    int minorVersion);

    public ResultSet deliver (Connection connection,
			      Map<String, String> macroData);
}