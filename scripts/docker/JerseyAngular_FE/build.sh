#!/usr/bin/env bash


#Build image
cd ../../../UsersManager-FE ;
docker build -t frontend ./ #> maintenanceScripts/dockerSetup/output.txt
