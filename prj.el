(jde-project-file-version "1.0")
(jde-set-variables
 '(jde-global-classpath (quote ("./classes" "./lib/junit-4.8.2.jar" "./lib/derby.jar" "./lib/ST-4.0.jar" "./lib/antlr-3.3-complete.jar")))
 '(jde-compiler (quote ("javac" "")))
 '(jde-compile-option-directory "./classes")
 '(jde-compile-option-sourcepath (quote ("./src")))
 '(jde-sourcepath (quote ("./src")))
 '(jde-compile-option-debug (quote ("all" (t nil nil))))
 '(jde-compile-option-hide-classpath nil)
 '(jde-cygwin-path-converter (quote (jde-cygwin-path-converter-custom)))
 '(jde-compile-option-command-line-args (quote ("-Xlint:unchecked"))))
(defun jde-cygwin-path-converter-custom (path)
  (if (string= (getenv "QMAKESPEC") "cygwin-g++") (jde-cygwin-path-converter-cygpath path) path))

(customize-set-variable 'jde-cygwin-path-converter '(jde-cygwin-path-converter-custom))


