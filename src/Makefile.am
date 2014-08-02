include sources.am

dist_pkgdata_DATA = @PACKAGE@.jar
AM_JAVACFLAGS = -Xlint:unchecked -Xlint:deprecation
CLEANFILES = @PACKAGE@.jar javac_args

@PACKAGE@.jar: javac_args
	-mkdir -p $(builddir)/java
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) -d $(builddir)/java @javac_args
	@JAR@ -cf $@ -C $(builddir)/java .

javac_args: $(SOURCES)
	echo $? > $@

clean-local:
	-rm -rf $(builddir)/java

dist-hook:
	-rm -rf `find $(distdir) -type f -iname "*.class"`
