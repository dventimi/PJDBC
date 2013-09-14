;;; Directory Local Variables
;;; See Info node `(emacs) Directory Variables' for more information.

((nil
  (compile-command . (concat "make -k -C " (let ((l (dir-locals-find-file (or (buffer-file-name) default-directory)))) (if (listp l) (car l) l)))))
 (java-mode
  (eval . (setq semanticdb-javap-classpath (directory-files (concat (let ((l (dir-locals-find-file (or (buffer-file-name) default-directory)))) (if (listp l) (car l) l)) "lib") t ".*\.jar")))))

