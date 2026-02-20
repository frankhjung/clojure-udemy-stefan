(defproject udemy-stefan "0.1.0-SNAPSHOT"
  :description "Clojure code for the Udemy course."
  :url "https://github.com/frankhjung/clojure-udemy-stefan"
  :license {:name "MIT License"
            :url "https://spdx.org/licenses/MIT.html"}
  :dependencies [[org.clojure/clojure "1.12.1"]]
  :plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]
  :test-paths ["test"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :cicd {:main ^:skip-aot tutorial.core}}
  :repl-options {:init (do (require 'tutorial.chapter3-test)
                           (require 'tutorial.chapter4-test)
                           (require 'tutorial.chapter5-test)
                           (require 'tutorial.chapter6-test))}
  :min-lein-version "2.0.0")
