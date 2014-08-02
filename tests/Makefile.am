TST_DIR = tests
TEST_EXTENSIONS = .bsh
AM_BSH_LOG_FLAGS = $(top_builddir)/src/@PACKAGE@.jar
BSH_LOG_COMPILER = bshunit
include tests.am

dist-hook:
	-rm -rf `find $(distdir) -type f -iname "*.log"`
	-rm -rf `find $(distdir) -type f -iname "*.trs"`
