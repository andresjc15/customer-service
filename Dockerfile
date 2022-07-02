FROM openjdk:11
VOLUME /tmp
ADD ./target/customer-service-1.0.0.jar customer-service-1.0.0.jar
ENTRYPOINT ["java","-jar","/customer-service-1.0.0.jar"]