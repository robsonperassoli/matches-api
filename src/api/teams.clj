(ns api.teams
  (:require [clj-json.core :as json]))

(defn list []
  (json/parse-string (slurp "resources/teams.json")))

(defn get-by-name [name]
  (let [filtered-teams (filter (fn [team] (= (get team "name") name)) (list))
        team-found (>= (count filtered-teams) 0)]
        (when team-found (first filtered-teams))))
        
(defn exists? [name]
  (not (= (get-by-name name) nil)))
