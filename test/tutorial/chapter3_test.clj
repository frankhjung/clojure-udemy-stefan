(ns tutorial.chapter3-test
  (:require [clojure.test :refer [deftest is testing]]
            [tutorial.chapter3 :refer [human-age]]))

(deftest get-age-tests
  (testing "get-age returns correct human-age equivalents"
    (is (= 21 (human-age `Dog 3)))
    (is (= 5  (human-age `Cat 1)))
    (is (= 20 (human-age `Fish 2)))))

(deftest unknown-animal-test
  (testing "get-age throws for unknown animals"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo
                          #"Unknown animal"
                          (human-age `Horse 1)))))
