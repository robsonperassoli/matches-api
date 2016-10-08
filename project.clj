(defproject api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring-json-params "0.1.3"]
                 [compojure "1.5.0"]
                 [clj-json "0.5.3"]
                 [com.novemberain/monger "3.1.0"]
                 [environ "1.1.0"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-environ "1.1.0"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "matches-api-standalone.jar"
  :main ^:skip-aot api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
