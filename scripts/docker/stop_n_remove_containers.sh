#!/usr/bin/env bash

docker stop $(docker ps -a -q)
docker rm -f $(docker ps -a -q)
docker rmi -f $(docker images -q)

#rm -rf ../../jerseyexample.tar