#!/bin/sh

cd specs
mvn clean test -Dio.openaffect.server.url=http://openaffect:8080/api -Djava.security.egd=file:/dev/./urandom


# The API token will expire a year after creation (April 6th, 2017). Renew via Probe Dock UI
#cat target/surefire-reports/TEST-io.openaffect.api.spec.SpecificationTest.xml | curl --data-binary @- \
#                      -H "Content-Type: application/xml" \
#                      -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiIxeHFsbCIsIm5iZiI6MTQ5MTQ3MzYyNn0.iqdDSkeyRTu3UH6FjkgyzUNGjQooLqX4atWgz63V6jcXu5TsfkQa4UaJn_fk_bYcB4Q-gNzXsMQ5gWuU4Xpthg" \
#                      -H "Probe-Dock-Project-Id: p8wv23r9huat" \
#                      -H "Probe-Dock-Project-Version: 1.0.0" \
#                      -H "Probe-Dock-Category: BDD" \
#                      https://trial.probedock.io/api/publish
