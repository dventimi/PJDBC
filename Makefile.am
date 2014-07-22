SRC_DIR = src
OBJ_DIR = classes
TST_DIR = tests

AM_JAVACFLAGS = -sourcepath $(srcdir)/$(SRC_DIR) -d $(OBJ_DIR) -Xlint:unchecked -Xlint:deprecation
EXTRA_DIST = $(SRC_DIR) $(TST_DIR)
CLEANFILES = @PACKAGE@.jar sources
TESTS_ENVIRONMENT = CLASSPATH=@PACKAGE@.jar
TEST_EXTENSIONS = .bsh
AM_BSH_LOG_FLAGS = @PACKAGE@.jar
BSH_LOG_COMPILER = $(srcdir)/$(TST_DIR)/bshunit
dist_pkgdata_DATA = @PACKAGE@.jar

@PACKAGE@.jar: sources
	$(shell mkdir -p $(OBJ_DIR))
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) @sources
	@JAR@ -cf $@ -C $(OBJ_DIR) .

sources: $(shell find $(srcdir)/$(SRC_DIR) -iname "*.java")
	echo $? > $@

clean-local:
	-rm -rf $(OBJ_DIR)

TESTS =	$(TST_DIR)/CatDriver/testJDBCCompliance.bsh \
	$(TST_DIR)/CatDriver/testConnectDirectlyAndInvokeMethods.bsh \
	$(TST_DIR)/CatDriver/testVersionInfo.bsh \
	$(TST_DIR)/CatDriver/testConnectIndirectly.bsh \
	$(TST_DIR)/CatDriver/testAcceptsURL.bsh \
	$(TST_DIR)/CatDriver/testConnectDirectly.bsh \
	$(TST_DIR)/TeeDriver/testJDBCCompliance.bsh \
	$(TST_DIR)/TeeDriver/testConnectDirectlyAndInvokeMethods.bsh \
	$(TST_DIR)/TeeDriver/testVersionInfo.bsh \
	$(TST_DIR)/TeeDriver/testAcceptsURL.bsh \
	$(TST_DIR)/TeeDriver/testConnectIndirectlyWithBadURLFails.bsh \
	$(TST_DIR)/TeeDriver/testConnectIndirectlyWithGoodURLSucceeds.bsh \
	$(TST_DIR)/TeeDriver/testConnectDirectly.bsh \
	$(TST_DIR)/PoolDriver/testGetConnectionTwiceProducesTwoDistinctConnections.bsh \
	$(TST_DIR)/PoolDriver/testJDBCCompliance.bsh \
	$(TST_DIR)/PoolDriver/testConnectDirectlyAndInvokeMethods.bsh \
	$(TST_DIR)/PoolDriver/testVersionInfo.bsh \
	$(TST_DIR)/PoolDriver/testCallMethodOnClosedPooledConnectionThrowsSQLException.bsh \
	$(TST_DIR)/PoolDriver/testConnectIndirectly.bsh \
	$(TST_DIR)/PoolDriver/testAcceptsURL.bsh \
	$(TST_DIR)/PoolDriver/testConnectDirectly.bsh \
	$(TST_DIR)/DriverComposition/testTeePlusSinkButReverseOrder.bsh \
	$(TST_DIR)/DriverComposition/testUrlsCanHaveWhitespace.bsh \
	$(TST_DIR)/DriverComposition/testProgressiveComposition.bsh \
	$(TST_DIR)/DriverComposition/testTeePlusSink.bsh \
	$(TST_DIR)/LogDriver/testJDBCCompliance.bsh \
	$(TST_DIR)/LogDriver/testConnectDirectlyAndInvokeMethods.bsh \
	$(TST_DIR)/LogDriver/testVersionInfo.bsh \
	$(TST_DIR)/LogDriver/testConnectIndirectly.bsh \
	$(TST_DIR)/LogDriver/testAcceptsURL.bsh \
	$(TST_DIR)/LogDriver/testConnectDirectly.bsh \
	$(TST_DIR)/SinkDriver/testJDBCCompliance.bsh \
	$(TST_DIR)/SinkDriver/testConnectDirectlyAndInvokeMethods.bsh \
	$(TST_DIR)/SinkDriver/testVersionInfo.bsh \
	$(TST_DIR)/SinkDriver/testConnectIndirectly.bsh \
	$(TST_DIR)/SinkDriver/testAcceptsURL.bsh \
	$(TST_DIR)/SinkDriver/testConnectDirectly.bsh \
	$(TST_DIR)/FilterDriver/testJDBCCompliance.bsh \
	$(TST_DIR)/FilterDriver/testConnectDirectlyAndInvokeMethods.bsh \
	$(TST_DIR)/FilterDriver/testVersionInfo.bsh \
	$(TST_DIR)/FilterDriver/testConnectIndirectly.bsh \
	$(TST_DIR)/FilterDriver/testAcceptsURL.bsh \
	$(TST_DIR)/FilterDriver/testConnectDirectly.bsh \
	$(TST_DIR)/FilterDriver/testUpcaseFilter.bsh \
	$(TST_DIR)/FilterDriver/testAssertionFilter.bsh

XFAIL_TESTS =

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
