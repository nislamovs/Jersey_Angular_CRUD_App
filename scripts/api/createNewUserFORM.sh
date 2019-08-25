#!/usr/bin/env bash

CMD=$(curl -kvvv -X POST \
 -F "firstname=Vjacheslav" \
 -F "lastname=Kosigin" \
 -F "email=vjacheslav.kosigin@yahoo.com" \
 -F "address=Kilimanjaro street 13" \
 -F "phone=344556856" \
 -F "birthdate=1966-05-07" \
 -F "description=Proud geek and sex terrorist" \
 -F "skills=Smoking weed under water" \
 -F "experience=5 years" \
 -F "image=@./res/photo.png" \
 'https://localhost:8081/api/users')

echo "$CMD" 2>/dev/null | jq '.'