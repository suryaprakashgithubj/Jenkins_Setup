FROM selenium/standalone-chrome:latest

# Install Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . .

# Debug versions
RUN java -version && mvn -version

# Build project
RUN mvn clean install -DskipTests

CMD ["mvn", "test"]
