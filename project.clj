(defproject hello-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.12.2"]
                 [aero/aero "1.1.6"]
                 [io.pedestal/pedestal.service  "0.6.1"]
                 [io.pedestal/pedestal.route "0.6.1"]
                 [io.pedestal/pedestal.jetty   "0.6.1"]
                 [org.slf4j/slf4j-simple       "2.0.9"]]
  :repl-options {:init-ns basic-server.main})