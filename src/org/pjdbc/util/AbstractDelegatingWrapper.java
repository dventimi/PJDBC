// package org.pjdbc.util;

// import java.sql.*;
// import java.util.*;

// public abstract class AbstractDelegatingWrapper implements Wrapper {
//     protected abstract List<Wrapper> getDelegates();
//     public boolean isWrapperFor (Class<?> iface) {for (Wrapper d : getDelegates()) return d.isWrapperFor(iface); throw new SQLException();}
//     public <T> T unwrap(Class<T> iface) throws SQLException {for (Wrapper d : getDelegates()) return d.unwrap(iface); throw new SQLException();}}
