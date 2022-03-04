https://github.com/steveswinsburg/oracle19c-docker

# Config
1.edit `OracleDatabase/SingleInstance/dockerfiles/19.3.0/dbca.rsp.tmpl` , and change `totalMemory=2048` to `totalMemory=4000` or whatever value you want.

2.In Docker Desktop, update the allocated memory to a value more than the value above.

# Building

```
./buildContainerImage.sh -v 19.3.0 -e
```

# Run

```
docker run \
--name oracle19c \
-p 1521:1521 -p 5500:5500 \
-e ORACLE_PDB=orcl \
-e ORACLE_PWD=123456 \
-e ORACLE_MEM=4000 \
-v /Users/terwer/Documents/workspace/senior-java-engineer-road/p7-skill/database/oracle/masteroraclesql/oracle-19c-docker/oradata/:/opt/oracle/oradata \
-d \
oracle/database:19.3.0-ee
```

# Getting a shell on the container
First run docker ps to get the container ID. Then run: `docker exec -it <container id> /bin/bash`

Or as root: `docker exec -u 0 -it <container id> /bin/bash`
