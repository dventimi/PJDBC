package com.omnicorps.global.pjdbc; // Generated package name

import java.util.Map;

public interface SQLMacroFrontEnd {
    public boolean accepts (String macro);

    public Map<String, String> parse (String macro);
}