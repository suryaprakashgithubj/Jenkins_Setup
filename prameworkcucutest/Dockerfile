FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY . .

# Build project (skip tests during image build)
RUN mvn clean install -DskipTests

# Default test run
CMD ["mvn", "test"]