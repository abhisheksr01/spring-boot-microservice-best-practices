#!/bin/bash

set -e

echo "checking ls"

pwd

echo "111"

ls 

echo "changing to source-repo"

cd source-repo 

echo "print pwd & ls"

pwd

ls

# Some projects don't have exec permission
chmod a+x gradlew

./gradlew build -x test -x checkstyleTest

echo "Copying artifacts..."
cp -rfv build/libs/* ../releases/

echo "Listing directory..."
ls -lah ../releases/
