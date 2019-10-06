FROM openjdk:12.0.1-jdk-oraclelinux7

#ENV JAVA_HOME /usr/java/openjdk-12

VOLUME /tmp

RUN mkdir /app
WORKDIR /app

ENV TARGET "salonservice-0.0.1-SNAPSHOT.jar"

ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=docker build/libs/${JAR_TARGET}"]

