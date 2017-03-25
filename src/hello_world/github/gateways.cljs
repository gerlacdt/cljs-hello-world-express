(ns hello-world.github.gateways
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.core.async :as async]
            [cljs.nodejs :as nodejs]))

(def request (nodejs/require "request"))

(def github-url "https://api.github.com/users")

(defn get-user-repos
  "Returns a channel containing information about the requested
  user. "
  [username]
  (let [opts {:url (str github-url "/" username "/repos")
              :headers {"User-Agent" "request"}
              :json true}
        out (async/chan)]
    (.get request (clj->js opts)
          (fn [err response body]
            (async/put! out (js->clj body))))
    out))
