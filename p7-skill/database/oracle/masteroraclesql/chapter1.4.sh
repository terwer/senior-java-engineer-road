#!/usr/bin/env bash
docker exec -it oracle19c /bin/sh
sqlplus  /nolog
# SQL*Plus: Release 12.2.0.1.0 Production on Sun Mar 10 14:15:02 2019
# Copyright (c) 1982, 2016, Oracle.  All rights reserved.
SQL> connect hr@orcl
Enter password:
# 123456
Connected.

