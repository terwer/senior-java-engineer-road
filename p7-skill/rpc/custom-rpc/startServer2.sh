#!/bin/sh
cd  custom-rpc-provider
echo "转到目录："
pwd
mvn spring-boot:run -Dspring-boot.run.arguments=9992