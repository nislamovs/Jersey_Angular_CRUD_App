#!/usr/bin/env bash


#CONTAINER_ID=$1
#docker export "$CONTAINER_ID" > jerseyexample.tar

docker image save -o ../../JerseyServer_BE/build/docker/jerseyexample.tar jerseyexample