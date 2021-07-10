ARG VERSION=8u151

FROM openjdk:${VERSION}-jdk as BUILD

COPY . /atsumori_management
WORKDIR /atsumori_management
RUN /atsumori_management/gradlew --no-daemon shadowJar

FROM openjdk:${VERSION}-jre

COPY --from=BUILD /atsumori_management/build/libs/atsumori_management-0.0.1.jar /bin/runner/run.jar
WORKDIR /bin/runner

CMD ["java","-jar","run.jar"]