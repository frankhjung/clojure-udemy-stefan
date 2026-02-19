(defproject udemy-stefan "0.1.0-SNAPSHOT"
  :description "Clojure code for the Udemy course."
  :url "https://github.com/frankhjung/clojure-udemy-stefan"
  :license {:name "MIT License"
            :url "https://spdx.org/licenses/MIT.html"}
  :dependencies [[org.clojure/clojure "1.12.1"]]
  :test-paths ["test"]
  :main ^:skip-aot tutorial.chapter2
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :cicd {}}
  :min-lein-version "2.0.0")
