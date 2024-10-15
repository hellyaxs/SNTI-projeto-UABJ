#
# Build stage
#
FROM gradle:7.5.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

LABEL org.name="spring"
#
# Package stage
#
FROM openjdk:17-jdk-slim 
COPY --from=build /home/gradle/src/build/libs/semana-tecnologia-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
