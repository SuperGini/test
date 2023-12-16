FROM openjdk:21-jdk

COPY ./build/libs/traines-core-0.0.1.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","/app.jar"]
