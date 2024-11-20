FROM jenkins/jenkins:lts

USER root
RUN apt-get update && apt-get install -y openjdk-17-jdk
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

ENV JENKINS_OPTS="--httpPort=9090"

USER jenkins