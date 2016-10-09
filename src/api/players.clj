(ns api.players
  (:require [monger.collection :as mc]
            [api.util :refer [identify stringfy-id stringfy-ids new-object-id is-valid-id?]]
            [api.db :refer [db]]))

(defn list []
  (-> (mc/find-maps db :players) stringfy-ids))

(defn post [player]
    (-> (mc/insert-and-return db :players (identify player)) stringfy-id))

(defn put [id player]
  (mc/update-by-id db :players (new-object-id id) player)
  (get id))

(defn get [id]
  (when (is-valid-id? id)
        (-> (mc/find-map-by-id db :players (new-object-id id)) stringfy-id)))

(defn delete [id]
  (mc/remove-by-id db :players (new-object-id id)))

(defn exists? [id]
  (not (= (get id) nil)))
