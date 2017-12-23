package org.pjdbc.sql;

import java.sql.*;
import java.util.*;

public abstract class AbstractWrapper implements Wrapper {
    protected List<Wrapper> delegates = new ArrayList<Wrapper>();

    public AbstractWrapper (Wrapper wrapper) throws SQLException {
        this(new Wrapper[]{wrapper});}

    public AbstractWrapper (Wrapper... wrappers) throws SQLException {
        if (wrappers==null || wrappers.length==0) throw new SQLException();
	delegates = Arrays.asList(wrappers);}

    @Override
    public boolean isWrapperFor (Class<?> iface) throws SQLException {
	if (iface.isInstance(this)) return true;
	for (Wrapper d : delegates) if (!d.isWrapperFor(iface)) return false;
	return true;}

    @SuppressWarnings("unchecked")
    @Override
    public <T> T unwrap (Class<T> iface) throws SQLException {
	if (!isWrapperFor(iface)) throw new SQLException();
	if (iface.isInstance(this)) return (T)this;
	for (Wrapper d : delegates) if (iface.isInstance(d)) return (T)d;
	for (Wrapper d : delegates) return d.unwrap(iface);
	throw new SQLException(String.format("Cannot unwrap to %s", iface.getName()));}}
