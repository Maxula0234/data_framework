FROM openjdk:11
COPY target/mmedia-0.0.1-SNAPSHOT.jar /data.jar
CMD ["java","-jar","/data.jar"]

