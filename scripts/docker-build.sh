#!/usr/bin/env bash

set -euo pipefail

lint_docker_file() {
  echo "Starting hadolinting: $1"
  hadolint $1
  echo "Completed hadolinting: $1"
}

build_image() {
  echo "Start Building Docker Image..."
  TAG=$([ "${CIRCLE_BRANCH}" == "master" ] && echo "0.1.${CIRCLE_BUILD_NUM}" || echo "${CIRCLE_BRANCH}" | sed 's/dependabot\/gradle//g;s/.//')
  echo "${TAG}" > version/docker-version.txt
  echo "${TAG}" > version/test.txt
  echo "New Docker Image Version : ${TAG}"
  echo "${DOCKER_PASS}" | docker login --username "${DOCKER_USER}" --password-stdin
  docker build -t "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}" -f ci.Dockerfile .
}
push_image() {
  TAG=$(cat version/docker-version.txt)
  echo "print value from test.tx"
  cat version/test.txt
  if [ "${CIRCLE_BRANCH}" == "master" ]; then
    echo "Pushing Docker Image latest & ${TAG} ..."
    docker tag "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}" "${DOCKER_USER}/${DOCKER_IMAGE}:latest"
    docker push "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}"
    docker push "${DOCKER_USER}/${DOCKER_IMAGE}:latest"
  else
    echo "Skipping pushing docker image for non master branch."
  fi
}
"$@"
