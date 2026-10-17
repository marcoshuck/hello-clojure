(ns todo.server.router
  (:require [clojure.set :as set]
            [todo.health.routes :as health]
            [todo.users.routes :as users]))

(defn- prefix-routes [prefix routes]
  (into #{} (map (fn [[path & rest-route]] (into [(str prefix path)] rest-route))) routes))

(defn- v1 [routes] (prefix-routes "/v1" routes))
(defn- v1alpha1 [routes] (prefix-routes "/v1alpha1" routes))
(defn- api [routes] (prefix-routes "/api" routes))

(defn routes
  "Returns all project routes in a union set"
  []
  (set/union
    (api (v1 (health/get-routes)))
    (api (v1alpha1 (users/get-routes))))
  )