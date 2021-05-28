FROM openjdk:15-buster
RUN mkdir /videos
RUN mkdir /streams
RUN apt update; apt install ffmpeg -y
ENV ffmpeg_ffmpeg_path=/usr/bin/ffmpeg
ENV ffmpeg_ffprobe_path=/usr/bin/ffprobe
ENV ffmpeg_input_directory=/videos
ENV ffmpeg_output_directory=/streams
ENV JDBC_DATABASE_URL=jdbc:postgresql://ec2-3-234-85-177.compute-1.amazonaws.com:5432/d7urijajvn5pgc
ENV JDBC_DATABASE_USERNAME=ylsukkzqjtafhu
ENV JDBC_DATABASE_PASSWORD=00b8b73cc6323ff12f615e68ac2694a01adab3e720add9067317d7da13e93cef
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]