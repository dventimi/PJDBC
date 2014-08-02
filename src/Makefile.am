include sources.am

dist_pkgdata_DATA = @PACKAGE@.jar
BUILT_SOURCES = @PACKAGE@.jar @PACKAGE@.arg
EXTRA_DIST = $(SOURCES)
CLEANFILES = @PACKAGE@.jar @PACKAGE@.arg
SUFFIXES = .arg .jar

.arg.jar:
	-mkdir -p $(builddir)/java-$@
	cd $(srcdir) && find . -type f ! -iregex "\(.*\.java\|.*\.in\|.*\.am\|.*find-sources\)" -exec cp --parents {} $(abs_builddir)/java-$@ \;
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) -d $(builddir)/java-$@ @$?
	@JAR@ -cf $(builddir)/$@ -C $(builddir)/java-$@ .

@PACKAGE@.arg: $(SOURCES)
	echo $? > $@

clean-local:
	-rm -rf $(builddir)/java-*

