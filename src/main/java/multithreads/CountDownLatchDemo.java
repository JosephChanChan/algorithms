package multithreads;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Joseph on 2017/12/1.
 *  A B C D 4个线程。D线程等待 其它3个线程执行完毕之后再执行任务。
 *  Thread.join可以让 A B C线程在D线程之前执行，但是如果要求A B C线程同时运行呢？
 *  可以使用CountDownLatch这个数据结构。
 */
public class CountDownLatchDemo {

    static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        new Thread(new D()).start();

        new Thread(new A()).start();
        new Thread(new B()).start();
        new Thread(new C()).start();
    }

}

class A implements Runnable{

    @Override
    public void run() {
        long time = (long) (Math.random() * 10);
        time = time < 1 ? time * 10 : time;
        System.out.println("A开始运行，需要时间 = "+time);
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A运行完毕");
        CountDownLatchDemo.latch.countDown();
    }
}

class B implements Runnable{

    @Override
    public void run() {
        long time = (long) (Math.random() * 10);
        time = time < 1 ? time * 10 : time;
        System.out.println("B开始运行，需要时间 = "+time);
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("B运行完毕");
        CountDownLatchDemo.latch.countDown();
    }
}

class C implements Runnable{

    @Override
    public void run() {
        long time = (long) (Math.random() * 10);
        time = time < 1 ? time * 10 : time;
        System.out.println("C开始运行，需要时间 = "+time);
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("C运行完毕");
        CountDownLatchDemo.latch.countDown();
    }
}

class D implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("D线程等待");

            //D线程开始等待，知道latch的count为0
            CountDownLatchDemo.latch.await();

            //开工
            System.out.println("D线程开工。");
            Thread.sleep((long)Math.random() * 10);

            System.out.println("D线程完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


