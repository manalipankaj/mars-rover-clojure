(ns mars-rover.core-test
  (:require [clojure.test :refer :all]
            [mars-rover.core :refer :all]))

(deftest rover-test
  (testing "Test failed"
    (is (= (inputs [1 2 "N"] "LMLMLMLMM") [1 3 "N"]))
    (is (= (inputs [3 3 "E"] "MMRMMRMRRM") [5 1 "E"]))))
