(ns tutorial.chapter3)

;; Chapter 3 Basics
;;
;; Create a function that returns the humand age equivalent for an animal.
;; Where:
;; Dog => 7 x age = human age equivalent
;; Cat => 5 x age = human age equivalent
;; Fish => 10 x age = human age equivalent

;; Define a map that contains the animal type and the age multiplier (unqualified symbols are used as keys).

;; Age multipliers are defined as partial functions that return the human age equivalent given the animal's age.
(def ages {'Dog (partial * 7)
           'Cat (partial * 5)
           'Fish (partial * 10)})

(defn human-age
  "This function takes an animal and its age and returns the human age equivalent. It throws an exception if the animal is unknown."
  [animal age]
  (let [key (symbol (name animal))
        f (clojure.core/get ages key)]
    (if (fn? f)
      (f age)
      (throw (ex-info (str "Unknown animal: " animal) {:animal animal})))))