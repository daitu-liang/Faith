package com.nbfox.component_me.demo.net;

import com.nbfox.component_base.utils.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue();
    //延迟队列
    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue();
    //创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(
                3,
                10,
                15,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        //线程池 执行中可能出现异常 抛出异常 重新添加到队列
                        mQueue.add(r);
                    }
                });
        mThreadPoolExecutor.execute(coreThread);
        mThreadPoolExecutor.execute(delayThread);

    }

    private static ThreadPoolManager threadPoolManager = new ThreadPoolManager();

    public static ThreadPoolManager getThreadPoolManager() {
        return threadPoolManager;
    }

    //将异步任务添加队列中
    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                mQueue.put(runnable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //创建  核心 线程  不停获取队列中的请求，并提交给线程池处理
    public Runnable coreThread = new Runnable() {
        Runnable runn = null;

        @Override
        public void run() {
            while (true) {
                try {
                    Logger.getLogger("ThreadPoolManager").i("run()", "线程池循环从任务队列中取任务");

                    runn = mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(runn);
            }
        }
    };


    //将httptask任务添加队列中
    public void addDelayTask(HttpTask httpTask) {
        if (mDelayQueue != null) {
            try {
                httpTask.setDelayTime(3000);
                mDelayQueue.offer(httpTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //创建延迟线程 不停获取延迟任务列表中 任务
    public Runnable delayThread =new Runnable() {
        @Override
        public void run() {
            HttpTask ht=null;
            while (true){
                try {
                    ht=mDelayQueue.take();
                    if(ht.getRetryCount()<3){
                        mThreadPoolExecutor.execute(ht);
                        ht.setRetryCount(ht.getRetryCount()+1);
                        Logger.getLogger("ThreadPoolManager").i("delayThread", "重试机制=" + ht.getRetryCount()+"  时间="+ht.getDelayTime());
                    }else{
                        Logger.getLogger("ThreadPoolManager").i("delayThread", "重试机制 3次执行后 结束=" + ht.getRetryCount());
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
