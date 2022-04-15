package com.terwergreen;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 */
public class App {

    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        // test1();
        test2();
    }

    private static void test1() {
        try {
            for (int i = 0; i < 1000; i++) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        incr();
                    }
                });

                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("count结果：" + count);
    }

    private static void test2() {
        try {
            final int THREAD_NUM = 1000;
            final CountDownLatch latch = new CountDownLatch(THREAD_NUM);

            for (int i = 0; i < THREAD_NUM; i++) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        incr();
                        latch.countDown();
                    }
                });

                thread.start();
            }

            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("lock count结果：" + count);
    }

    public static void incr() {
        try {
            Thread.sleep(10);
            lock.lock();
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
