#!/usr/bin/env bash


#CONTAINER_ID=$1
#docker export "$CONTAINER_ID" > jerseyexample.tar

docker image save -o ../../../UsersManager-FE/dockerimage/jerseyexamplefe.tar frontend