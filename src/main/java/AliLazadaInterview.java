import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Joseph
 * @since 2021-07-09 17:26
 */
public class AliLazadaInterview {

    //评测题目:
    //题目1：无实现一个按权重进行负载均衡的算法i=f(int[] weights)。输入为一组机器机器对应的权重，
    //返回值为当次请求返回的对应机器的下标。
    //例如: 输入 int[] ={3,5,20,12,22,38}, 输出： 3

    //题目2：请实现两个线程，使之交替打印1 - 100，如：两个线程名称分别为：Printer1和Printer2，最后输出结果为：
    //Printer1 — 1
    //Printer2 — 2
    //Printer1 — 3
    //Printer2 — 4

    class LoadBalancer {

        int run(int[] w) {
            int n = w.length;
            // 0~3, 3~8, 8~28, 28~40
            int[] space = new int[n];
            space[0] = w[0];
            int sum = w[0];

            // 累计每个区间，区间差值是概率范围
            for (int i = 1; i < n; i++) {
                sum += w[i];
                space[i] = space[i-1]+w[i];
            }

            Random r = new Random();
            // 随机范围 [1, sum]
            int j = r.nextInt(sum)+1;

            for (int i = 0; i < n; i++) {
                if (j <= space[i]) return i;
            }
            return -1;
        }
    }

    class ProducerAndConsumer {

        AtomicInteger order = new AtomicInteger(1);
        Semaphore p = new Semaphore(1);
        Semaphore q = new Semaphore(0);

        void run() {
            Producer producer = new Producer();
            Consumer consumer = new Consumer();
            Thread t1 = new Thread(producer::make);
            Thread t2 = new Thread(consumer::take);

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Main done");
        }

        class Producer {
            public void make() {
                // 需要继续
                while (order.get() <= 100) {
                    try {
                        p.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (order.get() > 100) break;
                    System.out.println(Thread.currentThread().getName()+"-"+order.getAndIncrement());
                    q.release();
                }
                System.out.println("Producer done");
            }
        }

        class Consumer {
            public void take() {
                // 需要继续
                while (order.get() <= 100) {
                    try {
                        q.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (order.get() > 100) break;
                    System.out.println(Thread.currentThread().getName()+"-"+order.getAndIncrement());
                    p.release();
                }
                System.out.println("Consumer done");
            }
        }
    }
}
