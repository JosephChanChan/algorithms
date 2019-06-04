import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Joseph
 * @date_time: 2019/6/4 10:31
 */
public class SnowFlakeIdTest {

    private int count = 3000000;
    private volatile boolean flag = true;

    private final Object remains = new Object();
    private Map<Long, Object> vessel = new ConcurrentHashMap<>();
    private Map<Long, Object> vessel2 = new ConcurrentHashMap<>();

    private static List<Object[]> queueList = new ArrayList<>();

    private ThreadLocal<Queue<Long>> localQueue = new ThreadLocal<>();
    private ThreadLocal<ReentrantReadWriteLock> localLock = new ThreadLocal<>();

    private CountDownLatch latch = new CountDownLatch(7);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 11; i++) {
            long time = System.currentTimeMillis();
            SnowFlakeIdTest test = new SnowFlakeIdTest();

            test.doConcurrentTask();
            test.latch.await();
            long tail = System.currentTimeMillis();
            System.out.println((tail - time) / 1000);
            System.out.println("All workers finished jobs! ");
        }
    }

    private void doConcurrentTask() {
        // 3个线程，每个线程运行 500w 次获取雪花id，看是否有重复
        for (int i = 0; i < 3; i++) {
            new Thread(new SpadeSnowWorker()).start();
        }
        // 3个线程，每个线程运行 500w 次获取雪花id，看是否有重复
        for (int i = 0; i < 3; i++) {
            new Thread(new SpadeSnowWorker2()).start();
        }
        // 异步消费雪花id，都写进文件中，后面如果有冲突，可以查这个文件
        new Thread(new FileWorker()).start();
    }

    private void storeId(Long snowFlakeId) {
        Queue<Long> list = localQueue.get();
        if (null == list) {
            list = new LinkedList<>();
            localQueue.set(list);

            // 默认非公平模式读写锁
            ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            localLock.set(readWriteLock);

            Object[] kits = new Object[2];
            kits[0] = readWriteLock;// 操作队列前先获取锁
            kits[1] = list;// 队列
            queueList.add(kits);
        }

        // 往队列写数据时要注意并发，先获取写锁
        ReentrantReadWriteLock readWriteLock = localLock.get();
        try {
            readWriteLock.writeLock().lock();
            list.offer(snowFlakeId);
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private class SpadeSnowWorker implements Runnable {

        @Override
        public void run() {
            Object old ;
            long snowFlakeId ;
            for (int i = 0; i < count && flag; i++) {
                snowFlakeId = SnowFlakeId.nextSnowFlakeId(12);
                old = vessel.put(snowFlakeId, remains);
                if (null != old) {
                    System.out.println("SnowFlakeId already exists in vessel! it is " + snowFlakeId);
                    flag = false;
                }
                storeId(snowFlakeId);
            }
            latch.countDown();
        }
    }

    private class SpadeSnowWorker2 implements Runnable {

        @Override
        public void run() {
            Object old ;
            long snowFlakeId ;
            for (int i = 0; i < count && flag; i++) {
                snowFlakeId = SnowFlakeId.nextSnowFlakeId(13);
                old = vessel2.put(snowFlakeId, remains);
                if (null != old) {
                    System.out.println("SnowFlakeId already exists in vessel2! it is " + snowFlakeId);
                    flag = false;
                }
                storeId(snowFlakeId);
            }
            latch.countDown();
        }
    }

    private class FileWorker implements Runnable {

        File file ;
        static final String filePath = "D:\\SnowFlakeIdList.txt";
        BufferedWriter bufferedWriter = null;

        public FileWorker() {
            file = new File(filePath);
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            int queueCount = queueList.size();
            try {
                // 当前还有写线程在工作，还得继续读
                while (1 < latch.getCount()) {
                    pollingQueue(queueCount);
                }
                // 所有写线程完成后，再轮询一遍队列，防止读线程睡眠时写线程写了数据
                pollingQueue(queueCount);
            }
            finally {
                try {
                    if (null != bufferedWriter) {
                        bufferedWriter.flush();
                        bufferedWriter.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            latch.countDown();
        }

        private void pollingQueue(int queueCount) {
            Object[] kits ;
            Queue<Long> queue ;
            ReentrantReadWriteLock readWriteLock ;

            // 不断轮询每个队列，写进本地文件
            for (int i = 0; i < queueCount; i++) {
                kits = queueList.get(i);
                queue = (Queue<Long>) kits[1];
                readWriteLock = (ReentrantReadWriteLock) kits[0];

                try {
                    readWriteLock.readLock().lock();
                    while (queue.size() > 0) {
                        Long snowFlakeId = queue.poll();// 从队列头出队
                        if (null == snowFlakeId) continue;
                        write2File(snowFlakeId);
                    }
                }
                finally {
                    readWriteLock.readLock().unlock();
                }
            }

            // 本次轮询完毕，先睡10ms避免与写线程过度争抢锁和cpu
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void write2File(Long snow) {
            try {
                bufferedWriter.write(String.valueOf(snow));
                bufferedWriter.newLine();
            }
            catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        }
    }
}
