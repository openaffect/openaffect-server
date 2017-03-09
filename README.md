# openaffect-server

This repository contains a server-side implementation of the Open Affect API, built on top of the Spring Boot framework.

## What is Open Affect API?

## Where do I find the API specification?

The Open Affect API has been specified with the Swagger format. The YAML file that describes the types and endpoints for the API is located in `./microservices/oa-server/src/main/resources/api-spec.yaml`.

You can edit this file with the Swagger Editor, which you can run with Docker. Type the following command and open a web brower on `http://YOURDOCKERHOST:28080`.

```
docker run -p 28080:8080 swaggerapi/swagger-editor
```


## How do I run the server?

1. Build the docker image
  * `cd docker-images/oa-java-server/`
  * `./build-docker-image.sh` 
2. Start the docker topology
  * `cd ../../docker-topologies/runtime/`
  * `docker-compose up`
3. Check that the server is running
  * Open a web browser on [http://localhost:8080/api](http://localhost:8080/api) or [http://192.168.99.100:8080/api](http://192.168.99.100:8080/api) (depending on your Docker configuration and whether you use docker machine or not)


## How do I send API requests to the server?

## Licences

The icon for the GitHub organization is 'masks' by Creative Stall from the Noun Project