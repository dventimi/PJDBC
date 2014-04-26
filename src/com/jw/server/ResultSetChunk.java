package com.jw.server;

import java.io.*;
import java.util.*;

public class ResultSetChunk implements Serializable{
    private List rsRows;
    int curIndex = 0;

    public ResultSetChunk(List rows) {
	rsRows = rows;}

    public Object[] getNextRow () {
	Object []row = null;
	if (curIndex < rsRows.size()) {
	    row = (Object[])rsRows.get(curIndex);
	    curIndex++;}
	return row;}}
