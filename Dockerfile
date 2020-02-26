FROM maven:latest
COPY src src
COPY pom.xml .
RUN mvn clean package

ENTRYPOINT ["java", "-jar", "/target/fifteen-1.0.jar"]
