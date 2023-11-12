# Use an official Maven image as a build environment
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the source code and pom.xml to the container
COPY ./src ./src
COPY pom.xml ./

#COPY ../pom.xml .

# Build the JAR file
RUN mvn clean install package

# Create a new image using the OpenJDK image
FROM openjdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build image to the new image
COPY --from=build /app/target/*.war app.war

# Expose the port your application runs on
EXPOSE 9000

# Command to run your application
CMD ["java", "-jar", "app.war"]
