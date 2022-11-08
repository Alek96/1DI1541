# Setting IntelliJ

## Install SonarLint plugin

Under `File` -> `Settings` -> `Plugins`, search for SonarLint and slick `Install`.

## Install the google coding style settings

Under `File` -> `Settings` -> `Editor` -> `Code Style` -> `Java`, click on the little gear next
to Scheme. Click on `import Scheme` -> `IntelliJ IDEa code style XML` and select
the `checkstyle/codestyle/intellij-java-google-style.xml`. Finish it off by clicking `Apply`.

# Run Manually

## Building

```
./mvnw package
```

## Running

```
java -jar -Dspring.profiles.active=dev target/*.jar
```

# Docker

[Tutorial by Spring](https://spring.io/guides/topicals/spring-boot-docker/)  
[Tutorial by Baeldung](https://www.baeldung.com/spring-boot-docker-images)  
[Tutorial by Docker blog](https://www.docker.com/blog/kickstart-your-spring-boot-application-development/)

## Build by build plugin

```
./mvnw spring-boot:build-image
```

## Build by dockerfile

```
docker build -t backend:latest .
```

## Running

```
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=local" backend
```