(ns basic-server.main
  (:require [basic-server.config :as config]
            [basic-server.server :as server]
            [basic-server.health :as health]))

(defn -main [& args]
  (println "Starting application...")
  (let [cfg (config/read-config)]
    (println "Server listening on port" (-> cfg :server :port))
    (server/run-server (server/create-server  #{(health/get-routes)} (-> cfg :server :port)))
    )
  )

