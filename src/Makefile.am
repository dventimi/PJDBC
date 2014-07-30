dist_pkgdata_DATA = @PACKAGE@.jar
AM_JAVACFLAGS = -Xlint:unchecked -Xlint:deprecation
CLEANFILES = @PACKAGE@.jar jar_args javac_args

@PACKAGE@.jar:
	find . -iname "*.java" -printf "%p " > javac_args
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) @javac_args
	find . ! -iname "*.java" ! -iname "Makefile*" -type f > jar_args
	@JAR@ -cf $@ @jar_args

clean-local:
	-find . -name "*.class" -exec rm {} \;
