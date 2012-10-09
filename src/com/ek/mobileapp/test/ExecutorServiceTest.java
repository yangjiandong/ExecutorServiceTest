package com.ek.mobileapp.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class ExecutorServiceTest {

    @Test
    public void testDemo() throws Exception {

        //单例线程，任意时间(同一时间)池中只能有一个线程
        ExecutorService es = Executors.newSingleThreadExecutor();

        es.execute(new Runnable() {
            @Override
            public void run() {
                System.err.println("线程启动并运行" + Thread.currentThread().getName());
            }

        });

        es.execute(new Runnable() {
            @Override
            public void run() {
                System.err.println("第二个也运行了" + Thread.currentThread().getName());
            }

        });

        //Thread.sleep(1000 * 60 * 60);
    }

    @Test
    public void testDemo3() throws Exception {
        //声明一个线程池
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            final int a = i;

            //每一次execute方法，都是向池中放入一个对象
            ex.execute(new Runnable() {
                public void run() {
                    int l = 0;
                    while (l < 10) {

                        System.err.println("测试...." + a + ">"

                        + Thread.currentThread().getName() + ","

                        + Thread.currentThread().isDaemon());

                        try {
                            Thread.sleep(2000);
                            l++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        //Thread.sleep(1000 * 60 * 60);
    }

    @Test
    public void testCall() throws Exception {
        //声明一个类，可以被调用，类似于线程，但它可以拥有返回值
        class MyCall implements Callable<String> {
            private int seq;

            public MyCall(int seq) {
                this.seq = seq;

            }

            //抛出异常并可以拥有返回值
            public String call() throws Exception {
                System.err.println("执行" + seq + "," + Thread.currentThread().getName());
                Thread.sleep(3000);
                System.err.println("Weak up " + seq);
                return "完成" + seq;//这是返回值
            }

        }

        ExecutorService es = Executors.newCachedThreadPool();//创建线程池对象
        List<Future<String>> result = new ArrayList<Future<String>>();//放结果用的集合
        for (int i = 0; i < 3; i++) {
            Future<String> f = es.submit(new MyCall(i));//线程执行完成以后可以通过引用获取返回值
            result.add(f);

        }

        for (Future<String> f : result) {
            System.err.println("返回值：" + f.get());//输出返回的值
        }

        System.err.println("完成....");

    }
}
