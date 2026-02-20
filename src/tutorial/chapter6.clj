(ns tutorial.chapter6)

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

(def buyerAccount (ref 16.00))
(def merchantAccount (ref 0.00))
(def prices {:pen 1.00 :notepad 5.00 :backpack 10.00})
(def stock (keys prices))
(def cart (ref []))

(defn buy [item]
  (let [price (get prices item)]
    (if price
      (dosync
       (if (>= @buyerAccount price)
         (do
           (alter buyerAccount - price)
           (alter merchantAccount + price)
           (alter cart conj item)
           {:item item :price price :remaining @buyerAccount})
         {:error (str "Insufficient funds for " (name item) ": costs $" price ", have $" @buyerAccount)}))
      {:error (str "Item not found: " (name item))})))
