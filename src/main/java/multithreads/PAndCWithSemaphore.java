package multithreads;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者&消费者模式
 *
 * 用信号量控制生产和消费线程数，生产线程往队列写数据，消费线程从队列取数据。
 *
 * @author Joseph
 * @since 2021-05-18
 */
public class PAndCWithSemaphore {

    volatile boolean soldOut = false;
    AtomicInteger count = new AtomicInteger(0);
    Semaphore goods = new Semaphore(3);
    Semaphore takes = new Semaphore(3);
    final Deque<Integer> que = new ArrayDeque<>(10);

    public static void main(String[] args) {
        PAndCWithSemaphore m = new PAndCWithSemaphore();
        m.run();
    }

    void run() {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        for (int i = 0; i < 10; i++) {
            new Thread(producer::make, "Producer"+i).start();
            new Thread(consumer::take, "Consumer"+i).start();
        }
        while (count.get() < 100) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done!");
    }

    class Producer {
        public void make() {
            // 总共生产100件商品
            while (count.get() < 100) {
                try {
                    // 最多允许3个线程并发生产
                    goods.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (que) {
                    // 最多允许10件商品库存
                    if (count.get() < 100 && que.size() < 10) {
                        que.addLast(count.get());
                        count.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()+" 生产了["+que.getLast()+"号商品]!");
                    }
                }
                goods.release();
            }
            soldOut = true;
            System.out.println(Thread.currentThread().getName()+" done!");
        }
    }

    class Consumer {
        public void take() {
            while (!soldOut) {
                try {
                    // 最多允许3个线程并发消费
                    takes.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (que) {
                    if (!que.isEmpty()) {
                        System.out.println(Thread.currentThread().getName()+" 消费了["+que.pollFirst()+"号商品]!");
                    }
                }
                takes.release();
            }
            System.out.println(Thread.currentThread().getName()+" done!");
        }
    }




}
