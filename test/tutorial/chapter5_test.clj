(ns tutorial.chapter5-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as str]
            [tutorial.chapter5 :refer [purchase-item purchase-items]]))

;; Test helper functions for clarity
(defn has-error?
  "Check if result contains an error."
  [result]
  (string? (:error result)))

(defn success?
  "Check if result does not have an error."
  [result]
  (nil? (:error result)))

(defn error-contains?
  "Check if result error message contains a substring."
  [result substring]
  (and (has-error? result)
       (str/includes? (:error result) substring)))

;; Tests for the purchase-item function
(deftest test-purchase-item
  (testing "Successfully purchasing an item with sufficient budget"
    (let [result (purchase-item 10.00 :pen)]
      (is (success? result))
      (is (= (:item result) :pen))
      (is (= (:price result) 1.00))
      (is (= (:remaining result) 9.00))))

  (testing "Successfully purchasing with exact budget remaining"
    (let [result (purchase-item 5.00 :notepad)]
      (is (success? result))
      (is (= (:item result) :notepad))
      (is (= (:price result) 5.00))
      (is (= (:remaining result) 0.00))))

  (testing "Error when insufficient funds for item"
    (let [result (purchase-item 0.50 :notepad)]
      (is (error-contains? result "Insufficient funds"))
      (is (error-contains? result "notepad"))))

  (testing "Error when attempting to purchase non-existent item"
    (let [result (purchase-item 10.00 :laptop)]
      (is (error-contains? result "Item not found")))))

;; Tests for the purchase-items function
(deftest test-purchase-items-success
  (testing "Successfully purchasing multiple items within budget"
    (let [result (purchase-items 20.00 [:pen :notepad :backpack])]
      (is (success? result))
      (is (= 3 (count (:purchased result))))
      (is (= 4.00 (:remaining result)))))

  (testing "Successfully purchasing multiple items with exact budget"
    (let [result (purchase-items 16.00 [:pen :notepad :backpack])]
      (is (success? result))
      (is (= 3 (count (:purchased result))))
      (is (= 0.00 (:remaining result)))))

  (testing "Successfully purchasing single item"
    (let [result (purchase-items 10.00 [:pen])]
      (is (success? result))
      (is (= 1 (count (:purchased result))))
      (is (= 9.00 (:remaining result)))))

  (testing "Successfully purchasing with empty items list"
    (let [result (purchase-items 100.00 [])]
      (is (success? result))
      (is (= 0 (count (:purchased result))))
      (is (= 100.00 (:remaining result))))))

(deftest test-purchase-items-failures
  (testing "Error when budget insufficient mid-purchase"
    (let [result (purchase-items 6.50 [:pen :notepad :backpack])]
      (is (error-contains? result "Insufficient funds"))
      (is (= :backpack (:failed-item result)))
      (is (= 2 (count (:purchased result))))))

  (testing "Error when items list is nil"
    (let [result (purchase-items 100.00 nil)]
      (is (error-contains? result "nil"))))

  (testing "Error when budget is zero"
    (let [result (purchase-items 0.00 [:pen])]
      (is (error-contains? result "greater than 0"))))

  (testing "Error when budget is negative"
    (let [result (purchase-items -10.00 [:pen])]
      (is (error-contains? result "greater than 0"))))

  (testing "Error when attempting to purchase non-existent item"
    (let [result (purchase-items 100.00 [:pen :invalid-item])]
      (is (error-contains? result "Item not found"))
      (is (= :invalid-item (:failed-item result)))
      (is (= 1 (count (:purchased result)))))))

(deftest test-purchase-items-details
  (testing "Purchased items include item and price information"
    (let [result (purchase-items 20.00 [:pen :notepad])
          purchased (:purchased result)]
      (is (= 2 (count purchased)))
      (is (= :pen (:item (first purchased))))
      (is (= 1.00 (:price (first purchased))))
      (is (= :notepad (:item (second purchased))))
      (is (= 5.00 (:price (second purchased)))))))

;; Tests for the result structure
(deftest test-purchase-result-structure
  (testing "Success result contains required keys"
    (let [result (purchase-items 20.00 [:pen :notepad])]
      (is (contains? result :purchased))
      (is (contains? result :remaining))
      (is (not (contains? result :error)))))

  (testing "Error result contains required error tracking keys"
    (let [result (purchase-items 1.00 [:pen :notepad])]
      (is (contains? result :error))
      (is (contains? result :failed-item))
      (is (contains? result :purchased))
      (is (contains? result :remaining))))

  (testing "Purchased item entries have item and price"
    (let [result (purchase-items 20.00 [:pen])
          item (first (:purchased result))]
      (is (contains? item :item))
      (is (contains? item :price)))))
