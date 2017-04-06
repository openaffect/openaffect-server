FROM jenkins:2.46.1-alpine


#
# Running jenkins as root (instead as jenkins) is not recommended for a regular CD server. However,
# it solves a couple of issues and enables a smooth out-of-the-box experience for this repo. One of the
# issues is that ADDing a directory to a VOLUME uses the uid/gid on the host (it does not use the USER
# value into account). This means that the standard jenkins image will cause access rights problems at
# container startup time. Another issue is that when jenkins is not run as root, then the user has to
# enter a randomly generated password the first time it connects to the UI. He also sees the setup wizard,
# which we want to avoid here since we install the plugins ourselves.
#
USER root

#
# Install docker and docker-compose. Note that on alpine, we may be behind latest releases... See bottom of
# this file to build an image with the latest version on a another linux distribution.
#
RUN apk update && apk add docker make py-pip shadow && pip install docker-compose
RUN usermod -aG users jenkins

#
# Add initial jenkins configuration. This is how jenkins knows about our job. If we were accessing a private
# git repo, we would also setup credentials and keys via this process.
#
ADD config/jenkins_home /var/jenkins_home/

#
# Install plugins that we want to use
#
RUN /usr/local/bin/install-plugins.sh nodejs workflow-aggregator pipeline-stage-view blueocean


#FROM jenkins:2.32.3
#
# Install Docker
#
#USER root
#RUN curl -fsSL https://get.docker.com/ | sh
#RUN usermod -aG docker jenkins
#RUN curl -L https://github.com/docker/compose/releases/download/1.11.2/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
#RUN chmod +x /usr/local/bin/docker-compose
#RUN usermod -aG users jenkins
#USER jenkins
