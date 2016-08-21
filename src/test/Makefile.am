include sources.am

TEST_EXTENSIONS = .bsh
AM_BSH_LOG_FLAGS = $(top_builddir)/java/@PACKAGE@.jar
BSH_LOG_COMPILER = $(srcdir)/bshunit
EXTRA_DIST = $(TESTS) bshunit

clean-local:
	-rm -rf `find $(distdir) -type f -iname "*.log"`
	-rm -rf `find $(distdir) -type f -iname "*.trs"`

dist-hook:
	-rm -rf `find $(distdir) -type f -iname "*.log"`
	-rm -rf `find $(distdir) -type f -iname "*.trs"`
