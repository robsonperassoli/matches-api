(ns api.players
  (:require [monger.collection :as mc]
            [api.util :refer [identify stringfy-id stringfy-ids new-object-id is-valid-id?]]
            [api.db :refer [db]]))

(def invalid-player-message {:message "Could not save the player due do invalid information supplied"})

(defn list []
  (-> (mc/find-maps db :players) stringfy-ids))

(defn get-by-name [name]
  (-> (mc/find-one-as-map db :players {:name name}) stringfy-id))

(defn is-valid? [player]
  (let [name (:name player)]
    (and
      (not (empty? name))
      (nil? (:id (get-by-name name))))))

(defn post [player]
  (if (is-valid? player)
    (-> (mc/insert-and-return db :players (identify player)) stringfy-id)
    invalid-player-message))

(defn get-by-id [id]
  (when (is-valid-id? id)
        (-> (mc/find-map-by-id db :players (new-object-id id)) stringfy-id)))

(defn put [id player]
  (if (is-valid? player)
    (do (mc/update-by-id db :players (new-object-id id) player)
        (get-by-id id))
    invalid-player-message))

(defn delete [id]
  (mc/remove-by-id db :players (new-object-id id)))

(defn exists? [id]
  (not (nil? (get-by-id id))))
