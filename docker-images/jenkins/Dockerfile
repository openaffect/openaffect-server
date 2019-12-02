FROM jenkins/jenkins:2.198-alpine

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
RUN apk add --update \
	docker \
	py-pip \
  python-dev \
  gcc \
  libc-dev \
  make \
	procps \
	maven \
	nodejs \
	npm \
  openjdk7 \
  py-cffi \
  libffi-dev \
  openssl-dev \
  libgcc && \
  pip install --upgrade pip && \
  pip install docker-compose	


RUN /usr/local/bin/install-plugins.sh git matrix-auth workflow-aggregator docker-workflow credentials-binding blueocean

#
# Add initial jenkins configuration. This is how jenkins knows about our job. If we were accessing a private
# git repo, we would also setup credentials and keys via this process.
#
ADD config/jenkins_home /var/jenkins_home/


