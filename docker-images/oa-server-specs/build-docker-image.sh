#!/bin/bash

mkdir tmp
rm -fr tmp/*

cp -r ../../microservices/oa-server-specs/* ./tmp/

docker build -t openaffect/server-specs .
