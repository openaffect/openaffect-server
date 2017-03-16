FROM maven:3.3.9-jdk-8-alpine

ADD tmp specs
WORKDIR specs

ENTRYPOINT ["mvn","clean", "test", "-Dio.openaffect.server.url=http://openaffect:8080/api", "-Djava.security.egd=file:/dev/./urandom"]
