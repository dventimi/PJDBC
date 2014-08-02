include sources.am

dist_pkgdata_DATA = @PACKAGE@.jar
BUILT_SOURCES = @PACKAGE@.jar @PACKAGE@.arg
EXTRA_DIST = $(SOURCES)
CLEANFILES = @PACKAGE@.jar @PACKAGE@.arg
SUFFIXES = .arg .jar

.arg.jar:
	-mkdir -p $(builddir)/.classes
	cd $(srcdir) && find . -type f ! -iregex "\(.*\.java\|.*\.in\|.*\.am\|.*find-sources\)" -exec cp --parents {} $(abs_builddir)/.classes \;
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) -d $(builddir)/.classes @$?
	@JAR@ -cf $(builddir)/$@ -C $(builddir)/.classes .

@PACKAGE@.arg: $(SOURCES)
	echo $? > $@

clean-local:
	-rm -rf $(builddir)/.classes
