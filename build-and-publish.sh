#!/bin/bash

# external service
docker build -t franco87/mca-cyo-externalservice ./externalservice
docker push franco87/mca-cyo-externalservice

# Server
docker build -t franco87/mca-cyo-server-p3 ./server
docker push franco87/mca-cyo-server-p3

# Worker
cd ./worker && mvn compile jib:build -Dimage=franco87/mca-cyo-worker