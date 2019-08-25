#!/usr/bin/env bash

DEFAULTVALUE=1
ID=${1:-$DEFAULTVALUE}

#curl -kvvv -XGET https://localhost:8081/api/users/2 2>/dev/null | jq '.'


#The same, but without image
curl -kvvv -XGET https://localhost:8081/api/users/$ID 2>/dev/null | jq 'del(.photoImage)'