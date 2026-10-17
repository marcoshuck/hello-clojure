(ns todo.server.runner
  (:require [io.pedestal.http :as http]))

(defn run-server [server]
  (http/start server))