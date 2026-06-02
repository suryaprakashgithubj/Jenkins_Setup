FROM maven:3.9.15-eclipse-temurin-17
WORKDIR /app
 
COPY . .
 
RUN mvn clean install -DskipTests
 
CMD ["mvn","-Dtest=Runner","test"]