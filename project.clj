(defproject hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.473"]
                 [org.clojure/core.async "0.2.395"
                  :exclusions [org.clojure/tools.reader]]]

  :plugins [[lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["out"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :compiler {:main hello-world.core
                           :output-to "out/server.js"
                           :output-dir "out"
                           :source-map true
                           :target :nodejs
                           :optimizations :none
                           }}]}
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   :plugins [[cider/cider-nrepl "0.15.0-SNAPSHOT"]]
                   :repl-options {:init (set! *print-length* 150)
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

)
