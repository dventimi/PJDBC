package org.radiumsalt; // Generated package name

import org.stringtemplate.v4.STGroup;

public abstract class AbstractSalt implements Salt {
    public static String DROP_TRIGGER = "drop_trigger";
    public static String CREATE_INSERT_TRIGGER = "create_insert_trigger";
    public static String CREATE_UPDATE_TRIGGER = "create_update_trigger";

    private static STGroup stGroup;

    public static void setSTGroup (STGroup group) {
	stGroup = group;}

    public static STGroup getSTGroup () {
	return stGroup;}

    public boolean acceptsInput (String str) {
	return this.getPattern().matcher(str).matches();}
}