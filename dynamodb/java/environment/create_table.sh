#!/usr/bin/env bash

set -euo pipefail

TABLE_NAME="articles"

delete_table() {
  table_name=$1
  aws dynamodb delete-table \
    --table-name "$table_name" \
    --endpoint-url http://localhost:8000
}

create_table() {
  table_name=$1
  aws dynamodb create-table \
    --table-name "$table_name" \
    --attribute-definitions \
    AttributeName=Name,AttributeType=S \
    AttributeName=Manufacturer,AttributeType=S \
    --key-schema \
    AttributeName=Name,KeyType=HASH \
    AttributeName=Manufacturer,KeyType=RANGE \
    --billing-mode=PAY_PER_REQUEST \
    --endpoint-url http://localhost:8000
}

start_local_dynamodb() {
  docker-compose down -v --remove-orphans
  docker-compose up -d
}

cleanup() {
  tables=$(aws dynamodb list-tables --endpoint-url http://localhost:8000 | jq -r '.TableNames[]')
  for table in $tables; do
    delete_table "$table"
  done
}

get_items() {
  table_name=$1
  aws dynamodb query \
    --table-name "$table_name" \
    --endpoint-url http://localhost:8000
}

start_local_dynamodb

cleanup
create_table $TABLE_NAME
