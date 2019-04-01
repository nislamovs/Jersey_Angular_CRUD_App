#!/usr/bin/env bash

#cat ../../target/docker/jerseyexample.tar | docker import - jerseyexample:latest

docker image load -i ../../JerseyServer_BE/build/docker/jerseyexample.tar