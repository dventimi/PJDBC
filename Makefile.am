## Process this file with automake to produce Makefile.in
AM_JAVACFLAGS=-Xlint:unchecked -g
JAVAROOT=$(top_builddir)/classes
CLEANFILES = *.class
javadir = $(top_builddir)/src
classesdir = $(top_builddir)/classes
jardir = $(prefix)/java
classes_JAVA = $(shell find . -name '*.java')
CLASSPATH = $(top_builddir)/lib/derby.jar:$(top_builddir)/lib/junit-4.8.2.jar:$(top_builddir)/classes
CLASSPATH_ENV = export CLASSPATH=$(CLASSPATH);
JARFILE = pjdbc.jar

.PHONY: clean-local-check

all-local: $(JARFILE)

install-data-local: $(JARFILE)
	@MKDIR_P@ $(jardir)
	cp $(JARFILE) $(jardir)

uninstall-local:
	rm $(jardir)/$(JARFILE)

$(JARFILE): $(classes_JAVA)
	cd
	@JAR@ -cf $(JARFILE) -C $(classesdir) .

clean-local: clean-local-check

clean-local-check:
	rm $(JARFILE)
	cd $(classesdir)
	find . -name *.class -execdir rm {} +


check:
	@JAVA@ -classpath $(CLASSPATH) org.junit.runner.JUnitCore com.omnicorps.global.pjdbc.InterceptingDriverTest
