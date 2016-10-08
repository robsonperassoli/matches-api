(ns api.db
  (:require [monger.core :as mg]
            [environ.core :refer [env]]))


(defn get-database-uri []
  (or (env :database-uri) "mongodb://127.0.0.1/matches-api"))

(defn get-db []
  (let [uri (get-database-uri)
        {:keys [conn db]} (mg/connect-via-uri uri)]
        db))

(def db (get-db))
