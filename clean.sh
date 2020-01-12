#!/usr/bin/env bash
rm -r build
echo "Removed build directory"
rm -r node_modules
echo "Removed node_modules directory"
rm -r out
echo "Removed out directory"
rm -r src/main/resources/static/built
echo "Removed built directory"
rm -r data
echo "Removed data directory"
docker rm mongo-db
echo "Removed mongo-db container"
docker rm movies-app
echo "Removed movies-app container"

