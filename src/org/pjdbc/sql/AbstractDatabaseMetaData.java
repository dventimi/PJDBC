package org.pjdbc.sql;

import java.sql.*;

public abstract class AbstractDatabaseMetaData extends AbstractWrapper implements DatabaseMetaData {
    private Connection conn;
    private DatabaseMetaData d;

    protected ResultSet wrap (ResultSet r) throws SQLException {
	return new AbstractResultSet(r){};}

    public AbstractDatabaseMetaData (Connection conn, DatabaseMetaData stmt) throws SQLException {
	super(stmt);
	this.conn = conn;
	this.d = stmt;}

    public Connection getConnection () throws SQLException {return d.getConnection();}
    public ResultSet getAttributes (String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {return wrap(d.getAttributes(catalog, schemaPattern, typeNamePattern, attributeNamePattern));}
    public ResultSet getBestRowIdentifier (String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {return wrap(d.getBestRowIdentifier(catalog, schema, table, scope, nullable));}
    public ResultSet getCatalogs () throws SQLException {return wrap(d.getCatalogs());}
    public ResultSet getClientInfoProperties () throws SQLException {return wrap(d.getClientInfoProperties());}
    public ResultSet getColumnPrivileges (String catalog, String schema, String table, String columnNamePattern) throws SQLException {return wrap(d.getColumnPrivileges(catalog, schema, table, columnNamePattern));}
    public ResultSet getColumns (String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {return wrap(d.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern));}
    public ResultSet getCrossReference (String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {return wrap(d.getCrossReference(parentCatalog, parentSchema, parentTable, foreignCatalog, foreignSchema, foreignTable));}
    public ResultSet getExportedKeys (String catalog, String schema, String table) throws SQLException {return wrap(d.getExportedKeys(catalog, schema, table));}
    public ResultSet getFunctionColumns (String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {return wrap(d.getFunctionColumns(catalog, schemaPattern, functionNamePattern, columnNamePattern));}
    public ResultSet getFunctions (String catalog, String schemaPattern, String functionNamePattern) throws SQLException {return wrap(d.getFunctions(catalog, schemaPattern, functionNamePattern));}
    public ResultSet getImportedKeys (String catalog, String schema, String table) throws SQLException {return wrap(d.getImportedKeys(catalog, schema, table));}
    public ResultSet getIndexInfo (String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {return wrap(d.getIndexInfo(catalog, schema, table, unique, approximate));}
    public ResultSet getPrimaryKeys (String catalog, String schema, String table) throws SQLException {return wrap(d.getPrimaryKeys(catalog, schema, table));}
    public ResultSet getProcedureColumns (String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {return wrap(d.getProcedureColumns(catalog, schemaPattern, procedureNamePattern, columnNamePattern));}
    public ResultSet getProcedures (String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {return wrap(d.getProcedures(catalog, schemaPattern, procedureNamePattern));}
    public ResultSet getPseudoColumns (String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {return wrap(d.getPseudoColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern));}
    public ResultSet getSchemas () throws SQLException {return wrap(d.getSchemas());}
    public ResultSet getSchemas (String catalog, String schemaPattern) throws SQLException {return wrap(d.getSchemas(catalog, schemaPattern));}
    public ResultSet getSuperTables (String catalog, String schemaPattern, String tableNamePattern) throws SQLException {return wrap(d.getSuperTables(catalog, schemaPattern, tableNamePattern));}
    public ResultSet getSuperTypes (String catalog, String schemaPattern, String typeNamePattern) throws SQLException {return wrap(d.getSuperTypes(catalog, schemaPattern, typeNamePattern));}
    public ResultSet getTablePrivileges (String catalog, String schemaPattern, String tableNamePattern) throws SQLException {return wrap(d.getTablePrivileges(catalog, schemaPattern, tableNamePattern));}
    public ResultSet getTableTypes () throws SQLException {return wrap(d.getTableTypes());}
    public ResultSet getTables (String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {return wrap(d.getTables(catalog, schemaPattern, tableNamePattern, types));}
    public ResultSet getTypeInfo () throws SQLException {return wrap(d.getTypeInfo());}
    public ResultSet getUDTs (String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {return wrap(d.getUDTs(catalog, schemaPattern, typeNamePattern, types));}
    public ResultSet getVersionColumns (String catalog, String schema, String table) throws SQLException {return wrap(d.getVersionColumns(catalog, schema, table));}
    public RowIdLifetime getRowIdLifetime () throws SQLException {return d.getRowIdLifetime();}
    public String getCatalogSeparator () throws SQLException {return d.getCatalogSeparator();}
    public String getCatalogTerm () throws SQLException {return d.getCatalogTerm();}
    public String getDatabaseProductName () throws SQLException {return d.getDatabaseProductName();}
    public String getDatabaseProductVersion () throws SQLException {return d.getDatabaseProductVersion();}
    public String getDriverName () throws SQLException {return d.getDriverName();}
    public String getDriverVersion () throws SQLException {return d.getDriverVersion();}
    public String getExtraNameCharacters () throws SQLException {return d.getExtraNameCharacters();}
    public String getIdentifierQuoteString () throws SQLException {return d.getIdentifierQuoteString();}
    public String getNumericFunctions () throws SQLException {return d.getNumericFunctions();}
    public String getProcedureTerm () throws SQLException {return d.getProcedureTerm();}
    public String getSQLKeywords () throws SQLException {return d.getSQLKeywords();}
    public String getSchemaTerm () throws SQLException {return d.getSchemaTerm();}
    public String getSearchStringEscape () throws SQLException {return d.getSearchStringEscape();}
    public String getStringFunctions () throws SQLException {return d.getStringFunctions();}
    public String getSystemFunctions () throws SQLException {return d.getSystemFunctions();}
    public String getTimeDateFunctions () throws SQLException {return d.getTimeDateFunctions();}
    public String getURL () throws SQLException {return d.getURL();}
    public String getUserName () throws SQLException {return d.getUserName();}
    public boolean allProceduresAreCallable () throws SQLException {return d.allProceduresAreCallable();}
    public boolean allTablesAreSelectable () throws SQLException {return d.allTablesAreSelectable();}
    public boolean autoCommitFailureClosesAllResultSets () throws SQLException {return d.autoCommitFailureClosesAllResultSets();}
    public boolean dataDefinitionCausesTransactionCommit () throws SQLException {return d.dataDefinitionCausesTransactionCommit();}
    public boolean dataDefinitionIgnoredInTransactions () throws SQLException {return d.dataDefinitionIgnoredInTransactions();}
    public boolean deletesAreDetected (int type) throws SQLException {return d.deletesAreDetected(type);}
    public boolean doesMaxRowSizeIncludeBlobs () throws SQLException {return d.doesMaxRowSizeIncludeBlobs();}
    public boolean generatedKeyAlwaysReturned () throws SQLException {return d.generatedKeyAlwaysReturned();}
    public boolean insertsAreDetected (int type) throws SQLException {return d.insertsAreDetected(type);}
    public boolean isCatalogAtStart () throws SQLException {return d.isCatalogAtStart();}
    public boolean isReadOnly () throws SQLException {return d.isReadOnly();}
    public boolean locatorsUpdateCopy () throws SQLException {return d.locatorsUpdateCopy();}
    public boolean nullPlusNonNullIsNull () throws SQLException {return d.nullPlusNonNullIsNull();}
    public boolean nullsAreSortedAtEnd () throws SQLException {return d.nullsAreSortedAtEnd();}
    public boolean nullsAreSortedAtStart () throws SQLException {return d.nullsAreSortedAtStart();}
    public boolean nullsAreSortedHigh () throws SQLException {return d.nullsAreSortedHigh();}
    public boolean nullsAreSortedLow () throws SQLException {return d.nullsAreSortedLow();}
    public boolean othersDeletesAreVisible (int type) throws SQLException {return d.othersDeletesAreVisible(type);}
    public boolean othersInsertsAreVisible (int type) throws SQLException {return d.othersInsertsAreVisible(type);}
    public boolean othersUpdatesAreVisible (int type) throws SQLException {return d.othersUpdatesAreVisible(type);}
    public boolean ownDeletesAreVisible (int type) throws SQLException {return d.ownDeletesAreVisible(type);}
    public boolean ownInsertsAreVisible (int type) throws SQLException {return d.ownInsertsAreVisible(type);}
    public boolean ownUpdatesAreVisible (int type) throws SQLException {return d.ownUpdatesAreVisible(type);}
    public boolean storesLowerCaseIdentifiers () throws SQLException {return d.storesLowerCaseIdentifiers();}
    public boolean storesLowerCaseQuotedIdentifiers () throws SQLException {return d.storesLowerCaseQuotedIdentifiers();}
    public boolean storesMixedCaseIdentifiers () throws SQLException {return d.storesMixedCaseIdentifiers();}
    public boolean storesMixedCaseQuotedIdentifiers () throws SQLException {return d.storesMixedCaseQuotedIdentifiers();}
    public boolean storesUpperCaseIdentifiers () throws SQLException {return d.storesUpperCaseIdentifiers();}
    public boolean storesUpperCaseQuotedIdentifiers () throws SQLException {return d.storesUpperCaseQuotedIdentifiers();}
    public boolean supportsANSI92EntryLevelSQL () throws SQLException {return d.supportsANSI92EntryLevelSQL();}
    public boolean supportsANSI92FullSQL () throws SQLException {return d.supportsANSI92FullSQL();}
    public boolean supportsANSI92IntermediateSQL () throws SQLException {return d.supportsANSI92IntermediateSQL();}
    public boolean supportsAlterTableWithAddColumn () throws SQLException {return d.supportsAlterTableWithAddColumn();}
    public boolean supportsAlterTableWithDropColumn () throws SQLException {return d.supportsAlterTableWithDropColumn();}
    public boolean supportsBatchUpdates () throws SQLException {return d.supportsBatchUpdates();}
    public boolean supportsCatalogsInDataManipulation () throws SQLException {return d.supportsCatalogsInDataManipulation();}
    public boolean supportsCatalogsInIndexDefinitions () throws SQLException {return d.supportsCatalogsInIndexDefinitions();}
    public boolean supportsCatalogsInPrivilegeDefinitions () throws SQLException {return d.supportsCatalogsInPrivilegeDefinitions();}
    public boolean supportsCatalogsInProcedureCalls () throws SQLException {return d.supportsCatalogsInProcedureCalls();}
    public boolean supportsCatalogsInTableDefinitions () throws SQLException {return d.supportsCatalogsInTableDefinitions();}
    public boolean supportsColumnAliasing () throws SQLException {return d.supportsColumnAliasing();}
    public boolean supportsConvert () throws SQLException {return d.supportsConvert();}
    public boolean supportsConvert (int fromType, int toType) throws SQLException {return d.supportsConvert(fromType, toType);}
    public boolean supportsCoreSQLGrammar () throws SQLException {return d.supportsCoreSQLGrammar();}
    public boolean supportsCorrelatedSubqueries () throws SQLException {return d.supportsCorrelatedSubqueries();}
    public boolean supportsDataDefinitionAndDataManipulationTransactions () throws SQLException {return d.supportsDataDefinitionAndDataManipulationTransactions();}
    public boolean supportsDataManipulationTransactionsOnly () throws SQLException {return d.supportsDataManipulationTransactionsOnly();}
    public boolean supportsDifferentTableCorrelationNames () throws SQLException {return d.supportsDifferentTableCorrelationNames();}
    public boolean supportsExpressionsInOrderBy () throws SQLException {return d.supportsExpressionsInOrderBy();}
    public boolean supportsExtendedSQLGrammar () throws SQLException {return d.supportsExtendedSQLGrammar();}
    public boolean supportsFullOuterJoins () throws SQLException {return d.supportsFullOuterJoins();}
    public boolean supportsGetGeneratedKeys () throws SQLException {return d.supportsGetGeneratedKeys();}
    public boolean supportsGroupBy () throws SQLException {return d.supportsGroupBy();}
    public boolean supportsGroupByBeyondSelect () throws SQLException {return d.supportsGroupByBeyondSelect();}
    public boolean supportsGroupByUnrelated () throws SQLException {return d.supportsGroupByUnrelated();}
    public boolean supportsIntegrityEnhancementFacility () throws SQLException {return d.supportsIntegrityEnhancementFacility();}
    public boolean supportsLikeEscapeClause () throws SQLException {return d.supportsLikeEscapeClause();}
    public boolean supportsLimitedOuterJoins () throws SQLException {return d.supportsLimitedOuterJoins();}
    public boolean supportsMinimumSQLGrammar () throws SQLException {return d.supportsMinimumSQLGrammar();}
    public boolean supportsMixedCaseIdentifiers () throws SQLException {return d.supportsMixedCaseIdentifiers();}
    public boolean supportsMixedCaseQuotedIdentifiers () throws SQLException {return d.supportsMixedCaseQuotedIdentifiers();}
    public boolean supportsMultipleOpenResults () throws SQLException {return d.supportsMultipleOpenResults();}
    public boolean supportsMultipleResultSets () throws SQLException {return d.supportsMultipleResultSets();}
    public boolean supportsMultipleTransactions () throws SQLException {return d.supportsMultipleTransactions();}
    public boolean supportsNamedParameters () throws SQLException {return d.supportsNamedParameters();}
    public boolean supportsNonNullableColumns () throws SQLException {return d.supportsNonNullableColumns();}
    public boolean supportsOpenCursorsAcrossCommit () throws SQLException {return d.supportsOpenCursorsAcrossCommit();}
    public boolean supportsOpenCursorsAcrossRollback () throws SQLException {return d.supportsOpenCursorsAcrossRollback();}
    public boolean supportsOpenStatementsAcrossCommit () throws SQLException {return d.supportsOpenStatementsAcrossCommit();}
    public boolean supportsOpenStatementsAcrossRollback () throws SQLException {return d.supportsOpenStatementsAcrossRollback();}
    public boolean supportsOrderByUnrelated () throws SQLException {return d.supportsOrderByUnrelated();}
    public boolean supportsOuterJoins () throws SQLException {return d.supportsOuterJoins();}
    public boolean supportsPositionedDelete () throws SQLException {return d.supportsPositionedDelete();}
    public boolean supportsPositionedUpdate () throws SQLException {return d.supportsPositionedUpdate();}
    public boolean supportsResultSetConcurrency (int type, int concurrency) throws SQLException {return d.supportsResultSetConcurrency(type, concurrency);}
    public boolean supportsResultSetHoldability (int holdability) throws SQLException {return d.supportsResultSetHoldability(holdability);}
    public boolean supportsResultSetType (int type) throws SQLException {return d.supportsResultSetType(type);}
    public boolean supportsSavepoints () throws SQLException {return d.supportsSavepoints();}
    public boolean supportsSchemasInDataManipulation () throws SQLException {return d.supportsSchemasInDataManipulation();}
    public boolean supportsSchemasInIndexDefinitions () throws SQLException {return d.supportsSchemasInIndexDefinitions();}
    public boolean supportsSchemasInPrivilegeDefinitions () throws SQLException {return d.supportsSchemasInPrivilegeDefinitions();}
    public boolean supportsSchemasInProcedureCalls () throws SQLException {return d.supportsSchemasInProcedureCalls();}
    public boolean supportsSchemasInTableDefinitions () throws SQLException {return d.supportsSchemasInTableDefinitions();}
    public boolean supportsSelectForUpdate () throws SQLException {return d.supportsSelectForUpdate();}
    public boolean supportsStatementPooling () throws SQLException {return d.supportsStatementPooling();}
    public boolean supportsStoredFunctionsUsingCallSyntax () throws SQLException {return d.supportsStoredFunctionsUsingCallSyntax();}
    public boolean supportsStoredProcedures () throws SQLException {return d.supportsStoredProcedures();}
    public boolean supportsSubqueriesInComparisons () throws SQLException {return d.supportsSubqueriesInComparisons();}
    public boolean supportsSubqueriesInExists () throws SQLException {return d.supportsSubqueriesInExists();}
    public boolean supportsSubqueriesInIns () throws SQLException {return d.supportsSubqueriesInIns();}
    public boolean supportsSubqueriesInQuantifieds () throws SQLException {return d.supportsSubqueriesInQuantifieds();}
    public boolean supportsTableCorrelationNames () throws SQLException {return d.supportsTableCorrelationNames();}
    public boolean supportsTransactionIsolationLevel (int level) throws SQLException {return d.supportsTransactionIsolationLevel(level);}
    public boolean supportsTransactions () throws SQLException {return d.supportsTransactions();}
    public boolean supportsUnion () throws SQLException {return d.supportsUnion();}
    public boolean supportsUnionAll () throws SQLException {return d.supportsUnionAll();}
    public boolean updatesAreDetected (int type) throws SQLException {return d.updatesAreDetected(type);}
    public boolean usesLocalFilePerTable () throws SQLException {return d.usesLocalFilePerTable();}
    public boolean usesLocalFiles () throws SQLException {return d.usesLocalFiles();}
    public int getDatabaseMajorVersion () throws SQLException {return d.getDatabaseMajorVersion();}
    public int getDatabaseMinorVersion () throws SQLException {return d.getDatabaseMinorVersion();}
    public int getDefaultTransactionIsolation () throws SQLException {return d.getDefaultTransactionIsolation();}
    public int getDriverMajorVersion () {return d.getDriverMajorVersion();}
    public int getDriverMinorVersion () {return d.getDriverMinorVersion();}
    public int getJDBCMajorVersion () throws SQLException {return d.getJDBCMajorVersion();}
    public int getJDBCMinorVersion () throws SQLException {return d.getJDBCMinorVersion();}
    public int getMaxBinaryLiteralLength () throws SQLException {return d.getMaxBinaryLiteralLength();}
    public int getMaxCatalogNameLength () throws SQLException {return d.getMaxCatalogNameLength();}
    public int getMaxCharLiteralLength () throws SQLException {return d.getMaxCharLiteralLength();}
    public int getMaxColumnNameLength () throws SQLException {return d.getMaxColumnNameLength();}
    public int getMaxColumnsInGroupBy () throws SQLException {return d.getMaxColumnsInGroupBy();}
    public int getMaxColumnsInIndex () throws SQLException {return d.getMaxColumnsInIndex();}
    public int getMaxColumnsInOrderBy () throws SQLException {return d.getMaxColumnsInOrderBy();}
    public int getMaxColumnsInSelect () throws SQLException {return d.getMaxColumnsInSelect();}
    public int getMaxColumnsInTable () throws SQLException {return d.getMaxColumnsInTable();}
    public int getMaxConnections () throws SQLException {return d.getMaxConnections();}
    public int getMaxCursorNameLength () throws SQLException {return d.getMaxCursorNameLength();}
    public int getMaxIndexLength () throws SQLException {return d.getMaxIndexLength();}
    public int getMaxProcedureNameLength () throws SQLException {return d.getMaxProcedureNameLength();}
    public int getMaxRowSize () throws SQLException {return d.getMaxRowSize();}
    public int getMaxSchemaNameLength () throws SQLException {return d.getMaxSchemaNameLength();}
    public int getMaxStatementLength () throws SQLException {return d.getMaxStatementLength();}
    public int getMaxStatements () throws SQLException {return d.getMaxStatements();}
    public int getMaxTableNameLength () throws SQLException {return d.getMaxTableNameLength();}
    public int getMaxTablesInSelect () throws SQLException {return d.getMaxTablesInSelect();}
    public int getMaxUserNameLength () throws SQLException {return d.getMaxUserNameLength();}
    public int getResultSetHoldability () throws SQLException {return d.getResultSetHoldability();}
    public int getSQLStateType () throws SQLException {return d.getSQLStateType();}}
