#!/bin/bash
docker run -it -v ~/.m2:/root/.m2 --net runtime_default openaffect/server-specs 
