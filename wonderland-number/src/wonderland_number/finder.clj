(ns wonderland-number.finder)

(defn wonderland-number []
  (first
    (for [i (range 100000 (int (/ 999999 6)))
          :when (->> (range 2 7)
                     (sequence (comp
                                 (map #(* i %))
                                 (map str)
                                 (map set)))
                     (apply =))]
      i)
    #_(for [i (range 100000 (int (/ 999999 6)))
            :when (->> (range 2 7)
                       (map #(* i %))
                       (map str)
                       (map set)
                       (apply =))]
        i)))
