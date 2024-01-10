#!/usr/bin/env bash
source ./scripts/utils.sh

response=$(curl -s -X GET -H "Header:Value" ${HEALTH_ENDPOINT})
response_status=$(jq -r '.status' <<<"${response}")
if [ "${response_status}" != "UP" ]; then
  printEmojiWrappedMessage "${RED}${SKULL_ICON}" "Health Check failed, the service isn't available please analyse further."
  exit 1
else
  printEmojiWrappedMessage "${GREEN}${HEART_ICON}" "Health Check is OK :) :)"
fi
