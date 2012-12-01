package com.example.cothread;

public class MyThread extends Thread {
    public static LockObject lo = new LockObject(1000);

    public MyThread(String threadName) {
        super(threadName);
    }

    public void run() {
            System.out.println(Thread.currentThread().getName() + " ----开始运行");
            lo.waitForOtherThread();
            System.out.println(Thread.currentThread().getName() + " ----结束运行");
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            Thread thread = new MyThread("第" + i + "个线程");
            thread.setPriority(NORM_PRIORITY);
            thread.start();
        }
    }

}
