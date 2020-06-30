#!/usr/bin/env bash

set -euo pipefail

build_image() {
  echo "Start Building Docker Image..."
  TAG=$([ "${CIRCLE_BRANCH}" == "master" ] && echo "0.1.${CIRCLE_BUILD_NUM}" || echo "${CIRCLE_BRANCH}" | sed 's/dependabot\/gradle//g;s/.//')
  echo "${TAG}" >version.txt
  echo "New Docker Image Version : ${TAG}"
  echo "${DOCKER_PASS}" | docker login --username "${DOCKER_USER}" --password-stdin
  docker build -t "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}" -f ci.Dockerfile .
}
push_image() {
  TAG=$(cat version.txt)
  echo "Pushing Docker Image latest & ${TAG} ..."
  docker push "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}"
  if [ "${CIRCLE_BRANCH}" == "master" ]; then
    docker tag "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}" "${DOCKER_USER}/${DOCKER_IMAGE}:latest"
    docker push "${DOCKER_USER}/${DOCKER_IMAGE}:latest"
  fi
}
"$@"
