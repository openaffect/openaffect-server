FROM jenkins:2.32.3-alpine

#
# Install docker and docker-compose. Note that on alpine, we may be behind latest releases... See bottom of
# this file to build an image with the latest version on a another linux distribution.
#
USER root
RUN apk update && apk add nodejs docker make py-pip shadow && pip install docker-compose && npm install -g grunt-cli bower
RUN usermod -aG users jenkins
USER jenkins

#
# Plugins
#
RUN /usr/local/bin/install-plugins.sh nodejs workflow-aggregator pipeline-stage-view blueocean slack email-ext





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
