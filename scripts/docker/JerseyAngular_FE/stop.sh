#!/usr/bin/env bash

docker stop `docker ps -a | grep frontend  | awk -F\  {'print $1'}`