PJDBC (README)
====================
This is the README for the PJDBC application, which is kept in a
GitHub repository at

    https://bitbucket.org/dventimi/PJDBC


POSIX Installation
--------------------

Installation instructions for POSIX-compliant platforms, such as:
Unix, Linux, BSD Unix, and Mac OS X.

Though it is written in Java, PJDBC adheres to the GNU coding
standards (see http://www.gnu.org/prep/standards/), so it adopts that
standard's Autotools convention for building and installing.  To
configure, build, and install PJDBC, execute these statements in a
command-line shell:

    ./configure
    make
    make install

Note that the third step will typically require elevated privileges,
so you may need either to be logged in as a superuser, `su` to the
superuser, or use something like `sudo`.  Note further that since the
configure script supports all the standard options, you can also use
it to change the installation location to a directory that is
writeable for you.  For more information, run configure with the help
option:

    ./configure --help

Windows Installation
--------------------

For Windows installation, perform the compilation and install steps
above within a Cygwin (http://www.cygwin.com/) console window.

Requirements
--------------------

1. PJDBC is written in Java, and JDK 1.7
   (http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
   is required.

<!-- Local Variables: -->
<!-- mode: markdown   -->
<!-- End:             -->
