include sources.am

dist_pkgdata_DATA = @PACKAGE@.jar
AM_JAVACFLAGS = -Xlint:unchecked -Xlint:deprecation
CLEANFILES = @PACKAGE@.jar jar_args javac_args

@PACKAGE@.jar: $(SOURCES)
	find $(srcdir) -iname "*.java" -printf "%p " > javac_args
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) @javac_args
	find $(srcdir) ! -iname "*.java" ! -iname "Makefile*" -type f > jar_args
	@JAR@ -cf $@ @jar_args

clean-local:
	-rm -rf `find $(srcdir) -type f -iname "*.class"`

dist-hook:
	-rm -rf `find $(distdir) -type f -iname "*.class"`
