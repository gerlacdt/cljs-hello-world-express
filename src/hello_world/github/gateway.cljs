(ns hello-world.github.gateways
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [kvlt.chan :as kvlt]
            [cljs.core.async :as async]))


(defn get-user
  "Returns a channel containing information about the requested
  user. "
  [username]
  (go (let [(async/<!
             (kvlt/request! {:url (str "https://api.github.com/user/" username)}))]
        (prn (:status response))
        (prn (:headers response))
        (prn (:body response))
        response)))
