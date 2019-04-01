#!/usr/bin/env bash

PHOTO=`cat ./res/2photo.png | base64`

DEFAULTVALUE=1
ID=${1:-$DEFAULTVALUE}

CMD=$(curl -kvvv -X PUT \
    -H 'Content-Type: application/json' \
    -d '{"id" : "'"$ID"'", "firstname" : "zubolom", "lastname": "plombovich", "address": "Paper Street 1",
    "email": "dirddjav444ij.zub@gmail.com", "phone": "6879343455", "photoImage": "'"$PHOTO"'"}' \
    https://localhost:8080/api/users)

echo "$CMD" 2>/dev/null | jq '.'

./getUserById.sh $ID