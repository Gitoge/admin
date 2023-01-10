FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/admin-0.0.1-SNAPSHOT.war

WORKDIR /opt/app

COPY ${JAR_FILE} admin-0.0.1-SNAPSHOT.war

COPY entrypoint.sh entrypoint.sh

RUN chmod 755 entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]