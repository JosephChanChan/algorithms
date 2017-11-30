package main.java.multithreads;

/**
 * Created by Joseph on 2017/6/15.
 */
public class ProducerAndConsumer {

    /**
     * 多线程，一个线程打印1-52，另一个线程打印字母A-Z
     */
    static String answer = "12A34B56C78D910E1112F1314G1516H1718I1920J2122K2324L2526M2728N2930O3132P3334Q3536R3738S3940T4142U4344V4546W4748X4950Y5152Z";
    static StringBuilder builder = new StringBuilder();
    public static void main(String[] args){
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
        producerAndConsumer.run();
    }

    public void run(){
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Thread one = new Thread(producer);
        Thread two = new Thread(consumer);
        one.start();
        two.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = builder.toString();
        if(result.length() != answer.length()){
            System.out.println("2组数据长度不一致");
        }

        if(!result.equals(answer)){
            System.out.println("不中啊");
        }

        System.out.println("中了");
        System.out.println("result = "+result);

    }

    class Producer implements Runnable{

        int a = 1;
        @Override
        public void run() {
            for(int i=0; i<26; i++){
                synchronized (builder){
                    builder.append(a++);
                    builder.append(a++);
                    try {
                        builder.notify();
                        builder.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Consumer implements Runnable{

        char a = 'A';
        @Override
        public void run() {
            for(int i=0; i<26; i++){
                synchronized(builder){
                    builder.append(a++);
                    try {
                        builder.notify();
                        builder.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
