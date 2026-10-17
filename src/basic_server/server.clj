(ns basic-server.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.jetty :as jetty]
            [io.pedestal.http.route :as route]))

(defn create-server [routes, port]
  (http/create-server {::http/routes (route/expand-routes routes) ::http/type :jetty ::http/port port}))

(defn run-server [server]
  (http/start server))