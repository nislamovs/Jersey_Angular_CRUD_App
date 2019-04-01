#!/usr/bin/env bash

PHOTO=`cat ../res/photo.png | base64`

CMD=$(curl -kvvv -X POST \
    -H 'Content-Type: application/json' \
    -d '{"firstname" : "zubolom", "lastname": "plombovich", "address": "Paper Street 1",
    "email": "dirjavij.zub@gmail.com", "phone": "6879343455", "photoImage": "'"$PHOTO"'"}' \
    https://localhost:8080/api/users)

echo "$CMD" 2>/dev/null | jq '.'