# Setting IntelliJ

## Install SonarLint plugin

Under `File` -> `Settings` -> `Plugins`, search for `SonarLint` and click `Install`.

## Install Key Promoter X plugin

Under `File` -> `Settings` -> `Plugins`, search for `Key Promoter X` and click `Install`.

## Install the google coding style settings

Download google style
from https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml
to `checkstyle/codestyle/`.

Apply style. Under `File` -> `Settings` -> `Editor` -> `Code Style` -> `Java`, click on the gear
next
to Scheme. Click on `import Scheme` -> `IntelliJ IDEa code style XML` and select
the `checkstyle/codestyle/intellij-java-google-style.xml`. Finally, click on `Apply`.

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
[Spring Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#container-images.dockerfiles)  
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

# How to use

## Swagger

Access Swagger UI http://localhost:8080/swagger-ui/index.html

## Curl examples

### Get

```
curl -v localhost:8080/notes
curl -v localhost:8080/notes/1
```

### POST

```
curl -v -X POST localhost:8080/notes -H 'Content-type:application/json' -d '{"title":"Note 1.0","text":"text"}'
```

### PUT

```
curl -v -X PUT  localhost:8080/notes/1 -H 'Content-type:application/json' -d '{"id": 1,"title":"Note 1.1","text":"text"}'
```

### DELETE

```
curl -v -X DELETE  localhost:8080/notes/1
```

# Authentication

[Oauth2 by Spring](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)  
[Keycloak with Spring Boot by stackoverflow](https://stackoverflow.com/questions/74571191/use-keycloak-spring-adapter-with-spring-boot-3/74572732)
