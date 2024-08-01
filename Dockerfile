FROM maven:3.9.5-amazoncorretto-21 AS build
WORKDIR /src
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package

FROM amazoncorretto:21.0.4
COPY --from=build /target/MintGestao-BackEnd-0.0.1-SNAPSHOT.jar MintGestao-BackEnd-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "MintGestao-BackEnd-0.0.1-SNAPSHOT.jar"]