#!/bin/bash

set -e

echo "changing to source-repo"

cd source-repo 

echo "perform following gradle task : ${GRADLE_TASK}"

gradle ${GRADLE_TASK}
