#!/usr/bin/env bash

set -euo pipefail

TAG="0.1.${CIRCLE_BUILD_NUM}"
echo "${TAG}" >version.txt
echo "New Docker Image Version : ${TAG}"
echo "${DOCKER_PASS}" | docker login --username "${DOCKER_USER}" --password-stdin
docker build -t "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}" -f ci.Dockerfile .
docker tag "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}" "${DOCKER_USER}/${DOCKER_IMAGE}:latest"
docker push "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}"
docker push "${DOCKER_USER}/${DOCKER_IMAGE}:latest"
