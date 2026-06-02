FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Use entrypoint for flexibility
CMD ["mvn", "test"]