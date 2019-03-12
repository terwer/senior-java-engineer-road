#!/usr/bin/env bash
ssh docker@terwergreen.com
docker@terwergreen.com's password:
Last login: Tue Mar 12 11:18:02 2019 from 183.14.135.13

Welcome to Alibaba Cloud Elastic Compute Service !

[docker@terwergreen ~]$ docker run -it --rm \
>     ubuntu:18.04 \
>     bash
#Unable to find image 'ubuntu:18.04' locally
# 18.04: Pulling from library/ubuntu
# 898c46f3b1a1: Pull complete
# 63366dfa0a50: Pull complete
# 041d4cd74a92: Pull complete
# 6e1bee0f8701: Pull complete
# Digest: sha256:6411f872357739ef0f5f5db6a0260ae9a1f688d49bb6d859a57a4de34c0c81e5
# Status: Downloaded newer image for ubuntu:18.04
root@b76925157468:/# cat /etc/os-release
# NAME="Ubuntu"
# VERSION="18.04.2 LTS (Bionic Beaver)"
# ID=ubuntu
# ID_LIKE=debian
# PRETTY_NAME="Ubuntu 18.04.2 LTS"
# VERSION_ID="18.04"
# HOME_URL="https://www.ubuntu.com/"
# SUPPORT_URL="https://help.ubuntu.com/"
# BUG_REPORT_URL="https://bugs.launchpad.net/ubuntu/"
# PRIVACY_POLICY_URL="https://www.ubuntu.com/legal/terms-and-policies/privacy-policy"
# VERSION_CODENAME=bionic
# UBUNTU_CODENAME=bionic
root@b76925157468:/# exit
exit