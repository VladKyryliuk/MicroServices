FROM eclipse-temurin:17-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/MSSmartphone.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]