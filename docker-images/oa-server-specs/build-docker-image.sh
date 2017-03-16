#!/bin/bash

mkdir tmp

cp -r ../../microservices/oa-server-specs/* ./tmp/

docker build -t openaffect/server-specs .
