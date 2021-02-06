FROM openjdk:11
ADD target/learning-platform.jar learning-platform.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "learning-platform.jar"]