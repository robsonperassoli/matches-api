(ns api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :as middleware]
            [ring.util.response :refer [response]]
            [api.teams :as teams]
            [api.players :as players]
            [api.match-results :as match-results]
            [api.util :as util]))

(defroutes api-routes
  (GET "/teams" []
    (-> (teams/list) response))

  (GET "/teams/:name" [name]
    (-> (teams/get-by-name name) response))

  (GET "/players" []
    (-> (players/list) response))

  (POST "/players" {player :body}
    (-> (players/post player) response))

  (PUT "/players/:id" {player :body params :params}
    (-> (players/put (:id params) player) response))

  (DELETE "/players/:id" [id]
    (-> (players/delete id) response))

  (GET "/match-results" []
    (-> (match-results/list) response))

  (POST "/match-results" {result :body}
    (-> (match-results/post result) response)))

(def app
   (-> api-routes
       (middleware/wrap-json-body {:keywords? true})
       middleware/wrap-json-response))
