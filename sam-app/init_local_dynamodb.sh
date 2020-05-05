#!/bin/bash

docker run --rm  -p 8000:8000 -d amazon/dynamodb-local

sleep 5

aws dynamodb create-table --table-name EntriesTable \
--attribute-definitions AttributeName=_id,AttributeType=S \
--key-schema AttributeName=_id,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
--endpoint-url http://localhost:8000