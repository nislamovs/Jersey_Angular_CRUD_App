#!/usr/bin/env bash

curl -kvvv -XDELETE https://localhost:8080/api/users/1 2>/dev/null | jq '.'


#The same, but without image
#curl -kvvv -XGET https://localhost:8080/api/users/1 2>/dev/null | jq 'del(.[].photoImage)'