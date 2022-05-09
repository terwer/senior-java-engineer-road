(ns com.terwergreen.clojore.hello
  (:import java.util.Date)
  )

;Hello World
(println "Hello, World")

;定义变量
(def d (new Date))
(println d)
;输出
(println (new Date))

;静态方法调用
;(.println (System/out) "hi")
;(System/currentTimeMillis)

;lambda
;(fn [message] (println message))
(def run_fun (fn [message] (println message)))
(run_fun "msg")


;定义main函数
(defn -main
  "I can say 'Hello World'."
  []
  (println "Hello, World!"))
