(ns infinifern.core)


(def canvas (.getElementById js/document "infinifern-canvas"))
(def ctx (.getContext canvas "2d"))

(defn f1
  "Stem. Call 1% of the time"
  [x y]
  {:x 0
   :y (* 0.16 y)})

(defn f2
  "Successively smaller leaflets. Call 85% of time"
  [x y]
  {:x (+ (* 0.85 x) (* 0.04 y))
   :y (+ (* -0.04 x) (+ 1.6 (* 0.85 y)))})

(defn f3
  "Largest left-hand leaflet. Call 7% of time"
  [x y]
  {:x (- (* 0.2 x) (* 0.26 y))
   :y (+ (* 0.23 x) (+ 1.6 (* 0.22 y)))})

(defn f4
  "Largest right-hand leaflet. Call 7% of time"
  [x y]
  {:x (+ (* -0.15 x) (* 0.28 y))
   :y (+ (* 0.26 x) (+ 0.44 (* 0.24 y)))})


(defn draw
  [init-x init-y]
  (let [rand-num (rand)
        canvas (.getElementById js/document "infinifern-canvas")
        ctx (.getContext canvas "2d")]
    (let [coords
          (cond
            (<= rand-num 0.01) (f1 init-x init-y)
            (<= rand-num 0.86) (f2 init-x init-y)
            (<= rand-num (+ 0.07 0.86)) (f3 init-x init-y)
            :else (f4 init-x init-y))]
      (.beginPath ctx)
      (set! (.-fillStyle ctx) "lightgreen")
      (.fillRect ctx
                 (+ (* 100 (:x coords)) (/ (.-width canvas) 2))
                 (- (.-height canvas) (* (:y coords) 70) )
                 1
                 1)

      (js/window.requestAnimationFrame (fn [] (draw (:x coords)
                                                   (:y coords))))
      )))


(js/window.requestAnimationFrame (fn [] (draw 0 0 )))
