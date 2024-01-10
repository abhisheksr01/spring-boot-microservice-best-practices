#!/usr/bin/env bash
set -euo pipefail
source ./scripts/utils.sh

lint_docker_file() {
  printMessage "$HEART_ICON" "$HEART_ICON" "Starting hadolinting: $1"
  hadolint $1
  printMessage "$HEART_ICON" "$HEART_ICON" "Completed hadolinting: $1"
}

build_image() {
  printMessage "$HEART_ICON" "$HEART_ICON" "Start Building Docker Image..."
  TAG=$([ "${CIRCLE_BRANCH}" == "master" ] && echo "0.1.${CIRCLE_BUILD_NUM}" || echo "${CIRCLE_BRANCH}" | sed 's/dependabot\/gradle//g;s/.//')
  echo "${TAG}" > version/docker-version.txt
  echo "${TAG}" > version/test.txt
  printMessage "$HEART_ICON" "$HEART_ICON" "New Docker Image Version : ${TAG}"
  echo "${DOCKER_PASS}" | docker login --username "${DOCKER_USER}" --password-stdin
  docker build -t "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}" -f ci.Dockerfile .
}
push_image() {
  TAG=$(cat version/docker-version.txt)
  printMessage "$HEART_ICON" "$HEART_ICON" "print value from test.tx"
  cat version/test.txt
  if [ "${CIRCLE_BRANCH}" == "master" ]; then
    printMessage "$HEART_ICON" "$HEART_ICON" "Pushing Docker Image latest & ${TAG} ..."
    docker tag "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}" "${DOCKER_USER}/${DOCKER_IMAGE}:latest"
    docker push "${DOCKER_USER}/${DOCKER_IMAGE}:${TAG}"
    docker push "${DOCKER_USER}/${DOCKER_IMAGE}:latest"
    printEmojiWrappedMessage "${GREEN}${HEART_ICON}" "Successfully pushed Docker Image '${DOCKER_USER}/${DOCKER_IMAGE}' with latest & ${TAG} tags..."
  else
    printEmojiWrappedMessage "${GREEN}${HEART_ICON}" "Skipping push docker image for non master branch."
  fi
}
"$@"
