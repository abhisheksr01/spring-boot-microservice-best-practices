#!/bin/bash

set -e

echo "changing to source-repo"

cd source-repo 

# Some projects don't have exec permission
chmod a+x gradlew

gradle build -x test -x checkstyleTest

echo "Copying artifacts..."
cp -rfv build/libs/* ../releases/

echo "Listing directory..."
ls -lah ../releases/
