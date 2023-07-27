FROM openjdk:17
COPY target/GuitarApp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 7070
ENV HOST mysql
ENTRYPOINT ["java", "-jar", "app.jar"]
