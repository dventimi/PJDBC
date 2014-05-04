package org.pjdbc.sql;

import java.sql.*;
import java.util.*;

public abstract class AbstractWrapper implements Wrapper {
    private Wrapper d;

    AbstractWrapper (Wrapper wrapper) throws SQLException {
	d = wrapper;}

    public boolean isWrapperFor (Class<?> iface) throws SQLException {
	return d.isWrapperFor(iface);}

    public <T> T unwrap (Class<T> iface) throws SQLException {
	return d.unwrap(iface);}}
