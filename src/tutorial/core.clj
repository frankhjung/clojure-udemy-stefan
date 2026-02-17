(ns tutorial.core
  (:gen-class))

(defn -main
  "Example of a main function in Clojure."
  [& args]
  (println "Hello, Clojure CLI Project!")
  (println "Arguments received:" (vec args))
  :rcf)
