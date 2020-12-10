FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} stream_app.jar
ENTRYPOINT ["java","-jar","/stream_app.jar"]