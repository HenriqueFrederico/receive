FROM openjdk:11
WORKDIR /app
ARG JAR_FILE=target/receiver.jar
COPY ${JAR_FILE} /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
