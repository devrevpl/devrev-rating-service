#!/bin/bash
./gradlew npmInstall
npx webpack
./gradlew clean build
echo "Gradle Build Completed!"
./gradlew docker
echo "Gradle Docker Completed!"
echo "Starting Docker compose"
docker-compose up &