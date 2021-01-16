#!/usr/bin/env bash

set -euo pipefail

LOCALSTACK_PORT=4566
DYNAMO_DB_PORT=8000
SERVICES="apigateway lambda"

check_service() {
  service=$1
  printf ' waiting for %s...' "$service"
  while [ "$(curl -s http://localhost:4566/health | jq -r '.services | '."$service"'')" != "running" ]; do
    printf .
    sleep 2
  done
  printf 'ready\n'
}

check_localstack() {
  for service in $SERVICES; do
    check_service "$service"
  done
}

check_dynamodb() {
  printf ' waiting for %s...' "dynamodb"
  while ! aws dynamodb list-tables --endpoint-url http://localhost:$DYNAMO_DB_PORT &>/dev/null; do
    printf .
    sleep 2
  done
  printf 'ready\n'
}

destroy_container_with_ports() {
  ports="$1"
  for port in $ports; do
    docker rm -f "$(docker container ls -a --format="{{.ID}}\t{{.Ports}}" | grep "$port" | awk '{print $1}')" || true
  done
}

start_local_environment() {
  echo "starting local environment..."
  destroy_container_with_ports "$LOCALSTACK_PORT $DYNAMO_DB_PORT" &> /dev/null
  docker-compose down --remove-orphans -v &> /dev/null
  docker-compose up -d &> /dev/null
  check_localstack
  check_dynamodb
  echo "local environment started."
}

start_local_environment
