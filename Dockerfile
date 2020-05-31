FROM openjdk:8-jdk-alpine
COPY target/provider-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 50609

ENV application-name=ms-provider

ENTRYPOINT ["java","-jar","/app.jar","--spring.application.name=$application-name"]
