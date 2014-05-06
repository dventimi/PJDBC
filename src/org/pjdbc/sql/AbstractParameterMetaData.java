package org.pjdbc.sql;

import java.sql.*;

public abstract class AbstractParameterMetaData extends AbstractWrapper implements ParameterMetaData {
    private ParameterMetaData d;

    public AbstractParameterMetaData (ParameterMetaData stmt) throws SQLException {
	super(stmt);
	this.d = stmt;}

    public String getParameterClassName (int param) throws SQLException {return d.getParameterClassName(param);}
    public String getParameterTypeName (int param) throws SQLException {return d.getParameterTypeName(param);}
    public boolean isSigned (int param) throws SQLException {return d.isSigned(param);}
    public int getParameterCount () throws SQLException {return d.getParameterCount();}
    public int getParameterMode (int param) throws SQLException {return d.getParameterMode(param);}
    public int getParameterType (int param) throws SQLException {return d.getParameterType(param);}
    public int getPrecision (int param) throws SQLException {return d.getPrecision(param);}
    public int getScale (int param) throws SQLException {return d.getScale(param);}
    public int isNullable (int param) throws SQLException {return d.isNullable(param);}}
