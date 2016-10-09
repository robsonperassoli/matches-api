(ns api.match-results
  (:require [monger.collection :as mc]
            [api.util :refer [identify stringfy-id stringfy-ids new-object-id]]
            [api.db :refer [db]]
            [api.players :as players]
            [api.teams :as teams]))

(defn list []
  (-> (mc/find-maps db :match-results) stringfy-ids))

(defn has-valid-attrs [result]
  (every? result [:home_team :away_team :home_player :away_player :home_goals :away_goals :date_time]))

(defn has-valid-relations [result]
  (and (players/exists? (:home_player result))
       (players/exists? (:away_player result))
       (teams/exists? (:home_team result))
       (teams/exists? (:away_team result))))

(defn is-valid? [result]
  (and (has-valid-attrs result) (has-valid-relations result)))

(defn post [result]
  (if (is-valid? result)
        (-> (mc/insert-and-return db :match-results (identify result)) stringfy-id)
        {:message "Could not save the result due to invalid data"}))
