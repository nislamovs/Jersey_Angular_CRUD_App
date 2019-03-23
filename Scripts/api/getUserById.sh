#!/usr/bin/env bash

ID=2

#curl -kvvv -XGET https://localhost:8080/api/users/2 2>/dev/null | jq '.'


#The same, but without image
curl -kvvv -XGET https://localhost:8080/api/users/$ID 2>/dev/null | jq 'del(.photoImage)'