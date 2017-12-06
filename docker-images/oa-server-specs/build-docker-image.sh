#!/bin/bash

mkdir tmp
rm -fr tmp/*

cp -r ../../microservices/oa-server-specs/* ./tmp/

docker build -no-cache -t openaffect/server-specs .
