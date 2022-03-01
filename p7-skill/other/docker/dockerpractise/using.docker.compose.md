https://docs.docker.com/compose/overview/

Using Compose is basically a three-step process:

Define your app’s environment with a ``Dockerfile`` so it can be reproduced anywhere.

Define the services that make up your app in ``docker-compose.yml`` so they can be run together in an isolated environment.

Run ``docker-compose up`` and Compose starts and runs your entire app.

A ``docker-compose.yml`` looks like this:

````yaml
version: '3'
services:
  web:
    build: .
    ports:
    - "5000:5000"
    volumes:
    - .:/code
    - logvolume01:/var/log
    links:
    - redis
  redis:
    image: redis
volumes:
  logvolume01: {}
````

https://docs.docker.com/compose/compose-file/