(ns tutorial.chapter2
  "This contains functions to randomly generate inputs for the tutorial exercises."
  (:require
   [tutorial.chapter3 :refer [animals]]
   [tutorial.chapter4 :refer [DISCOUNT10 DISCOUNT20]]
   [tutorial.chapter5 :refer [items]]
   [tutorial.chapter6 :refer [stock]]))

(defn random-animal []
  (rand-nth animals))

(defn random-age []
  (rand-int (inc 10)))

(defn random-coupon []
  (rand-nth [DISCOUNT10 DISCOUNT20 DISCOUNT20]))

(defn random-budget []
  (rand-nth (range 10000 100000 1000)))

(defn random-items []
  (take (inc (rand-int 10)) (repeatedly #(rand-nth items))))

(defn random-buyer-budget []
  (rand-nth (range 5 100 5)))

(defn random-stock []
  (take (inc (rand-int 10)) (repeatedly #(rand-nth stock))))
