FROM eclipse-temurin:17

LABEL mentainer="anaiuliaenache@gmail.com"

WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar /app/user-service-docker.jar

ENTRYPOINT ["java","-jar","user-service-docker.jar"]