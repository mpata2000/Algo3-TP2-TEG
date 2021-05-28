docker-compose -f docker/docker-compose.yaml up -d --build
docker-compose -f docker/docker-compose.yaml exec app bash
docker-compose -f docker/docker-compose.yaml down
