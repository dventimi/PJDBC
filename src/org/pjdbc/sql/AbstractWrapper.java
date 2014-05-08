package org.pjdbc.sql;

import java.sql.*;
import java.util.*;

public abstract class AbstractWrapper implements Wrapper {
    private List<Wrapper> delegates = new ArrayList<Wrapper>();

    public AbstractWrapper (Wrapper wrapper) throws SQLException {
	delegates.add(wrapper);}

    public AbstractWrapper (Wrapper[] wrappers) throws SQLException {
	delegates = Arrays.asList(wrappers);}

    public boolean isWrapperFor (Class<?> iface) throws SQLException {
	if (iface.isInstance(this)) return true;
	for (Wrapper d : delegates) if (!d.isWrapperFor(iface)) return false;
	return false;}

    @SuppressWarnings("unchecked")
    public <T> T unwrap (Class<T> iface) throws SQLException {
	if (!isWrapperFor(iface)) throw new SQLException();
	if (iface.isInstance(this)) return (T)this;
	for (Wrapper d : delegates) if (iface.isInstance(d)) return (T)d;
	for (Wrapper d : delegates) return d.unwrap(iface);
	throw new SQLException("Cannot unwrap to %s".format(iface.getName()));}}
