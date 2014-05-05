package org.pjdbc.sql;

import java.sql.*;

public abstract class AbstractResultSetMetaData extends AbstractWrapper implements ResultSetMetaData {
    private ResultSetMetaData d;

    AbstractResultSetMetaData (ResultSetMetaData stmt) throws SQLException {
	super(stmt);
	this.d = stmt;}

    public String getCatalogName (int column) throws SQLException {return d.getCatalogName(column);}
    public String getColumnClassName (int column) throws SQLException {return d.getColumnClassName(column);}
    public String getColumnLabel (int column) throws SQLException {return d.getColumnLabel(column);}
    public String getColumnName (int column) throws SQLException {return d.getColumnName(column);}
    public String getColumnTypeName (int column) throws SQLException {return d.getColumnTypeName(column);}
    public String getSchemaName (int column) throws SQLException {return d.getSchemaName(column);}
    public String getTableName (int column) throws SQLException {return d.getTableName(column);}
    public boolean isAutoIncrement (int column) throws SQLException {return d.isAutoIncrement(column);}
    public boolean isCaseSensitive (int column) throws SQLException {return d.isCaseSensitive(column);}
    public boolean isCurrency (int column) throws SQLException {return d.isCurrency(column);}
    public boolean isDefinitelyWritable (int column) throws SQLException {return d.isDefinitelyWritable(column);}
    public boolean isReadOnly (int column) throws SQLException {return d.isReadOnly(column);}
    public boolean isSearchable (int column) throws SQLException {return d.isSearchable(column);}
    public boolean isSigned (int column) throws SQLException {return d.isSigned(column);}
    public boolean isWritable (int column) throws SQLException {return d.isWritable(column);}
    public int getColumnCount () throws SQLException {return d.getColumnCount();}
    public int getColumnDisplaySize (int column) throws SQLException {return d.getColumnDisplaySize(column);}
    public int getColumnType (int column) throws SQLException {return d.getColumnType(column);}
    public int getPrecision (int column) throws SQLException {return d.getPrecision(column);}
    public int getScale (int column) throws SQLException {return d.getScale(column);}
    public int isNullable (int column) throws SQLException {return d.isNullable(column);}}
