FROM openjdk:21-slim
COPY ./target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar"]
