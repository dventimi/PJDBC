AM_JAVACFLAGS = -Xlint:unchecked -Xlint:deprecation

@PACKAGE@.jar: sources
	@JAVAC@ $(JAVACFLAGS) $(AM_JAVACFLAGS) @sources

sources: $(shell find $(srcdir) -iname "*.java")
	echo $? > $@

