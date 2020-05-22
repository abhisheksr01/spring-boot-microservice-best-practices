#!/usr/bin/env bash

set -euo pipefail

TAG="0.1.${CIRCLE_BUILD_NUM}"
echo "${TAG}" > version.txt
pwd
ls
echo "${DOCKER_PASS}" | docker login --username "${DOCKER_USER}" --password-stdin
docker build -t "${DOCKER_USER}/${PRODUCT}:${TAG}" -f ci.Dockerfile .
docker tag "${DOCKER_USER}/${PRODUCT}:${TAG}" "${DOCKER_USER}/${PRODUCT}:latest"
docker push "${DOCKER_USER}/${PRODUCT}:${TAG}"
docker push "${DOCKER_USER}/${PRODUCT}:latest"
