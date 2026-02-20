 (ns tutorial.chapter4)

(def cars
  "Simple car inventory."
  [{:type "BMW" :price 60000}
   {:type "FERRARI" :price 100000}
   {:type "FIAT" :price 20000}])

;; Export constants so tests can reference them. Only DISCOUNT20
;; is an active coupon in the `coupons` map; DISCOUNT10 is left
;; available as a symbol but not present in `coupons` (returns 0.0).
(def ^{:active true :rate 0.20} DISCOUNT20 "20% discount coupon" :DISCOUNT20)
(def ^{:active false :rate 0.10} DISCOUNT10 "10% discount coupon" :DISCOUNT10)

(def ^:private coupons
  "Map of active coupons to their discount rates; auto-discovered from var metadata."
  (->> (ns-interns *ns*)                     ;; get interned vars in this namespace
       vals
       (filter #(and (:active (meta %)) (:rate (meta %)))) ;; only active coupons with a rate
       (map (fn [v] [@v (:rate (meta v))])) ;; map var value (e.g. :DISCOUNT20) to rate
       (into {}) ;; create a map of coupon to discount rate
       ))

(defn discount
  "Return discount rate (1.0 - discount-rate) for a coupon. Coupons are looked up in the `coupons` map; unknown coupons return 0.0 (inactive)."
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
    (println (str "  - " (:type car) ": " (:final-price car)))))
