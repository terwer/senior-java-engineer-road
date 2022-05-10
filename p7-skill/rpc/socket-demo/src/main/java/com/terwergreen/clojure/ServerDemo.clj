(ns com.terwergreen.clojure.ServerDemo
  (:import java.util.concurrent.Executors)
  (:import java.net.ServerSocket)
  )

;定义main函数
(defn -main
  "main"
  []
  ;创建线程池
  (def executorService (Executors/newCachedThreadPool))
  ;(println executorService)

  ;socket通信
  (def serverSocket (new ServerSocket 9999))
  (println serverSocket)

  ;启动服务器
  (println "clojore socker server is runnning...")
  ;(.println (System/out) "clojore socker server is runnning...")

  (while true
    (def socket (.accept serverSocket))

    ;Runnable线程
    (def run_fun (fn [socket] (println "message")))
    ;放入线程池
    ;(def call_run_fun (run_fun socket))
    ;TODO:lambda传参有问题
    (.execute executorService call_run_fun)

    )

  )

;(defn handle [socket] (print "Hello"))
