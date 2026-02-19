(ns tutorial.chapter3
  (:require [clojure.string :as str]))

;; Chapter 3 Basics
;;
;; Use keywords as map keys (idiomatic) and accept keywords,
;; symbols, or strings for the `animal` argument.
(def animal-multipliers
  {:dog 7
   :cat 5
   :fish 10})

(def animals (keys animal-multipliers))

(defn human-age
  "Return human age equivalent for `animal` given numeric `age`.
  `animal` may be a keyword, symbol, or string (case-insensitive)."
  [animal age]
  (let [k (keyword (str/lower-case (name animal)))
        multiplier (get animal-multipliers k)]
    (if multiplier
      (* age multiplier)
      (throw (ex-info (str "Unknown animal: " animal) {:animal animal})))))