(defproject udemy-stefan "0.1.0-SNAPSHOT"
  :description "Clojure code for the Udemy course."
  :url "https://github.com/frankhjung/clojure-udemy-stefan"
  :license {:name "MIT License"
            :url "https://spdx.org/licenses/MIT.html"}
  :dependencies [[org.clojure/clojure "1.12.1"]]
  :plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]
  :test-paths ["test"]
  :main ^:skip-aot tutorial.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:aliases {"build" ["do" "check," "eftest," "run"]}
                   :plugins [[lein-cljfmt "0.6.8"]]}
             :cicd {:main ^:skip-aot tutorial.core}}
  :repl-options {:init (do (require 'tutorial.chapter3-test)
                           (require 'tutorial.chapter4-test)
                           (require 'tutorial.chapter5-test)
                           (require 'tutorial.chapter6-test))}
  :clean-targets [:target-path]
  :aliases {"build" ["do" "check," "test," "run"]}
  :min-lein-version "2.0.0")
