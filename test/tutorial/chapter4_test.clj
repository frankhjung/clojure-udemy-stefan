(ns tutorial.chapter4-test
  (:require [clojure.test :refer [deftest is testing]]
            [tutorial.chapter4 :refer [cars affordable-cars, DISCOUNT10, DISCOUNT20]]))

(deftest valid-coupon-test
  (testing "valid coupon returns correct cars"
    (let [budget 50000
          coupon DISCOUNT20
          affordable (affordable-cars cars budget coupon)]
      (is (= 2 (count affordable)))
      (is (some #(= "BMW" (:type %)) affordable))
      (is (some #(= "FIAT" (:type %)) affordable))))

  (testing "valid coupon with strict budget (avoid double discount bug)"
    (let [budget 45000
          coupon DISCOUNT20
          affordable (affordable-cars cars budget coupon)]
      (is (= 1 (count affordable))) ;; Only FIAT (20000 * 0.8 = 16000) should be affordable. BMW (60000 * 0.8 = 48000) is > 45000.
      (is (some #(= "FIAT" (:type %)) affordable))
      (is (not (some #(= "BMW" (:type %)) affordable))))))

(deftest invalid-coupon-test
  (testing "invalid coupon returns correct cars"
    (let [budget 50000
          coupon DISCOUNT10 ;; Invalid coupon, no discount applied
          affordable (affordable-cars cars budget coupon)]
      (is (= 1 (count affordable)))
      (is (some #(= "FIAT" (:type %)) affordable)))))

(deftest coupons-discovery-test
  (testing "`coupons` map is auto-built from vars with `:active true` meta"
    (let [coupons (-> (ns-interns 'tutorial.chapter4) (get 'coupons) deref)]
      (is (contains? coupons DISCOUNT20))
      (is (not (contains? coupons DISCOUNT10)))
      (is (= 0.20 (get coupons DISCOUNT20))))))
