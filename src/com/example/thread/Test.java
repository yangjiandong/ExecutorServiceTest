package com.example.thread;

public class Test {
    public static void main(String[] args) {
        //1.new一个线程管理队列
        DownloadTaskManager.getInstance();
        //2.new一个线程池，并启动
        DownloadTaskManagerThread downloadTaskManagerThread = new DownloadTaskManagerThread();
        new Thread(downloadTaskManagerThread).start();

        //3.请求下载
        String []items=new String[]{"向晨宇1","向晨宇2","向晨宇3","向晨宇4","向晨宇5","向晨宇6","向晨宇7","向晨宇1","向晨宇2"};

        for(int i=0;i<items.length;i++){
            DownloadTaskManager downloadTaskMananger = DownloadTaskManager
                    .getInstance();
            downloadTaskMananger.addDownloadTask(new DownloadTask(items[i]));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        downloadTaskManagerThread.setStop(true);
    }
}