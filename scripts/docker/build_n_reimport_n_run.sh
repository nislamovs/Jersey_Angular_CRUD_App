#!/usr/bin/env bash

chmod a+x ./* ;

./stop_n_remove_containers.sh ;
./build.sh ;
./create_image.sh
./stop_n_remove_containers.sh ;
./import_image.sh ;
./run.sh ;

echo "Checking service availability : [https://localhost:8081/actuator/health] ... "
sleep 10 ;

curl -kvvv https://localhost:8081/actuator/health 2>/dev/null ;