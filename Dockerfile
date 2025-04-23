# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-slim
# Set the working directory inside the container
WORKDIR /app
# Metadata (recommended instead of deprecated MAINTAINER)
LABEL maintainer="Santosh Limbale <santoshlimbale76@gmail.com>"
# Copy the jar file from the target directory into the image
COPY target/employee-service-0.0.1-SNAPSHOT.jar app.jar
# Expose the port your Spring Boot app runs on
EXPOSE 9091
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
