# Use a base image with JDK
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file (adjust name accordingly)
COPY target/*.jar app.jar

# Expose the port (Render uses this)
EXPOSE 4000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
