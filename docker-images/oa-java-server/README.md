# Game Dock as a micro service

## Creating and packaging the micro service
* We want to run Game Dock as a micro service, integrated with other services of the Probe Dock platform (main Probe Docker server, data stores such as Redis, Postgres, etc.).

* In other words, we want to package Game Dock in a Docker image and then run a Docker container based on this image.

* To create the Docker image, we need the Dockerfile in this directory. It is very simple. It needs to extends a base image which provides a Java runtime environment. It also needs access to the executable .jar which is produced by the `mvn package` goal run on the Game Dock `pom.xml`.

* We have created the `build-docker-image.sh` script to automate this process. In the script, we first do a `mvn package`, then grab the executable jar from the target directory and finally run `docker build -t probedock/gamedock .`. This will update the probedock/gamedock:latest image on the current machine.

* Warning: to run the script, you must be in a "Docker environment" (in other words, on Mac OS and Windows, you must have all environment variables defined so that you can use Docker machine).

* Question: what is the purpose of in the Dockerfile? If you don't do that, the startup time of the application will be much slower - measurable in minute(s) -. You will find something like that in your log:

```
gamedock_1 | 2016-03-13 06:51:18.892  INFO 1 --- [ost-startStop-1] o.a.c.util.SessionIdGeneratorBase        : Creation of SecureRandom instance for session ID generation using [SHA1PRNG] took [78,206] milliseconds.
```