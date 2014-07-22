SUBDIRS = tests

SRC_DIR = src
OBJ_DIR = classes
TST_DIR = tests

AM_JAVACFLAGS = -sourcepath $(srcdir)/$(SRC_DIR) -d $(OBJ_DIR) -Xlint:unchecked -Xlint:deprecation
EXTRA_DIST = $(SRC_DIR) $(TST_DIR)
CLEANFILES = @PACKAGE@.jar sources
dist_pkgdata_DATA = @PACKAGE@.jar

@PACKAGE@.jar: sources
	$(shell mkdir -p $(OBJ_DIR))
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) @sources
	@JAR@ -cf $@ -C $(OBJ_DIR) .

sources: $(shell find $(srcdir)/$(SRC_DIR) -iname "*.java")
	echo $? > $@

clean-local:
	-rm -rf $(OBJ_DIR)

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
