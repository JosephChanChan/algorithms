package com.junyi.order.test;

import com.junyi.framework.util.DateFormatType;
import com.junyi.framework.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * created by Joseph
 * at 2018/1/24 15:49
 */
public class ScheduleAtFixedRateTest {

    static int count = 0;
    static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {
        System.out.println("The Test begin at "+DateUtil.format(new Date(), DateFormatType.HH_MM_SS));
        for (int i=1; i<=3; i++){
            Worker worker = new Worker("任务"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testScheduleAtFixedRate(worker);
        }
    }

    /**
     * 测试目标：
     *  搞清楚 ScheduledExecutorService.scheduleAtFixedRate() 方法
     *  多个任务执行时：
     *      1、是否第2个任务在第1个任务执行完毕后，再加上周期时间，才开始执行？
     *          理解错了。scheduleAtFixedRate 工作原理是 传入的 Runnable 会被线程池中随机的线程执行。
     *          每个任务是独立的，分开的被线程执行。
     *          initialDelay 针对第1个任务被推迟的时间，只作用于第1次。
     *          period  间隔时间是作用于每个任务内部的，假设 任务1 执行时间为5秒，period为3秒，则period是从该任务被执行起倒计时，
     *          若任务1执行完毕后，period未清0，则需等待剩余的period，否则立即执行任务1。
     *          不存在任务2需要等待任务1执行完毕才执行的情况，否则就不是并发执行了。
     *          源码中说：
     *              If any execution of this task
     *              takes longer than its period, then subsequent executions
     *              may start late, but will not concurrently execute.
 *              指的是某个任务若某次执行时间超过了priod，则这个任务的后续执行会被滞后，不存在同一个任务被并发执行！
     *
     *      2、中途有任务异常，是否后续任务皆停止？
     *          如果中间有一个线程执行任务时异常，则后续任务都不会被执行，且线程池不会停止，一直阻塞着（从本测试用例看的，未分析源码）。
     */
    private static void testScheduleAtFixedRate(Worker worker){
        scheduledExecutorService.scheduleAtFixedRate(worker,1,3, TimeUnit.SECONDS);
    }

    private static class Worker implements Runnable {

        private String name;

        public Worker(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Thread "+Thread.currentThread().getName()+" start the "+this.name+" !!! at "+ DateUtil.format(new Date(), DateFormatType.HH_MM_SS));
            //模拟工作
            try {
                if (this.name.contains("任务1")){
                    count++;
                    Thread.sleep(5000);
                }
                else {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 3){
//                throw new RuntimeException("强制异常");
            }
            System.out.println("Thread "+Thread.currentThread().getName()+" finished the "+this.name+" !!! at "+DateUtil.format(new Date(), DateFormatType.HH_MM_SS));
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
