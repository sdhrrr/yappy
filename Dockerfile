# Build stage where i create a container called builder
# this container has the java dev kit and compiles and builds the jar file
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

# Copying only pom.xml and mvnw files first
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn/

# Making mvnw executable
RUN chmod +x mvnw

# Copying source code
COPY src ./src

# Building without tests
RUN ./mvnw clean package -DskipTests

# Run stage: this is the final container where i copy the jar file from builder container
# here i execute the jar file 
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]