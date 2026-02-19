(ns tutorial.chapter2
  "This namespace contains functions to randomly generate inputs for chapters 3 and 4."
  (:require
   [tutorial.chapter3 :refer [animals]]
   [tutorial.chapter4 :refer [DISCOUNT10 DISCOUNT20]]))

(defn random-animal []
  (rand-nth animals))

(defn random-age []
  (rand-int 10))

(defn random-coupon []
  (rand-nth [DISCOUNT10 DISCOUNT20 DISCOUNT20]))

(defn random-budget []
  (rand-nth (range 10000 100000 1000)))