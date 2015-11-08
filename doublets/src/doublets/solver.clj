(ns doublets.solver
  (:require [clojure.java.io :as io]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn is-step-1? [w1 w2]
  (= 1 (->> (map = w1 w2)
            (filter false?)
            count)))

(defn prepare-dict [word-list start-word end-word]
  (-> (filter #(= (count start-word) (count %)) word-list)
      (set)
      (disj start-word)
      (conj end-word)))

(defn select-step-1-words [dict word]
  (filter #(is-step-1? word %) dict))

(defn doublets-impl [chain dict end-word]
  (let [cur-word (last chain)]
    (if (= cur-word end-word)
      chain
      (some seq (for [w (select-step-1-words dict (last chain))]
                  (doublets-impl (conj chain w)
                                 (disj dict w)
                                 end-word))))))

(defn doublets [word1 word2]
  (let [len1 (count word1)
        len2 (count word2)]
    (if (not= len1 len2)
      []
      (let [dict (prepare-dict words word1 word2)]
        (vec (doublets-impl [word1] dict word2))))))