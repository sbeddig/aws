#!/usr/bin/env bash

set -euo pipefail

BUILD_DIR=$PWD/build
mkdir -p "$BUILD_DIR"

cd ./lambdas
lambdas=$(ls)

build() {
  lambda=$1
  out="$lambda".zip
  GOOS=linux GOARCH=amd64 go build main.go
  zip "$out" main
  rm main
  mv "$out" "$BUILD_DIR"/"$out"
  cd ..
}

for lambda in $lambdas; do
  cd "$lambda"
  build "$lambda"
  cd ..
done
