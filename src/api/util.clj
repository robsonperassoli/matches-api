(ns api.util
  (:import org.bson.types.ObjectId))

(defn identify [map]
  (merge map {:_id (ObjectId.)}))

(defn stringfy-id [map]
  (-> map (assoc :id (.toString (:_id map)))
      (dissoc :_id)))

(defn stringfy-ids [vector]
  (map stringfy-id vector))

(defn new-object-id [& [id]]
  (if (empty? id)
      (ObjectId.)
      (ObjectId. id)))

(defn is-valid-id? [id]
  (try
    (new-object-id id)
    true
    (catch Exception e false)))
