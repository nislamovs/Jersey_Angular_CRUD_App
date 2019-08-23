#!/usr/bin/env bash

echo " === JerseyApp - FE === "
echo "Cleaning project from prev build..."

cd ../../UsersManager-FE
if [ "$(basename `pwd`)" == "UsersManager-FE" ]; then
    echo "JerseyApp - FE directory found ..."
else
    cd ./UsersManager-FE
    if [ "$(basename `pwd`)" == "UsersManager-FE" ]; then
        echo "JerseyApp - FE directory found ..."
    else
       echo "JerseyApp - FE directory was NOT found!"
       exit 1
    fi
fi


echo "Current directory : `pwd`"
rm -rf ./dist ;
rm -rf ./node_modules ;

echo "Building project..."
npm install ;
npm audit fix ;
#npm rebuild node-sass ;

ng build ;
