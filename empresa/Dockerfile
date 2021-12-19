FROM openjdk:8

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/empresa

COPY ./build/libs/empresa-0.0.1-SNAPSHOT.jar spring_boot_com_postgresql.jar

SHELL ["/bin/sh" , "-c"]

EXPOSE 8080
EXPOSE 5005


CMD java ${ADDITIONAL_OPTS} -jar spring_boot_com_postgresql.jar --spring.profiles.active=${PROFILE}