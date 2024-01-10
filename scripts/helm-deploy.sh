#!/usr/bin/env bash
source ./scripts/utils.sh

set -euo pipefail

: "${EKS_NAMESPACE}"
: "${RELEASE_NAME}"
VERSION=latest
printMessage "$HEART_ICON" "$HEART_ICON" "Deploying app version : ${VERSION}"
helm upgrade \
--install \
--wait \
--namespace "${EKS_NAMESPACE}" \
--set buildno="${VERSION}" \
--set authUserName="${AUTH_USERNAME}" \
"${RELEASE_NAME}" ./kubernetes/helm-chart
