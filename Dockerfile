FROM openjdk:21-jdk-slim

RUN apt update && \
    apt install maven -y

WORKDIR /app

COPY . .

RUN mvn clean install

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/taskman-1.0.0.jar"]
