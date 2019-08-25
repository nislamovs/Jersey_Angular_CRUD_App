#!/usr/bin/env bash

#Excepts photoImage

curl -kvvv -XGET https://localhost:8081/api/users/list 2>/dev/null | jq 'del(.[].photoImage)'

