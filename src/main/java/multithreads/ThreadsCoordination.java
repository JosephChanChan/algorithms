package multithreads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Question Description:
 *  3个线程交替打印斐波那契数列前一百项。
 *  t1-> f(1)
 *  t2-> f(2)
 *  t3-> f(3)
 *  t1-> f(4)
 *  ...
 *
 * @author Joseph
 * @since 2021-05-18 09:37
 */
public class ThreadsCoordination {

    // f(n)=f(n-1)+f(n-2)
    long[] f = new long[101];
    AtomicInteger count = new AtomicInteger(1);
    Semaphore T0 = new Semaphore(1);
    Semaphore T1 = new Semaphore(0);
    Semaphore T2 = new Semaphore(0);

    public static void main(String[] args) {
        ThreadsCoordination m = new ThreadsCoordination();
        m.print();
    }

    void print() {
        Thread[] g = {
                new Thread(new Job(0), "t0"),
                new Thread(new Job(1), "t1"),
                new Thread(new Job(2), "t2")
        };
        g[0].start();
        g[1].start();
        g[2].start();

        for (int i = 0; i < g.length; i++) {
            try {
                g[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("finished!");
    }

    class Job implements Runnable {
        int i ;
        public Job(int i) {
            this.i = i;
        }
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            // 还需要执行
            while (count.intValue() <= 100) {
                try {
                    match(i);
                    if (count.intValue() > 100) break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (count.intValue() == 1) f[count.intValue()] = 1;
                if (count.intValue() == 2) f[count.intValue()] = 1;
                if (count.intValue() > 2) f[count.intValue()] = f[count.intValue()-1]+f[count.intValue()-2];
                System.out.println(name+" print "+f[count.intValue()]);
                count.incrementAndGet();

                ThreadsCoordination.this.notify(i);
            }
            ThreadsCoordination.this.notify(i);
        }
    }

    void match(int i) throws InterruptedException {
        if (i == 0) {
            T0.acquire();
            System.out.println();
        }
        if (i == 1) T1.acquire();
        if (i == 2) T2.acquire();
    }

    void notify(int i) {
        if (i == 0) T1.release();
        if (i == 1) T2.release();
        if (i == 2) T0.release();
    }





















}
