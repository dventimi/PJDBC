include sources.am

dist_pkgdata_DATA = @PACKAGE@.jar
BUILT_SOURCES = @PACKAGE@.jar @PACKAGE@.arg
EXTRA_DIST = $(SOURCES)
CLEANFILES = @PACKAGE@.jar @PACKAGE@.arg
SUFFIXES = .arg .jar

.arg.jar: resources
	-mkdir -p $(builddir)/.classes
	cd $(srcdir) && find . -type f \
	! -iname "*.java" \
	! -iname "*.in" \
	! -iname "*.am" \
	! -iname "*.arg" \
	! -iname "find-sources" \
	! -iname "Makefile" \
	! -iname "@" \
	! -iregex ".*\.classes.*" \
	-exec cp --parents {} $(abs_builddir)/.classes \;
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) -d $(builddir)/.classes -sourcepath $(srcdir) @$?
	@JAR@ -cf $(builddir)/$@ -C $(builddir)/.classes .

@PACKAGE@.arg: $(SOURCES)
	echo $? > $@

clean-local:
	-rm -rf $(builddir)/.classes
