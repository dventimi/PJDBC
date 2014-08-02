include tests.am

TEST_EXTENSIONS = .bsh
AM_BSH_LOG_FLAGS = $(top_builddir)/src/@PACKAGE@.jar
BSH_LOG_COMPILER = bshunit
EXTRA_DIST = $(TESTS)

clean-local:
	-rm -rf `find $(distdir) -type f -iname "*.log"`
	-rm -rf `find $(distdir) -type f -iname "*.trs"`

dist-hook:
	-rm -rf `find $(distdir) -type f -iname "*.log"`
	-rm -rf `find $(distdir) -type f -iname "*.trs"`
