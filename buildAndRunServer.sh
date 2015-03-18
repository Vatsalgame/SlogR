#!/bin/sh
mvn clean
mvn package
# Change the target/SlogR-X.0-SNAPSHOT.jar to match the build version
java -jar target/SlogR-1.0-SNAPSHOT.jar server target/classes/conf/settings.yaml