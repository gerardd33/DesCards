FROM maven:3.6-jdk-11 AS base
WORKDIR /usr/src/app/generator

FROM base AS dev
CMD ["mvn", "spring-boot:run"]

FROM base AS prod
COPY ./pom.xml ./
RUN ["mvn", "dependency:resolve", "-Dclassifier=sources"]
COPY  ./mvnw ./mvnw.cmd ./
COPY ./src ./src/
ENTRYPOINT ["mvn", "spring-boot:run"]
