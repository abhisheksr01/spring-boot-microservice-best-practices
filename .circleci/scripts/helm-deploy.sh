#!/usr/bin/env bash

set -euo pipefail

: "${EKS_NAMESPACE}"
: "${RELEASE_NAME}"
echo "show files in version dir"
ls version
echo "print content of test.txt"
cat version/test.txt
VERSION=$(cat version/docker-version.txt)
echo "Deploying app version : ${VERSION}"
helm upgrade \
--install \
--wait \
--namespace "${EKS_NAMESPACE}" \
--set buildno="${VERSION}" \
--set authUserName="${AUTH_USERNAME}" \
"${RELEASE_NAME}" ./kubernetes/helm-chart
