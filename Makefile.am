SRC_DIR = src
LIB_DIR = lib
BIN_DIR = bin
OBJ_DIR = classes

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

dist_pkgdata_DATA = @PACKAGE@.jar $(shell find $(srcdir)/$(LIB_DIR) -type f -iname '*.jar') 
dist_java_JAVA = $(shell find $(srcdir)/$(SRC_DIR) -type f -iname '*.java')
javadir = $(datadir)/java
JAVAROOT = $(OBJ_DIR)
CLASSPATH = $(shell find $(srcdir)/$(LIB_DIR) -name '*.jar' -printf '%p:')
CLASSPATH_ENV = export CLASSPATH=$(shell find $(srcdir)/$(LIB_DIR) -name '*.jar' -printf '%p:');
AM_JAVACFLAGS = -sourcepath $(srcdir)/$(SRC_DIR) -Xlint:unchecked -Xlint:deprecation
EXTRA_DIST = $(LIB_DIR) $(BIN_DIR)/@PACKAGE@.in
EXTRA_SCRIPTS = $(BIN_DIR)
CLEANFILES =  @PACKAGE@ @PACKAGE@.jar
TESTS_ENVIRONMENT = CLASSPATH=@PACKAGE@.jar
TEST_EXTENSIONS = .bsh
BSH_LOG_COMPILER = ./bshunit
AM_BSH_LOG_FLAGS = pjdbc.jar

$(shell mkdir -p $(OBJ_DIR))

@PACKAGE@.jar: classjava.stamp $(shell find $(srcdir)/$(SRC_DIR) -type f ! -iname '*.java')
	cd $(srcdir)/$(SRC_DIR) ; find . -type f ! -iname '*.java' -exec cp --parents -u {} ../$(OBJ_DIR) \;
	touch $@
	@JAR@ -uf $@ -C $(OBJ_DIR) .

clean-local:
	rm -rf $(OBJ_DIR)

rmi:
	rmic -d . com.jw.server.RemoteDriverImpl
	rmic -d . com.jw.server.RemoteConnectionImpl
	rmic -d . com.jw.server.RemoteStatementImpl
	rmic -d . com.jw.server.RemoteResultSetImpl

server:
	java -Djava.security.policy=./java.policy com.jw.server.RemoteDriverImpl
