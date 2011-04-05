package org.radiumsalt; // Generated package name

import org.stringtemplate.v4.STGroup;
import java.util.HashMap;
import java.util.Map;
import org.stringtemplate.v4.ST;
import java.sql.Connection;

public abstract class AbstractSalt implements Salt {
    public static String DROP_TRIGGER = "drop_trigger";
    public static String CREATE_INSERT_TRIGGER = "create_insert_trigger";
    public static String CREATE_UPDATE_TRIGGER = "create_update_trigger";

    private static Map<String, STGroup> stGroups = new HashMap<String, STGroup>();

    public static void setSTGroup (String dbName, STGroup group) {
	stGroups.put(dbName, group);}

    public static STGroup getSTGroup (String dbName) {
	return stGroups.get(dbName);}

    public boolean acceptsInput (String str) {
	return this.getPattern().matcher(str).matches();}

    protected ST getTemplate (Connection conn, String name) throws Exception {
	STGroup group = getSTGroup(conn.getMetaData().getDatabaseProductName());
	return group==null ? new ST("") : group.getInstanceOf(name);}
}