(ns project-euler-clj.core
  (:require clojure.contrib.math)
  (:require clojure.string))

(defn modx= [a n x]
  "Given two positive numbers, a (the dividend) and n (the divisor)
   determines if the remainder if equal to the value given by x"
  (= (mod a n) x))


(defn modx-filter [coll]
  ""
  (filter
   #(or (modx= % 3 0)
        (modx= % 5 0)) coll ))
  
(defn problem1 []
  "Add all the natural numbers below one thousand that are multiples of 3 or 5."
  (apply + (modx-filter (range 1000))))




(defn fib-seq []
  ((fn fib [a b]  (cons a (lazy-seq (fib b (+ a b)))))
   0 1) )

(defn problem2 []
  "By considering the terms in the Fibonacci sequence whose values do
  not exceed four million, find the sum of the even-valued terms"
  (apply + (filter #(even? %) (take-while #(< % 4000000) (fib-seq)))))


(defn max-num [coll]
  "find max number in collection"
  (reduce #(max %1 %2) coll))


(defn prime? [n]
  "Determines if natrual number is prime"
  (let [s (clojure.contrib.math/sqrt n)]
    (loop [m 2]
      (cond
       (< n 2) false
       (> m s) true
       (integer? (/ n m)) false
       :else (recur (inc m))))))

(defn gcd-all [coll]
  "Get GCD all all numbers in list"
  (loop [l (seq coll) gcd (first l)]
    (if (seq l)
      (recur (rest l) (clojure.contrib.math/gcd (first l) gcd))
      gcd)))                        

;;refactor these into higher order functions;

(defn lcm-all [coll]
  "Computes the least common multiple of all numbers in the list"
  (loop [l (seq coll) lcm (first l)]
    (if (seq l)
      (recur (rest l) (clojure.contrib.math/lcm (first l) lcm))
     lcm)))
            

(defn next-prime [x]
  "Takes a number and returns the next prime number
  greater than x"
  (some #(when (prime? %) %)
        (range
         (if (odd? x)
           (+ x 2)
           (inc x))
         Double/POSITIVE_INFINITY 2)))  

(defn prime-seq []
  "Generate lazy sequece of prime numbers"
  ((fn ps [n](cons n (lazy-seq (ps (next-prime n))))) 2))

(defn prime-factors [n]
  "gets prime factors of numbers using trial division"
  (loop [factors [] val n prime 2]
    (cond
     (= val 1) factors
     (= (mod val prime) 0) (recur (cons prime factors) (/ val prime) prime)
     :else (recur factors val (next-prime prime)))))
     
     
      
(defn problem3 []
  "What is the largest prime factor of the number 600851475143 ?"
  (max-num (prime-factors 600851475143)))
    

; check for string ?

(defn palindrome? [s]
  "determine if string is a palindrome"
  (= s (apply str (reverse s))))

(defn problem4 []
  "Find the largest palindrome made from the product of two 3-digit numbers."
  (max-num
   (filter
    #(palindrome? (str %))
    (for [x (range 100 1000) y (range 100 1000)] (* x y)))))



(defn problem5 []
  "What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?"
  "IN another words. Find the LCM for all of these."
  (lcm-all (range 1 21)))


(defn sum-squares [coll]
  "Sums squares of numbers in the collection"
  (apply + (map #(* % %) coll)))

(defn square [n]
  "Square a number"
  (* n n))

(defn square-sum [coll]
  "Square the sum of the collection"
  (square (apply + coll)))


(defn problem6 []
  "Find the difference between the sum of the squares of the first
   one hundred natural numbers and the square of the sum."
  (- (square-sum (range 1 101))
     (sum-squares (range 1 101))))



(defn problem7 []
  "What is the 10001st prime number?"
  (nth (prime-seq) 10000))


(def problem8-num "73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450")

(defn filter-digits-to-vec [s]
  (into []
   (map
    #(Integer/parseInt (str %))
    (filter #(Character/isDigit %) (seq s)))))

(defn problem8 []
  "Find the greatest product of five consecutive digits in the 1000-digit number."
)


 
    
