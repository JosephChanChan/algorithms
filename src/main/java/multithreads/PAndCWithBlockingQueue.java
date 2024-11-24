package multithreads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者&消费者，用阻塞队列实现。
 * LinkedBlockQueue比ArrayBlockQueue有更高并发，因为读写锁分开。
 * LBQ使用dummy node， offer()和poll()操作的不是同一个节点，offer总是插入队尾，poll总是弹出头部。
 * 当可以poll时队列保证至少会有2个节点，head->real，此时有写线程offer，也只会操作到real.next指针，而poll线程只会操作
 * head指针和real节点值，读写线程不冲突。
 *
 * @author Joseph
 * @since 2021-07-09 09:51
 */
public class PAndCWithBlockingQueue {

    // 控制生产者刚好生产指定件数
    AtomicInteger goods = new AtomicInteger(0);
    // 控制消费者把所有商品刚好消费完，消费件数要和生产件数对应
    AtomicInteger cost = new AtomicInteger(0);
    // 控制商品库存最大为10，让消费者饥饿等待
    BlockingQueue<Integer> que = new LinkedBlockingQueue<>(10);


    public static void main(String[] args) {
        PAndCWithBlockingQueue m = new PAndCWithBlockingQueue();
        m.run();
    }

    void run() {
        Producer p = new Producer();
        Consumer c = new Consumer();
        for (int i = 0; i < 10; i++) {
            new Thread(p::make, "Producer"+i).start();
            new Thread(c::take, "Consumer"+i).start();
        }
        while (cost.get() < 100) {
            Thread.yield();
        }
        System.out.println("stock remain "+que.size());
    }


    class Producer {
        public void make() {
            while (goods.get() < 100) {
                try {
                    // put 容量满了会阻塞直到非满，入队成功会通知notEmpty队列上的消费者
                    int i = goods.getAndIncrement();
                    que.put(i);
                    System.out.println(Thread.currentThread().getName()+" 生产了["+i+"]号商品!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" done");
        }
    }

    class Consumer {
        public void take() {
            while (cost.get() < 100) {
                try {
                    // 消费者数>库存数，总有线程挂起的，避免售罄后有消费者仍被挂起，需要超时等待后回来查看是否售罄
                    Integer goods = que.poll(1000, TimeUnit.MILLISECONDS);
                    if (null != goods) {
                        cost.getAndIncrement();
                        System.out.println(Thread.currentThread().getName()+" 消费了["+goods+"]号商品!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" done");
        }
    }



















}
