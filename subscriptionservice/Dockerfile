FROM openjdk:8-jdk-alpine

ADD build/libs/*.jar /app.jar

CMD echo "PrivateService Docker image starts..."

ENTRYPOINT ["java", "-Dspring.profiles.active=pro","-jar","app.jar"]
