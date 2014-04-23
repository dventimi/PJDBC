SRC_DIR = src
LIB_DIR = lib
BIN_DIR = bin
OBJ_DIR = classes

TESTS =	tests/testTestingFrameworkNegatively.bsh \
	tests/testTestingFrameworkPositively.bsh \
	tests/CatDriver/testJDBCCompliance.bsh \
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
	tests/PoolingDriver/testGetConnectionTwiceProducesTwoDistinctConnections.bsh \
	tests/PoolingDriver/testJDBCCompliance.bsh \
	tests/PoolingDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/PoolingDriver/testVersionInfo.bsh \
	tests/PoolingDriver/testCallMethodOnClosedPooledConnectionThrowsSQLException.bsh \
	tests/PoolingDriver/testConnectIndirectly.bsh \
	tests/PoolingDriver/testAcceptsURL.bsh \
	tests/PoolingDriver/testConnectDirectly.bsh \
	tests/DriverComposition/testTeePlusSinkButReverseOrder.bsh \
	tests/DriverComposition/testUrlsCanHaveWhitespace.bsh \
	tests/DriverComposition/testProgressiveComposition.bsh \
	tests/DriverComposition/testTeePlusSink.bsh \
	tests/LoggingDriver/testJDBCCompliance.bsh \
	tests/LoggingDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/LoggingDriver/testVersionInfo.bsh \
	tests/LoggingDriver/testConnectIndirectly.bsh \
	tests/LoggingDriver/testAcceptsURL.bsh \
	tests/LoggingDriver/testConnectDirectly.bsh \
	tests/SinkDriver/testJDBCCompliance.bsh \
	tests/SinkDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/SinkDriver/testVersionInfo.bsh \
	tests/SinkDriver/testConnectIndirectly.bsh \
	tests/SinkDriver/testAcceptsURL.bsh \
	tests/SinkDriver/testConnectDirectly.bsh \
	tests/FilteringDriver/testJDBCCompliance.bsh \
	tests/FilteringDriver/testConnectDirectlyAndInvokeMethods.bsh \
	tests/FilteringDriver/testVersionInfo.bsh \
	tests/FilteringDriver/testConnectIndirectly.bsh \
	tests/FilteringDriver/testAcceptsURL.bsh \
	tests/FilteringDriver/testConnectDirectly.bsh

XFAIL_TESTS = tests/testTestingFrameworkNegatively.bsh

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

$(shell mkdir -p $(OBJ_DIR))

@PACKAGE@.jar: classjava.stamp $(shell find $(srcdir)/$(SRC_DIR) -type f ! -iname '*.java')
	cd $(srcdir)/$(SRC_DIR) ; find . -type f ! -iname '*.java' -exec cp --parents -u {} ../$(OBJ_DIR) \;
	touch $@
	@JAR@ -uf $@ -C $(OBJ_DIR) .

clean-local:
	rm -rf $(OBJ_DIR)

