(ns doublets.solver-test
  (:require [clojure.test :refer :all]
            [doublets.solver :refer :all]))

(deftest solver-test
  (testing "with word links found"
    (is (= ["head" "heal" "teal" "tell" "tall" "tail"]
           (doublets "head" "tail")))

    (is (= ["door" "boor" "book" "look" "lock"]
           (doublets "door" "lock")))

    (is (= ["bank" "bonk" "book" "look" "loon" "loan"]
           (doublets "bank" "loan")))

    (is (= ["wheat" "cheat" "cheap" "cheep" "creep" "creed" "breed" "bread"]
           (doublets "wheat" "bread"))))

  (testing "with no word links found"
    (is (= []
           (doublets "ye" "freezer")))))

(deftest can-is-step-1?
  (are [res w1 w2] (= res (is-step-1? w1 w2))
                   false "" ""
                   true "aaa" "aba"
                   false "aaa" "ccc"
                   false "aaa" "aaa"
                   false "aaa" "aaab"))

(deftest can-filter-step-1-words
  (is (= ["aab" "aba"] (select-step-1-words ["aab" "ccc" "aba"] "aaa"))))