(ns tutorial.chapter3
  (:require [clojure.string :as str]))

;; Chapter 3 Basics
;;
;; Use keywords as map keys (idiomatic) and accept keywords,
;; symbols, or strings for the `animal` argument.
(def ages {:dog (partial * 7)
           :cat (partial * 5)
           :fish (partial * 10)})

(defn human-age
  "Return human age equivalent for `animal` given numeric `age`.
  `animal` may be a keyword, symbol, or string (case-insensitive)."
  [animal age]
  (let [k (keyword (str/lower-case (name animal)))
        f (get ages k)]
    (if f
      (f age)
      (throw (ex-info (str "Unknown animal: " animal) {:animal animal})))))