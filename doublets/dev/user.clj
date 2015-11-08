(ns user
  (:require [clojure.repl :refer [apropos dir doc find-doc pst source]]
            [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [clojure.test :as test]))

(defn run-tests []
  (test/run-all-tests #"doublets\..*"))

(defn tests []
  (refresh :after 'user/run-tests))
