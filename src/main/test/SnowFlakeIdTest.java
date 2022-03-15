import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Joseph
 * @since 2019/6/4 10:31
 */
public class SnowFlakeIdTest {
    SnowFlakeId snowFlakeId = new SnowFlakeId(10);
    CountDownLatch c = new CountDownLatch(36);
    AtomicInteger p = new AtomicInteger(0);
    List<Long>[] total = new List[36];

    int w = 36, maxW = w;

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            w, maxW, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(36));

    void test() {
        poolExecutor.prestartAllCoreThreads();
        for (int j = 0; j < maxW; j++) {
            poolExecutor.execute(() -> {
                LinkedList<Long> list = new LinkedList<>();
                try {
                    System.out.println("Thread="+Thread.currentThread().getName()+" begin");
                    for (int i = 0; i < 200000; i++) {
                        list.add(snowFlakeId.nextId());
                    }
                }
                finally {
                    System.out.println("Thread="+Thread.currentThread().getName()+" done");
                    c.countDown();
                    total[p.getAndIncrement()] = list;
                    System.out.println("Thread="+Thread.currentThread().getName()+" exit, list size="+list.size());
                }
            });
        }
        try {
            System.out.println("Thread="+Thread.currentThread().getName()+" wait");
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        poolExecutor.shutdownNow();
        System.out.println("Thread="+Thread.currentThread().getName()+" distincting");
        Set<Long> distinct = new HashSet<>();
        for (List<Long> list : total) {
            for (Long l : list) {
                if (!distinct.add(l)) {
                    throw new RuntimeException("重复的雪花id="+l);
                }
            }
        }
        System.out.println("main done, no repeated id");
    }
}
