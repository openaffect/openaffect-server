FROM openjdk:8-jdk-alpine

COPY tmp/java-server*.jar java-server.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker","java-server.jar"]
