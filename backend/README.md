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
java -jar target/*.jar
```

# Curl examples

## Get

```
curl -v localhost:8080/notes
curl -v localhost:8080/notes/1
```

## POST

```
curl -v -X POST localhost:8080/notes -H 'Content-type:application/json' -d '{"title":"Note 1.0","text":"text"}'
```

## PUT

```
curl -v -X PUT  localhost:8080/notes/1 -H 'Content-type:application/json' -d '{"id": 1,"title":"Note 1.1","text":"text"}'
```

## DELETE

```
curl -v -X DELETE  localhost:8080/notes/1
```
