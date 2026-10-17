(ns todo.users.creator
  (:require [cheshire.generate :as json-gen]
            [malli.core :as m]
            [malli.error :as me]
            [todo.users.user :as user])
  (:import (java.time Instant)))

(json-gen/add-encoder Instant
                      (fn [instant gen] (.writeString gen (str instant))))

(def CreateUserRequest
  [:map {:closed true}
   [:first_name [:string {:min 1}]]
   [:last_name [:string {:min 1}]]
   [:email [:re {:error/message "Invalid email format"} #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"]]])

(def CreateUserResponse
  [:map {:closed true}
   [:user user/User]])

(defn- validate-request
  "Validates request against the schema.
   Returns nil if valid, or a map of field errors if invalid."
  [request]
  (when-not (m/validate CreateUserRequest request)
    (-> (m/explain CreateUserRequest request)
        me/humanize)))

(defn- parse-user
  "Converts the CreateUserRequest keys into their User equivalents."
  [request]
  {:user/first_name (:first_name request)
   :user/last_name  (:last_name request)
   :user/email      (:email request)})

(defn- persist-user
  "Persists the User."
  [user]
  user)

(defn- create-user
  "Creates a User from the incoming CreateUserRequest."
  [request]
  (let [now (Instant/now)]
    (-> (parse-user request)
        (assoc :user/id          (random-uuid)
               :user/create_time now
               :user/update_time now
               :user/delete_time nil)
        persist-user)))

(defn- create-user-response
  "Wraps the created User in a CreateUserResponse."
  [user]
  {:user user})

(defn create-user-handler
  "Create user endpoint"
  [request]
  (if-let [errors (validate-request (:json-params request))]
    {:status 400 :body errors}
    {:status 201 :body (create-user-response (create-user (:json-params request)))}))
