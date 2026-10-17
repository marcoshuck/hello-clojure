(ns todo.main
  (:require [todo.config.reader :as config-reader]
            [todo.server.runner :as runner]
            [todo.server.setup :as setup]
            [todo.server.router :as router]))

(defn -main [& args]
  (println "Starting application...")
  (let [cfg (config-reader/read-config)]
    (println "Server listening on port" (-> cfg :server :port))
    (runner/run-server (setup/create-server (router/routes) (-> cfg :server :port)))
    )
  )

