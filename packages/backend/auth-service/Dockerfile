FROM openjdk:11
CMD ["./mvnw package && java -jar target/auth-service.jar"]
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]