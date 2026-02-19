 (ns tutorial.chapter4)

;; Simple car inventory.
(def cars [{:type "BMW" :price 60000}
           {:type "FERRARI" :price 100000}
           {:type "FIAT" :price 20000}])

;; Export constants so tests can reference them. Only DISCOUNT20
;; is a valid coupon in the `coupons` map; DISCOUNT10 is left
;; available as a symbol but not present in `coupons` (returns 0.0).
(def ^{:doc "20% discount coupon"} DISCOUNT20 :DISCOUNT20)
(def ^{:doc "10% discount coupon (inactive)"} DISCOUNT10 :DISCOUNT10)

(def ^:private coupons
  {DISCOUNT20 0.20})

(defn discount
  "Return discount rate (0.0-1.0) for a coupon. Coupons are
   looked up in the `coupons` map; unknown coupons return 0.0."
  [coupon]
  (get coupons coupon 0.0))

(defn affordable-cars
  "Return a vector of cars augmented with `:final-price` (rounded)
   that are affordable given `budget` and `coupon`.
   Preserves original `:price`."
  [cars budget coupon]
  (let [discount-rate (discount coupon)]
    (->> cars
         (map (fn [car]
                (let [final (Math/round (* (:price car) (- 1.0 discount-rate)))]
                  (assoc car :final-price final))))
         (filter #(<= (:final-price %) budget))
         vec)))

(defn list-affordable-cars
  "Print a short report of affordable cars for given `budget` and `coupon`."
  [cars budget coupon]
  (println (if (pos? (discount coupon)) "Coupon is valid" "Coupon is invalid"))
  (println "Budget is" budget)
  (doseq [car (affordable-cars cars budget coupon)]
    (println (str (:type car) "\t" (:final-price car)))))