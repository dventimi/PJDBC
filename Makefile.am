include sources.am

SUBDIRS = src tests

SRC_DIR = src
OBJ_DIR = classes
TST_DIR = tests

AM_JAVACFLAGS = -sourcepath $(srcdir)/$(SRC_DIR) -d $(OBJ_DIR) -Xlint:unchecked -Xlint:deprecation
EXTRA_DIST = $(SRC_DIR) $(TST_DIR)
CLEANFILES = @PACKAGE@.jar sources
dist_pkgdata_DATA = @PACKAGE@.jar

@PACKAGE@.jar: sources
	mkdir -p $(OBJ_DIR)
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) @sources
	@JAR@ -cf $@ -C $(OBJ_DIR) .

sources: $(SOURCES)
	echo $? > $@

clean-local:
	-rm -rf $(OBJ_DIR)
