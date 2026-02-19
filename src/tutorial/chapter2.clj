(ns tutorial.chapter2
  (:gen-class)
  (:require
   [tutorial.chapter3 :refer [human-age]]))

(defn -main
  "Example of a main function in Clojure."
  [& args]
  (println "Hello, Clojure CLI Project!")
  (println "Arguments received:" (vec args))
  (let [age 3
        animal `Dog]
    (println "Human age for a" animal "of age" age "is" (human-age animal age) "in human years.")))