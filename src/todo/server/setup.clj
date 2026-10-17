(ns todo.server.setup
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defn create-server [routes, port]
  (http/create-server {::http/routes (route/expand-routes routes) ::http/type :jetty ::http/port port}))