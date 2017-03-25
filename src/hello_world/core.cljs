(ns hello-world.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.async :as async]
            [hello-world.github.gateways :as github]))

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

(defn github-handler
  "Return user info"
  [req res]
  (let [username (aget req "query" "username")
        in (github/get-user-repos username)]
    (go (.json res (clj->js (async/<! in))))))

(defn -main []
  (let [app (express)]
    (.get app "/" say-hello!)
    (.get app "/add" add-handler)
    (.get app "/github" github-handler)
    (.listen app 3000 (fn []
                        (println "Server started on port 3000")))))

(set! *main-cli-fn* -main)
