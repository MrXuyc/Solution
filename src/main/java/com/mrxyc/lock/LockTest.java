package com.mrxyc.lock;

public class LockTest {
    public static void main(String[] args) {
        ThreadDemo threadDemo1 = new ThreadDemo();
        threadDemo1.start();
        ThreadDemo threadDemo2 = new ThreadDemo();
        threadDemo2.start();
    }
}

class ThreadDemo extends Thread {
    private static int count;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (ThreadDemo.class) {
                System.out.println(this);
                count++;
                System.out.println(count);
            }
        }
    }
}

