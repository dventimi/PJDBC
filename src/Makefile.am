include sources.am

dist_pkgdata_DATA = pjdbc.jar
AM_JAVACFLAGS = -Xlint:unchecked -Xlint:deprecation
CLEANFILES = pjdbc.jar pjdbc.arg
EXTRA_DIST = $(SOURCES)

BUILT_SOURCES = pjdbc.jar pjdbc.arg

OBJEXT = jar

SUFFIXES = .arg .jar

.arg.jar:
	-mkdir -p $(builddir)/java-$@
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) -d $(builddir)/java-$@ @$?
	@JAR@ -cf $(builddir)/$@ -C $(builddir)/java-$@ .

pjdbc.arg: $(SOURCES)
	echo $? > $@

clean-local:
	-rm -rf $(builddir)/java-*

