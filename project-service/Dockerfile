FROM eclipse-temurin:17

LABEL mentainer="anaiuliaenache@gmail.com"

WORKDIR /app

COPY target/project-service-0.0.1-SNAPSHOT.jar /app/project-service-docker.jar

ENTRYPOINT ["java","-jar","project-service-docker.jar"]