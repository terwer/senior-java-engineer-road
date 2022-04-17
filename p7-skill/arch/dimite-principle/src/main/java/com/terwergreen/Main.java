package com.terwergreen;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Agent agent = new Agent();
        agent.setStar(new Star("小马"));
        agent.setFans(new Fans("粉丝小李"));
        agent.setCompany(new Company("中国传媒有限公司"));
        agent.meeting();
        agent.business();

        ReentrantLock

    }
}
