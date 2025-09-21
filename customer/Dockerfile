# Use official OpenJDK 21 slim image
FROM eclipse-temurin:21-jre-jammy

# Set a working directory
WORKDIR /app

# Copy the built jar file
COPY target/customer-0.0.1-SNAPSHOT.jar app.jar

# Use a non-root user for security (Azure best practice)
RUN useradd -ms /bin/bash spring
USER spring

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
