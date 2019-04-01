FROM openjdk:8-jdk-alpine
MAINTAINER nizamiislamovs@gmail.com
VOLUME /tmp
EXPOSE 8080
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.jerseyexample.app.JerseydemoApplication"]
