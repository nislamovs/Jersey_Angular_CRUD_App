#!/usr/bin/env bash

chmod a+x ./* ;
chmod a+x ../* ;

bash -x ../stop_n_remove_containers.sh ;
bash -x ./build.sh ;
bash -x ./create_image.sh
bash -x ../stop_n_remove_containers.sh ;
bash -x ./import_image.sh ;
bash -x ./run.sh ;

echo "Checking service availability : [https://localhost:8081/actuator/health] ... "
sleep 10 ;

#curl -kvvv https://localhost:8081/actuator/health 2>/dev/null ;
RESPONSE=$(curl -s -I -L -kvvv https://localhost:8081/actuator/health | egrep "HTTP/1.1" | awk -F' ' '{print $2}') #  2>/dev/null ;
if [[ "$RESPONSE" -ge 200 ]] && [[ "$RESPONSE" -lt 300 ]]; then
  echo "OK [$RESPONSE]"
else
  echo "FAIL! Service not accessible!"
  exit 1
fi