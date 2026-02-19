(ns tutorial.core
  (:gen-class)
  (:require
   [clojure.string :as str]
   [tutorial.chapter2 :refer [random-age random-animal random-coupon random-budget]]
   [tutorial.chapter3 :refer [human-age]]
   [tutorial.chapter4 :refer [list-affordable-cars cars DISCOUNT10 DISCOUNT20]]))

(defn -main
  "Example of a main function in Clojure."
  [& args]
  (println "Received arguments:" (vec args))
  (let [age (random-age)
        animal (random-animal)
        coupon (random-coupon)
        budget (random-budget)]
    (println "Human age for a" (str/lower-case (name animal)) "of age" age "is" (human-age animal age) "in human years.")
    (list-affordable-cars cars budget coupon)))