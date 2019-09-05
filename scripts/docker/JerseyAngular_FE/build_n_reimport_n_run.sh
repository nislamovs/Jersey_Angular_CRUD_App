#!/usr/bin/env bash

chmod a+x ./* ;

bash -x ../stop_n_remove_containers.sh ;
bash -x ./build.sh ;
bash -x ./create_image.sh
bash -x ../stop_n_remove_containers.sh ;
bash -x ./import_image.sh ;
bash -x ./run.sh ;

echo "Checking service availability : [http://localhost:8181/] ... "
sleep 10 ;

RESPONSE=$(curl -s -I -L localhost:8181/ | egrep "HTTP" | awk -F' ' '{print $2}') #  2>/dev/null ;
if [[ "$RESPONSE" -ge 200 ]] && [[ "$RESPONSE" -lt 300 ]]; then
  echo "OK [$RESPONSE]"
else
  echo "FAIL! Service not accessible!"
  exit 1
fi