# Make variables for definining important project directories

SRC_DIR = src
LIB_DIR = lib
BIN_DIR = bin
OBJ_DIR = classes

# Automake variables for automated testing

TESTS =	tests/CatDriver/testJDBCCompliance.bsh \
	tests/CatDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/CatDriver/testVersionInfo.bsh \
	tests/CatDriver/testConnectIndirectly.bsh \
	tests/CatDriver/testAcceptsURL.bsh \
	tests/CatDriver/testConnectDirectly.bsh \
	tests/TeeDriver/testJDBCCompliance.bsh \
	tests/TeeDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/TeeDriver/testVersionInfo.bsh \
	tests/TeeDriver/testAcceptsURL.bsh \
	tests/TeeDriver/testConnectIndirectlyWithBadURLFails.bsh \
	tests/TeeDriver/testConnectIndirectlyWithGoodURLSucceeds.bsh \
	tests/TeeDriver/testConnectDirectly.bsh \
	tests/PoolDriver/testGetConnectionTwiceProducesTwoDistinctConnections.bsh \
	tests/PoolDriver/testJDBCCompliance.bsh \
	tests/PoolDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/PoolDriver/testVersionInfo.bsh \
	tests/PoolDriver/testCallMethodOnClosedPooledConnectionThrowsSQLException.bsh \
	tests/PoolDriver/testConnectIndirectly.bsh \
	tests/PoolDriver/testAcceptsURL.bsh \
	tests/PoolDriver/testConnectDirectly.bsh \
	tests/DriverComposition/testTeePlusSinkButReverseOrder.bsh \
	tests/DriverComposition/testUrlsCanHaveWhitespace.bsh \
	tests/DriverComposition/testProgressiveComposition.bsh \
	tests/DriverComposition/testTeePlusSink.bsh \
	tests/LogDriver/testJDBCCompliance.bsh \
	tests/LogDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/LogDriver/testVersionInfo.bsh \
	tests/LogDriver/testConnectIndirectly.bsh \
	tests/LogDriver/testAcceptsURL.bsh \
	tests/LogDriver/testConnectDirectly.bsh \
	tests/SinkDriver/testJDBCCompliance.bsh \
	tests/SinkDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/SinkDriver/testVersionInfo.bsh \
	tests/SinkDriver/testConnectIndirectly.bsh \
	tests/SinkDriver/testAcceptsURL.bsh \
	tests/SinkDriver/testConnectDirectly.bsh \
	tests/FilterDriver/testJDBCCompliance.bsh \
	tests/FilterDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/FilterDriver/testVersionInfo.bsh \
	tests/FilterDriver/testConnectIndirectly.bsh \
	tests/FilterDriver/testAcceptsURL.bsh \
	tests/FilterDriver/testConnectDirectly.bsh \
	tests/FilterDriver/testUpcaseFilter.bsh \
	tests/FilterDriver/testAssertionFilter.bsh

XFAIL_TESTS =

# Automake variables for building and distribution

EXTRA_DIST = $(LIB_DIR) tests $(SRC_DIR)
EXTRA_SCRIPTS = $(BIN_DIR)
CLEANFILES = @PACKAGE@ @PACKAGE@.jar sources
TESTS_ENVIRONMENT = CLASSPATH=@PACKAGE@.jar
TEST_EXTENSIONS = .bsh
AM_JAVACFLAGS = -sourcepath $(srcdir)/$(SRC_DIR) -Xlint:unchecked -Xlint:deprecation
AM_BSH_LOG_FLAGS = pjdbc.jar
BSH_LOG_COMPILER = $(srcdir)/$(BIN_DIR)/bshunit
dist_bin_SCRIPTS = $(BIN_DIR)/bshunit
dist_pkgdata_DATA = @PACKAGE@.jar $(shell find $(srcdir)/$(LIB_DIR) -type f -iname '*.jar') 

# Make variable for setting the build class path

CLASSPATH = $(shell find $(srcdir)/$(LIB_DIR) -name '*.jar' -printf '%p:')

# Make target for creating the application binary

@PACKAGE@.jar: sources
	$(shell mkdir -p $(OBJ_DIR))
	@JAVAC@ -sourcepath $(srcdir)/$(SRC_DIR) -d $(OBJ_DIR) -Xlint:unchecked -Xlint:deprecation -cp $(CLASSPATH) @sources
	touch $@
	@JAR@ -uf $@ -C $(OBJ_DIR) .

# Make target for generating a list of source files to compile

sources: $(shell find $(srcdir)/$(SRC_DIR) -iname "*.java")
	echo $? > $@

# Automake target for additional objects to clean

clean-local:
	rm -rf $(OBJ_DIR)

rmi:
	rmic -d . com.jw.server.RemoteDriverImpl
	rmic -d . com.jw.server.RemoteConnectionImpl
	rmic -d . com.jw.server.RemoteStatementImpl
	rmic -d . com.jw.server.RemoteResultSetImpl

server:
	@JAVA@ -Djava.security.policy=./java.policy com.jw.server.RemoteDriverImpl

# ################################################################################
# DOTS stuff
# ################################################################################

dots-server:
	@JAVA@ -cp Perfmon.jar dots.perfmon.PerfMon -port 8001

dots-test: dots-BTCJ1 dots-BTCJ2 dots-BTCJ3 dots-BTCJ4 dots-BTCJ5 dots-BTCJ6 dots-BTCJ7 dots-BTCJ8 dots-ATCJ1 dots-ATCJ1

dots-BTCJ1:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case BTCJ1

dots-BTCJ2:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case BTCJ2

dots-BTCJ3:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case BTCJ3

dots-BTCJ4:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case BTCJ4

dots-BTCJ5:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case BTCJ5

dots-BTCJ6:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case BTCJ6

dots-BTCJ7:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case BTCJ7

dots-BTCJ8:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case BTCJ8

dots-ATCJ1:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case ATCJ1

dots-ATCJ2:
	@JAVA@ -cp Dots.jar:/usr/share/java/postgresql-jdbc4.jar dots.framework.Dots -config pgsql.ini -case ATCJ2
