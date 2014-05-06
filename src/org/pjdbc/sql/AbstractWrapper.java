package org.pjdbc.sql;

import java.sql.*;

public abstract class AbstractWrapper implements Wrapper {
    private Wrapper d;

    public AbstractWrapper (Wrapper wrapper) throws SQLException {
	d = wrapper;}

    public boolean isWrapperFor (Class<?> iface) throws SQLException {
	if (iface.isInstance(this)) return true;
	if (d instanceof Wrapper) return ((Wrapper)d).isWrapperFor(iface);
	return false;}

    @SuppressWarnings("unchecked")
    public <T> T unwrap (Class<T> iface) throws SQLException {
	if (iface.isInstance(this)) return (T)this;
	if (iface.isInstance(d)) return (T)d;
	if (d instanceof Wrapper) return ((Wrapper)d).unwrap(iface);
	throw new SQLException("Cannot unwrap to %s".format(iface.getName()));}}
