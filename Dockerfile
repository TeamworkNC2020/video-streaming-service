FROM openjdk:8-jdk-alpine
EXPOSE 8081
RUN mkdir /videos
RUN mkdir /streams
RUN apt update; apt install ffmpeg -y
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV ffmpeg_ffmpeg_path=/usr/bin/ffmpeg
ENV ffmpeg_ffprobe_path=/usr/bin/ffprobe
ENV ffmpeg_input_directory=/videos
ENV ffmpeg_output_directory=/streams
ENTRYPOINT ["java","-jar","/app.jar"]