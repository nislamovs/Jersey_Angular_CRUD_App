#!/usr/bin/env bash


#Build image
cd ../../../JerseyServer_BE ;
./gradlew clean build docker #> maintenanceScripts/dockerSetup/output.txt

#Saving docker image to file:
#docker image save -o build/docker/jerseyexample.tar jerseyexample

#Push image to repo
#mvn docker:push