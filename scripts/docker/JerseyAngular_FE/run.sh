#!/usr/bin/env bash

docker run -d -i -t -p 8181:80 frontend

# -d, --detach                  Run container in background and print container ID
# -i, --interactive             Keep STDIN open even if not attached
# -t, --tty                     Allocate a pseudo-TTY