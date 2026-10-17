(ns todo.users.routes
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [todo.users.creator :as creator]))

(defn get-routes []
  #{["/users" :post [(body-params/body-params) http/json-body creator/create-user-handler] :route-name :create-user]})
