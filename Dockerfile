FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} parcel.jar
ENTRYPOINT ["java","-jar","/parcel.jar"]