# openaffect-server

This repository contains a server-side implementation of the Open Affect API, built on top of the Spring Boot framework.

## What is Open Affect API?

Software development is a human activity and the affective state of stakeholders (developers, testers, users, etc.) plays an important role. More and more, tools allow to capture and record emotions associated to software artefacts and aspects. Open Affect is a proposal to standardize this process.

With Open Affect API, servers store **measures** reported by different types of **sensors**. A **measure** captures the fact that a **trigger** has caused a **subject** to feel a certain **emotion** (e.g. *a bug report has caused John to feel angry*).

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

In the current version, there is a single end-point: `/measures`, which supports `POST` and `GET` methods. The first method is used to create a measure, the second one is used to retrieve the list of all measures stored on the server.

An example is provided in the form of a PostMan collection, available in `examples/OpenAffectAPI.postman_collection`. The example shows how to report a measure, which captures the fact that a person has expressed an emotion about a particular issue on GitHub.

## Licences

The icon for the GitHub organization is 'masks' by Creative Stall from the Noun Project