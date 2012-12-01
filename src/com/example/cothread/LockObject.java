package com.example.cothread;


/**
 *
 * 此类主要用来处理线程的同步屏蔽模型，比如，一批线程运行，必须在最后一个线程运行
 * 完后，才能进行下一步的操作，那么就可以创建一个锁对象，锁对象提供一个线程等待其他线程
 * 的方法，如果当前线程运行时，还有未运行的线程，则此线程wait，否则，此线程唤醒其他阻塞的
 * 线程，进而最终完成线程的运行
 * */
public class LockObject {

    private int totalThread = 0;
    private int currentThread = 0;

    public LockObject(int totalThread) {
        this.totalThread = totalThread;
        this.currentThread = 1;
    }

    public synchronized void waitForOtherThread() {
        if (this.currentThread < this.totalThread) {
            this.currentThread++;
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            this.currentThread = 1;
            notifyAll();
        }
    }

    public int getTotalThread() {
        return totalThread;
    }

    public void setTotalThread(int totalThread) {
        this.totalThread = totalThread;
    }

    public int getCurrentThread() {
        return currentThread;
    }

    public void setCurrentThread(int currentThread) {
        this.currentThread = currentThread;
    }
}
