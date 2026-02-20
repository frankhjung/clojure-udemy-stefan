(ns tutorial.core
  (:gen-class)
  (:require
   [clojure.string :as str]
   [tutorial.chapter2 :refer [random-age random-animal random-coupon random-budget random-items random-buyer-budget random-stock]]
   [tutorial.chapter3 :refer [human-age]]
   [tutorial.chapter4 :refer [list-affordable-cars cars]]
   [tutorial.chapter5 :refer [purchase-items list-purchased-items]]
   [tutorial.chapter6 :refer [buy cart buyerAccount merchantAccount]]))

(defn -main
  "Example of a main function in Clojure."
  [& args]
  (println "Received arguments:" (vec args))
  (let [age (random-age)
        animal (random-animal)
        coupon (random-coupon)
        budget (random-budget)
        items-to-buy (random-items)
        buyer-budget (random-buyer-budget)
        stock (random-stock)]
    (println "Human age for a" (str/lower-case (name animal))
             "of age" age "is" (human-age animal age) "in human years.")
    (list-affordable-cars cars budget coupon)
    (list-purchased-items (purchase-items buyer-budget items-to-buy))
    (println "Simulating purchases with buy function:")
    (doseq [item stock]
      (println "  - attempting to buy" (name item) "...")
      (buy item))
    (println "Cart contents:")
    (doseq [item @cart]
      (println "  - " (name item)))
    (println "Final buyer account balance:" @buyerAccount)
    (println "Final merchant account balance:" @merchantAccount)))