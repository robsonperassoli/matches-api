(ns api.teams
  (:require [clojure.data.json :as json]))

(defn list []
  (json/read-str (slurp "resources/teams.json") :key-fn keyword))

(defn get-by-name [name]
  (let [filtered-teams (filter (fn [team] (= (:name team) name)) (list))
        team-found (>= (count filtered-teams) 0)]
        (when team-found (first filtered-teams))))

(defn exists? [name]
  (not (nil? (get-by-name name))))
