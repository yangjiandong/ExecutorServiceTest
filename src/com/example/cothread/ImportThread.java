package com.example.cothread;

import java.util.concurrent.CountDownLatch;

//CountDownLatch,一种主线程等待子线程完成的实现
public class ImportThread extends Thread {
    private CountDownLatch threadsSignal;

    public ImportThread(CountDownLatch threadsSignal) {
        this.threadsSignal = threadsSignal;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始...");
        //Do somethings
        threadsSignal.countDown();//线程结束时计数器减1
        System.out.println(Thread.currentThread().getName() + "结束. 还有" + threadsSignal.getCount() + " 个线程");
    }

    public static void main(String[] args) {
        int threadNum = 10;
        CountDownLatch threadSignal = new CountDownLatch(threadNum);//初始化countDown
        for (int ii = 0; ii < threadNum; ii++) {//开threadNum个线程
            //final Iterator<String> itt = it.get(ii);
            //Thread t = new ImportThread(itt,sql,threadSignal);
            Thread t = new ImportThread(threadSignal);
            t.start();
        }
        try {
            threadSignal.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//等待所有子线程执行完
        System.out.println(Thread.currentThread().getName() + "结束.");//打印结束标记

    }
}
