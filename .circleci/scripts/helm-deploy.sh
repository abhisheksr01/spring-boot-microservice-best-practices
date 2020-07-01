#!/usr/bin/env bash

set -euo pipefail

: "${EKS_NAMESPACE}"
: "${RELEASE_NAME}"
VERSION=$(cat version.txt)
echo "Deploying app version : ${VERSION}"
helm upgrade \
--install \
--wait \
--namespace "${EKS_NAMESPACE}" \
--set buildno="${VERSION}" \
--set authUserName="${AUTH_USERNAME}" \
"${RELEASE_NAME}" ./kubernetes/helm-chart
