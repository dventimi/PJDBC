package org.pjdbc.util;

import java.sql.Driver;

public interface TranslatingDriver extends ProxyDriver {
    public Translator getTranslator () throws Exception;}
