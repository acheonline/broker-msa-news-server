FROM amazoncorretto:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} broker-msa-news-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/broker-msa-news-server-0.0.1-SNAPSHOT.jar"]