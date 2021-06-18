FROM openjdk:15-buster
RUN apt update; apt install ffmpeg maven -y
COPY . ./
RUN mvn package
ARG JAR_FILE=target/*.jar
RUN cp ${JAR_FILE} app.jar
RUN mkdir /videos
RUN mkdir /streams
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]