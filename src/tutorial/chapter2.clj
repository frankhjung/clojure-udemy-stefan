(ns tutorial.chapter2
  (:gen-class)
  (:require
   [clojure.string :as str]
   [tutorial.chapter3 :refer [human-age]]
   [tutorial.chapter4 :refer [list-affordable-cars DISCOUNT20 cars]]))

(defn -main
  "Example of a main function in Clojure."
  [& args]
  (println "Received arguments:" (vec args))
  (let [age 3
        animal :dog]
    (println "Human age for a" (str/lower-case (name animal)) "of age" age "is" (human-age animal age) "in human years."))
  (list-affordable-cars cars 50000 DISCOUNT20))