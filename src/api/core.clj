(ns api.core
  (:use compojure.core)
  (:use ring.middleware.json-params)
  (:require [clj-json.core :as json]
            [ring.adapter.jetty :as jetty]
            [api.teams :as teams]
            [api.players :as players]
            [api.match-results :as match-results]
            [])
  (:gen-class))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes handler
  (GET "/teams" []
    (json-response (teams/list)))

  (GET "/players" []
    (json-response (players/list)))

  (POST "/players" [player]
    (json-response (players/post player)))

  (PUT "/players/:id" [id player]
    (json-response (players/put id player)))

  (DELETE "/players/:id" [id]
    (json-response (players/delete id)))

  (GET "/match-results" []
    (json-response (match-results/list)))

  (POST "/match-results" [result]
    (json-response (match-results/post result))))

(def app
   (-> handler wrap-json-params))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 8080))]
    (jetty/run-jetty app {:port port :join? false})))
