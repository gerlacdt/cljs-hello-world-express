(ns hello-world.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(def express (nodejs/require "express"))

(defn hello-world []
  (clj->js {:msg "Hello World"}))

(defn say-hello! [req res]
  (.json res
         (hello-world)))

(defn add-handler
  "Return foo"
  [req res]
  (.json res (clj->js {:result (+ (int (aget req "query" "x"))
                                  (int (aget req "query" "y")))})))

(defn -main []
  (let [app (express)]
    (.get app "/" say-hello!)
    (.get app "/add" add-handler)
    (.listen app 3000 (fn []
                        (println "Server started on port 3000")))))

(set! *main-cli-fn* -main)
