(ns api.core
  (:use [compojure.core]
        [ring.middleware.json-params])
  (:require [clj-json.core :as json]
            [ring.adapter.jetty :as jetty]
            [api.teams :as teams]
            [api.players :as players]
            [api.match-results :as match-results]
            [api.util :as util]
            [environ.core :refer [env]])
  (:gen-class))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes handler
  (GET "/teams" []
    (-> (teams/list) json-response))

  (GET "/teams/:name" [name]
    (-> (teams/get-by-name name) json-response))

  (GET "/players" []
    (-> (players/list) json-response))

  (POST "/players" [player]
    (-> (players/post player) json-response))

  (PUT "/players/:id" [id player]
    (-> (players/put id player) json-response))

  (DELETE "/players/:id" [id]
    (-> (players/delete id) json-response))

  (GET "/match-results" []
    (-> (match-results/list) json-response))

  (POST "/match-results" [result]
    (-> (match-results/post result) json-response)))

(def app
   (-> handler wrap-json-params))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 8080))]
    (jetty/run-jetty app {:port port :join? false})))
