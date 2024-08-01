FROM maven:3.9.5-amazoncorretto-21 AS build
COPY src .
RUN mvn clean package

FROM amazoncorretto:21.0.4
COPY --from=build /target/MintGestao-BackEnd-0.0.1-SNAPSHOT.jar MintGestao-BackEnd-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "MintGestao-BackEnd-0.0.1-SNAPSHOT.jar"]