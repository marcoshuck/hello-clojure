(ns todo.health.health-test
  (:require [todo.health.routes :as health]
            [clojure.test :refer :all]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.test :refer [response-for]]))

(def service (::http/service-fn (http/create-servlet {::http/routes (route/expand-routes (health/get-routes))})))

(deftest healthz-ok
  (let [resp (response-for service :get "/healthz")]
    (is (= 200 (:status resp)))
    (is (= "OK" (:body resp)))
    ))