(ns tutorial.chapter6-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [tutorial.chapter5-test :refer [error-contains? success?]]
   [tutorial.chapter6 :refer [buy buyerAccount cart merchantAccount]]))
(deftest test-buy
  (testing "Successfully purchasing multiple items within budget"
    (let [result1 (buy :pen)
          result2 (buy :notepad)
          result3 (buy :backpack)]
      (is (success? result1))
      (is (success? result2))
      (is (success? result3))
      (is (= 3 (count @cart)))
      (is (= 0.0 @buyerAccount))
      (is (= 16.0 @merchantAccount))))
  (testing "Error when insufficient funds for item"
    (let [result (buy :pen)]
      (is (not (success? result)))
      (is (error-contains? result "Insufficient funds"))
      (is (error-contains? result "pen"))))
  (testing "Error when attempting to purchase non-existent item"
    (let [result (buy :laptop)]
      (is (not (success? result)))
      (is (error-contains? result "Item not found")))))