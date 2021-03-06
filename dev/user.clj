(ns user
  (require [cljs.repl.node]
           [cemerick.piggieback]))

(defn start-cljs
  "Starts cljs nrepl."
  []
  (cemerick.piggieback/cljs-repl (cljs.repl.node/repl-env)))

(defn stop-cljs
  "Stops cljs repl (only usable in cljs)"
  []
  :cljs/quit)
