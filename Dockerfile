FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/admin.war

WORKDIR /opt/app

COPY ${JAR_FILE} admin.war

COPY entrypoint.sh entrypoint.sh

RUN chmod 755 entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]