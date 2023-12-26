FROM openjdk:21-jdk

COPY ./build/libs/trains-auth-server-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
