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
# COPY $WORKDIR/target/${JAR_FILE} /opt/app/

# ENTRYPOINT ["java", "-jar", "notifications.jar"]


FROM maven:alpine as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn package

FROM openjdk:17-oracle
COPY --from=build /usr/app/target/notifications.jar /app/notifications.jar
ENTRYPOINT java -jar /app/notifications.jar