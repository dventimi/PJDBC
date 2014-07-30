SUBDIRS = src tests

SRC_DIR = src
OBJ_DIR = classes
TST_DIR = tests

AM_JAVACFLAGS = -sourcepath $(srcdir)/$(SRC_DIR) -Xlint:unchecked -Xlint:deprecation
EXTRA_DIST = $(SRC_DIR) $(TST_DIR)
CLEANFILES = @PACKAGE@.jar sources
dist_pkgdata_DATA = @PACKAGE@.jar

@PACKAGE@.jar: @JAVA_SOURCES@
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) $?
	find $(SRC_DIR) ! -iname "*.java" ! -iname "Makefile*" -type f | cut -d/ -f2- | @SED@ "s/^/-C $(SRC_DIR) /" > jar_args
	@JAR@ -cf $@ @jar_args

clean-local:
	-rm -rf $(OBJ_DIR)
	-rm jar_args
	-find . -name "*.class" -exec rm {} \;
