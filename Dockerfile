# Group Members:
# Pranav Manish Reddi Madduri - G01504276
# Lavanya Jillela - G01449670
# Sneha Rathi - G01449688
# Chennu Naga Venkata Sai - G01514409

# Use a lightweight OpenJDK image
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/FEEDBACK-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
