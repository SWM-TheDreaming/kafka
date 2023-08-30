FROM openjdk:11
ARG JAR_FILE=./build/libs/*.jar
VOLUME /allso_Img
COPY ${JAR_FILE} kafka.jar
ENTRYPOINT ["java","-jar","/kafka.jar"]