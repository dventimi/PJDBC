AC_PREREQ([2.62])
AC_INIT([PJDBC], [0.9], [http://www.pjdbc.org])
AC_CONFIG_AUX_DIR([build-aux])
AC_CANONICAL_TARGET
AM_INIT_AUTOMAKE
AC_CONFIG_SRCDIR([src/org/pjdbc/util/AbstractDriver.java])
AC_PROG_SED
AC_PROG_JAVAC
AC_PROG_JAR
AC_PROG_INSTALL
JAVA_SOURCES=$(find src -iname "*.java" -printf "%p ")
AC_SUBST([JAVA_SOURCES])
AC_CONFIG_FILES([
        Makefile
        src/Makefile
        tests/Makefile])
AC_OUTPUT
