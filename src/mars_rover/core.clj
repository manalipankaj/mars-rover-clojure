(ns mars-rover.core
  (:gen-class))

(defn change-rover-direction [dir move]
  (let [mover ["N" "W" "S" "E" "N"]]
   (cond
    (= move \L) (nth mover (+ (.indexOf mover dir) 1))
    (= move \R) (nth mover (- (.lastIndexOf mover dir) 1)))))

(defn move-rover [pos dir]
  (cond
   (= dir "N") [(first pos) (inc (last pos)) "N"]
   (= dir "E") [(inc (first pos)) (last pos) "E"]
   (= dir "S") [(first pos) (dec (last pos)) "S"]
   (= dir "W") [(dec (first pos)) (last pos) "W"]))

(defn rover [current-pos moves]
  (loop [move moves accu nil]
    (if (> (count move) 0)
      (cond
       (= \M (first move)) (recur (drop 1 move) (reset! current-pos (move-rover [(first @current-pos) (second @current-pos)] (last @current-pos))))
       (or (= \R (first move)) (= \L (first move))) (recur (drop 1 move) (reset! current-pos [(first @current-pos) (second @current-pos) (change-rover-direction (last @current-pos) (first move))(change-rover-direction (last @current-pos) (first move))])))
      accu))
  (println @current-pos))

(defn inputs [current moves]
  (def current-pos (atom [(first current) (second current) (last current)]))
  (def move moves)
  (rover current-pos move))
