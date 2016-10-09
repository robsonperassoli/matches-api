(ns api.match-results
  (:require [monger.collection :as mc]
            [api.util :refer [identify stringfy-id stringfy-ids new-object-id]]
            [api.db :refer [db]]))

(defn list []
  (-> (mc/find-maps db :match-results) stringfy-ids))

(defn has-valid-attrs [result]
  (every? result [:home_team :away_team :home_player :away_player :home_goals :away_goals :date_time]))

(defn post [result]
  (if (has-valid-attrs result)
        (-> (mc/insert-and-return db :match-results (identify result)) stringfy-id)
        {:message "required attributes must be supplied"}))
