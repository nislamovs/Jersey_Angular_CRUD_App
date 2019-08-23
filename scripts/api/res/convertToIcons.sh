#!/usr/bin/env bash

#Go to folder with images and launc it there

for file in *.png; do convert $file -resize 32x32! $file; done