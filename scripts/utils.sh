#!/usr/bin/env bash
# set -e

# CONSTANTS
# https://stackoverflow.com/questions/5947742/how-to-change-the-output-color-of-echo-in-linux
RED='\033[0;31m'
GREEN='\033[0;32m'
WHITE='\033[1;37m'
NOCOLOUR="\033[0m"
HEART_ICON="\xE2\x99\xA0"
SKULL_ICON="\xE2\x98\xA0"

# Prints a formatted message
# Input Format::::
# printMessage "$HEART_ICON" "$COUNTER" "Skipping Deletion of Resource Group: '$resource_group' from DO NOT DELETE list\n"
# Output Message::
# ♠♠♠ 1 ♠♠♠ - Skipping Deletion of Resource Group: 'VisualStudioOnline-123123214' from DO NOT DELETE list
printMessage() {
  printf "$1$1$1 $2 $1$1$1 - $3\n"
}

# prints a given parameter or emoji asci iteratively
# ☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠
# Input Format::::
# printEmojiWrappedMessage "${RED}${SKULL_ICON}" "Listing down all the resources AWS Nuke would nuke on '$account' account:"
# Output Message::
# ☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠
emojiFunction() {
  printf "\033[92m"
  for i in {1..150}; do
    printf "$1"
  done
  printf '\n'
}

# prints a message by iterating over a given emoji and message body
# Input Format::::
# printEmojiWrappedMessage "${RED}${SKULL_ICON}" "Listing down all the resources AWS Nuke would nuke on '$account' account: \nNote: exeucution may take a few minutes please wait."
# Output Message::
# ☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠
# Listing down all the resources AWS Nuke would nuke on 'ak-training' account:
# Note: execution may take a few minutes please wait.
# ☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠☠
printEmojiWrappedMessage() {
  for i in {1..150}; do
    printf "$1"
  done
  printf "\n$2\n"
  for i in {1..150}; do
    printf "$1"
  done
  printf '\n\n'
}
