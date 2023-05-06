#
# Build stage
#
##FROM maven:3.6.0-jdk-8-slim AS build
##RUN mkdir /app
##COPY src /app/src
##COPY pom.xml /app/pom.xml
##RUN mvn -DskipTests -f /app/pom.xml clean package

#
# Package stage

##FROM openjdk:8-jre-slim
##COPY --from=build ./target/fizzbuzz-enterprise-0.0.1-SNAPSHOT.jar /fizzbuzz-enterprise-0.0.1-SNAPSHOT.jar
##ENTRYPOINT ["java","-jar","./FizzBuzz.jar"]
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} fizzbuzz-enterprise-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/fizzbuzz-enterprise-0.0.1-SNAPSHOT.jar"]