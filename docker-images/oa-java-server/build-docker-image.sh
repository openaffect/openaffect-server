#!/bin/bash

mkdir tmp

mvn clean package -f ../../microservices/oa-server/pom.xml
cp ../../microservices/oa-server/target/java-server*.jar ./tmp/

docker build -no-cache -t openaffect/java-server .
