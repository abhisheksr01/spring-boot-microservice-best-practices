#!/usr/bin/env bash

set -euo pipefail

: "${NAMESPACE}"
: "${RELEASE_NAME}"

VERSION=$(cat version.txt)
helm upgrade \
--install \
--wait \
--namespace "${NAMESPACE}" \
--set buildno="${VERSION}" \
"${RELEASE_NAME}" ./k8s-helm
