# openaffect-server

This repository contains a server-side implementation of the Open Affect API, built on top of the Spring Boot framework.

[![Build Status](https://travis-ci.org/openaffect/openaffect-server.svg?branch=master)](https://travis-ci.org/openaffect/openaffect-server)

## What is Open Affect API?

Software development is a human activity and the affective state of stakeholders (developers, testers, users, etc.) plays an important role. More and more, tools allow to capture and record emotions associated to software artefacts and aspects. Open Affect is a proposal to standardize this process.

With Open Affect API, servers store **measures** reported by different types of **sensors**. A **measure** captures the fact that a **trigger** has caused a **subject** to feel a certain **emotion** (e.g. *a bug report has caused John to feel angry*).

## Where do I find the API specification?

The Open Affect API has been specified with the Swagger format. The YAML file that describes the types and endpoints for the API is located in `./microservices/oa-server/src/main/resources/api-spec.yaml`.

You can edit this file with the Swagger Editor, which you can run with Docker. Type the following command and open a web brower on `http://YOURDOCKERHOST:28080`.

```
docker run -p 28080:8080 swaggerapi/swagger-editor
```

## How do I build, validate and deploy the server?

Since version 0.1.3, we provide a CI/CD pipeline for the server, directly in this repo. The pipeline is built on top of Jenkins and Docker. The following process allows you to start the CI/CD server, to build the code, to run API tests and to have a running server on your machine:

1. Start the CI/CD server
  * `cd docker-topologies/cdpipeline/`
  * `docker-compose up`
  * wait until jenkins has fully started
  * Open a web browser on [http://localhost:1080](http://localhost:1080) or [http://192.168.99.100:1080](http://192.168.99.100:1080) (depending on your Docker configuration and whether you use docker machine or not)
  * Start the **build, validate and deploy open affect server** job
  * Check the results in the jenkins UI
2. At the end of the process, you should have a running docker topology
  * Open a web browser on [http://localhost:8080/api](http://localhost:8080/api) or [http://192.168.99.100:8080/api](http://192.168.99.100:8080/api) (depending on your Docker configuration and whether you use docker machine or not)

## Legacy instructions (prior to version 0.1.3)

### How do I run the server?

1. Build the docker image
  * `cd docker-images/oa-java-server/`
  * `./build-docker-image.sh` 
2. Start the docker topology
  * `cd ../../docker-topologies/runtime/`
  * `docker-compose up`
3. Check that the server is running
  * Open a web browser on [http://localhost:8080/api](http://localhost:8080/api) or [http://192.168.99.100:8080/api](http://192.168.99.100:8080/api) (depending on your Docker configuration and whether you use docker machine or not)


### How do I send API requests to the server?

In the current version, there is a single end-point: `/measures`, which supports `POST` and `GET` methods. The first method is used to create a measure, the second one is used to retrieve the list of all measures stored on the server.

An example is provided in the form of a PostMan collection, available in `examples/OpenAffectAPI.postman_collection`. The example shows how to report a measure, which captures the fact that a person has expressed an emotion about a particular issue on GitHub.


### Is there an executable specification for the server?

Yes, since release 0.1.1. We use [Cucumber-JVM](https://cucumber.io/docs/reference/jvm) to specify the expected behaviour of the REST API implementation. We have a separate maven project for that, in the `./microservices/oa-server-specs` directory. The build process of that project also uses the Swagger API specification to generate client stubs. The features are described in Gherkin feature files in the `./microservices/oa-server-specs/src/test/resources/scenarios` directory.

### How do I run the executable specification?

1. Start the docker topology, as explained before.
2. Build the docker image that encapsulates the executable specification
  * `cd docker-images/oa-server-specs`
  * `./build-docker-image.sh`
3. Run this image and check the output on the console
  * `./run-docker-image.sh`

## Licences

The icon for the GitHub organization is 'masks' by Creative Stall from the Noun Project