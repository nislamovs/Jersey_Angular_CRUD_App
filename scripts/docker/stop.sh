#!/usr/bin/env bash

docker stop `docker ps -a | grep jerseyexample  | awk -F\  {'print $1'}`