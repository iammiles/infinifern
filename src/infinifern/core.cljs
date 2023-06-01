(ns infinifern.core)

(+ 1 2 3)

(js/console.log "bar")


(def canvas (.getElementById js/document "infinifern-canvas"))
(def ctx (.getContext canvas "2d"))


(set! (.-fillStyle ctx) "green")
(.fillRect ctx 10 10 150 100)
