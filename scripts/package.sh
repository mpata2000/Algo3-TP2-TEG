# /bin/bash
set -e

mvn clean package -DskipTests -Ppackage
