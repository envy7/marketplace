FROM arm64v8/openjdk:17-ea-16-jdk
MAINTAINER Noel
COPY build/libs/marketplace-0.0.1-SNAPSHOT.jar marketplace.jar
ENTRYPOINT ["java", "-jar", "/marketplace.jar"]