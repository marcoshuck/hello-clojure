(ns basic-server.health)

(defn healthz [_request]
  {:status 200 :body "OK"})

(defn get-routes []
  ["/healthz" :get healthz :route-name :healthz])

