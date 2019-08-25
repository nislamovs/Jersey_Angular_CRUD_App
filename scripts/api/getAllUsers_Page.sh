#!/usr/bin/env bash

#Excepts photoImage

PAGESIZE=10
PAGENUMBER=2
ORDERBY=id
ORDERDIRECTION=asc

curl -kvvv -XGET "https://localhost:8081/api/users?PageSize="$PAGESIZE"&PageNumber="$PAGENUMBER"&OrderBy="$ORDERBY"&OrderDirection="$ORDERDIRECTION""  | jq 'del(.[].photoImage)'


