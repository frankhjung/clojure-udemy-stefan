(ns tutorial.chapter5)

;; Simulate a merchant selling goods and a buyer purchasing them.
;; Buyer has a budget of $100.00
;; Merchant has 3 items for sale:
;; - pen for       $1.00
;; - notepad for   $5.00
;; - backpack for $10.00
;;
;; Build a function that will purchase multiple items until the buyer's budget
;; is exhausted. The function should return a list of items purchased and the
;; remaining budget.

(def ^:const items-for-sale
  {:pen      1.00
   :notepad  5.00
   :backpack 10.00})

(def items (keys items-for-sale))

(defn purchase-item
  "Attempt to purchase an item given the current budget.
   Returns a map with the result."
  [budget item]
  (let [price (get items-for-sale item)]
    (if price
      (if (>= budget price)
        {:item item :price price :remaining (- budget price)}
        {:error (str "Insufficient funds for " item ": costs $" price ", have $" budget)})
      {:error (str "Item not found: " item)})))

(defn purchase-items
  "Attempt to purchase multiple items given the current budget.
   Returns a map with the result."
  [budget items]
  (cond
    (nil? items) {:remaining budget :purchased [] :error "Items list cannot be nil"}
    (empty? items) {:remaining budget :purchased []}
    (<= budget 0) {:remaining budget :purchased [] :error "Budget must be greater than 0"}
    :else
    (reduce (fn [acc item]
              (let [result (purchase-item (:remaining acc) item)]
                (if (:error result)
                  (reduced (assoc acc :error (:error result) :failed-item item))
                  (assoc acc :remaining (:remaining result)
                         :purchased (conj (:purchased acc)
                                          {:item (:item result) :price (:price result)})))))
            {:remaining budget :purchased []}
            items)))

(defn list-purchased-items
  "Print a report of purchased items and remaining budget."
  [{:keys [error failed-item purchased remaining]}]
  (if error
    (do
      (println "Purchase failed:" error)
      (when failed-item (println "Failed at item:" failed-item)))
    (do
      (println "Purchased items:")
      (doseq [{:keys [item price]} purchased]
        (println (str "  - " (name item) ": $" price)))
      (println "Remaining budget: $" remaining))))