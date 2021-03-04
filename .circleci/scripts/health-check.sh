#!/usr/bin/env bash
emojiFunction() {
  for i in {1..68}; do
    printf "$1"
  done
  printf '\n'
}
response=$(curl -s -X GET -H "Header:Value" ${HEALTH_ENDPOINT})
response_status=$(jq -r '.status' <<<"${response}")
if [ "${response_status}" != "UP" ]; then
  printf "\033[91m\033[1m\n"
  emojiFunction "\xE2\x98\xA0"
  echo "Health Check failed service is not available please analyse further."
  emojiFunction "\xE2\x98\xA0"
  exit 1
else
  printf "\033[92m"
  emojiFunction "\xE2\x99\xA0"
  echo "Health Check is OK :) :)"
  emojiFunction "\xE2\x99\xA0"
fi
