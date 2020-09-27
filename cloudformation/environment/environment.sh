#!/usr/bin/env bash

set -euo pipefail

pip3 install awscli-local
cd environment
docker-compose up -d
