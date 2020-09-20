#!/usr/bin/env bash

set -euo pipefail

FOLDER=$PWD

create_environment() {
  cd environment
  ./create_table.sh
  cd "$FOLDER"
}

test_java_app() {
  ./gradlew clean build
}

create_environment
test_java_app