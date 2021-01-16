#!/usr/bin/env bash

set -euo pipefail

lambdas=$(find . -type d -maxdepth 1 -mindepth 1)

build_lambda() {
  lambda=$1
  cd "$lambda"
  GOOS=linux GOARCH=amd64 go build main.go | zip test.zip main
  cd ..
}

for lambda in $lambdas; do
  build_lambda "$lambda"
done
