(ns project-euler-clj.test.core
  (:use [project-euler-clj.core :as ec] :reload)
  (:use [clojure.test]))

(deftest test-problem1
  (is (= (ec/problem1) 233168 ) "Problem 1 is wrong."))

(deftest test-problem2
  (is (= (ec/problem2) 4613732)))

(deftest test-problem3
  (is (= (ec/problem3) 6857)))

(deftest test-problem4
  (is (= (ec/problem4) 906609)))

(deftest test-problem5
  (is (= (ec/problem5) 232792560)))

(deftest test-problem6
  (is (= (ec/problem6) 25164150)))

(deftest test-problem7
  (is (= (ec/problem7) 104743)))
