FROM openjdk:17
EXPOSE 8080
ADD target/lulo-assessment-docker.jar lulo-assessment-docker.jar
ENTRYPOINT ["java","-jar","/lulo-assessment-docker.jar"]