package org.pjdbc.sql;

import java.sql.*;
import java.util.*;

public abstract class AbstractWrapper implements Wrapper {
    private List<Wrapper> delegates;

    AbstractWrapper (Wrapper... wrappers) throws SQLException {
	if (wrappers.length==0) throw new SQLException();
	delegates = Arrays.asList(wrappers);}

    public boolean isWrapperFor (Class<?> iface) throws SQLException {
	for (Wrapper d : delegates) return d.isWrapperFor(iface);
	return false;}

    public <T> T unwrap (Class<T> iface) throws SQLException {
	for (Wrapper d : delegates) return d.unwrap(iface);
	return null;}}
