FROM maven:3.3.9-jdk-8-alpine

ADD tmp specs
ADD run-tests-and-publish-to-probedock.sh run-tests-and-publish-to-probedock.sh

ENTRYPOINT ["./run-tests-and-publish-to-probedock.sh"]