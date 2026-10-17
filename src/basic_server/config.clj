(ns basic-server.config
  (:require [clojure.java.io :as io]
            [aero.core :as aero]))

(defn read-config []
  (-> "config.edn"
      (io/resource)
      (aero/read-config)))