#!/usr/bin/env bash

set -euo pipefail

pip3 install awscli-local
docker-compose up -d
