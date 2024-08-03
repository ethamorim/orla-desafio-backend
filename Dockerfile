FROM gradle:8.9.0-jdk21-jammy AS run
ENV SPRING_PROFILES_ACTIVE=production

WORKDIR /usr/app
COPY . .
RUN gradle build

ARG JAR_FILE=orla-challenge-backend-0.0.1-SNAPSHOT.jar
COPY /build/libs/${JAR_FILE} /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
