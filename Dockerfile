FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/jobportal-1.0.jar
ADD ${JAR_FILE} jobportal.jar
ENTRYPOINT ["java","-jar","jobportal.jar"]