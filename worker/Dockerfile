FROM maven as builder
COPY ./pom.xml /code/
WORKDIR /code
RUN mvn verify clean --fail-never
COPY . /code/
RUN mvn package

FROM openjdk:8-jre
COPY --from=builder /code/target/*.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8081
CMD [ "java", "-jar", "worker-1.0-SNAPSHOT.jar" ]