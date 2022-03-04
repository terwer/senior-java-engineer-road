#!/usr/bin/env bash
docker run \
--name oracle19c \
-p 1521:1521 \
-p 5500:5500 \
-p 23:23 \
-e ORACLE_SID=ORCLCDB \
-e ORACLE_PDB=ORCL \
-e ORACLE_PWD=123456 \
-e ORACLE_MEM=4000 \
-v /Users/terwer/Documents/workspace/senior-java-engineer-road/p7-skill/database/oracle/masteroraclesql/oracle-19c-docker/oradata/:/opt/oracle/oradata \
-d \
oracle/database:19.3.0-ee