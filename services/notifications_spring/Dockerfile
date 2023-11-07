# FROM maven:3.8.3 AS maven
# WORKDIR /usr/src/app
# COPY . /usr/src/app

# VOLUME /tmp

# # Compile and package the application to an executable JAR
# RUN mvn package

# # For Java 17
# FROM openjdk:17-oracle

# ARG JAR_FILE=notifications.jar

# WORKDIR /opt/app

# # Copy the NotificationsApplication.jar from the maven stage to the /opt/app directory of the current stage.
# COPY --from=maven $WORKDIR/target/${JAR_FILE} /opt/app/

# ENTRYPOINT ["java", "-jar", "notifications.jar"]

FROM maven:3.8.3 AS maven
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn package

FROM openjdk:17-oracle
COPY --from=maven /usr/app/target/notifications.jar /app/notifications.jar
ENTRYPOINT java -jar /app/notifications.jar

# FROM maven:3.6.0-jdk-11-slim AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package

# #
# # Package stage
# #
# FROM openjdk:11-jre-slim
# COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/notifications.jar
# EXPOSE 5000
# ENTRYPOINT ["java","-jar","/usr/local/lib/notifications.jar"]