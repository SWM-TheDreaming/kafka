FROM openjdk:11

ARG JAR_FILE=./build/libs/kafka-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} kafka.jar

EXPOSE 9000

ENTRYPOINT ["java","-jar","kafka.jar"]