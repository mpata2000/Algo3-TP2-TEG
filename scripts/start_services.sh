# /bin/bash
set -e

docker-compose -f docker/docker-compose.yaml build
docker-compose -f docker/docker-compose.yaml up -d
docker-compose -f docker/docker-compose.yaml ps -a