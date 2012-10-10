package com.example.thread;

public class DownloadTask implements Runnable{
    public String name;
    public DownloadTask(String name){
        this.name=name;
    }
    @Override
    public void run() {
//      String name=Thread.currentThread().getName();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " executed OK!");
    }
    public String getFileId(){
        return name;
    }

}