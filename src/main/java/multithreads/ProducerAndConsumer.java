package main.java.multithreads;

/**
 * Created by Joseph on 2017/6/15.
 *  多线程，一个线程打印1-52，另一个线程打印字母A-Z
 */
public class ProducerAndConsumer {

    public static void main(String[] args){
        //开50个线程运行 50次，检查是否有错误
        for (int i=0; i<50; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Execute execute = new Execute();
                    execute.run();
                }
            }).start();
        }
    }


    private static class Execute{

        final String answer = "12A34B56C78D910E1112F1314G1516H1718I1920J2122K2324L2526M2728N2930O3132P3334Q3536R3738S3940T4142U4344V4546W4748X4950Y5152Z";
        StringBuilder builder = new StringBuilder();

        public void run(){
            Producer producer = new Producer();
            Consumer consumer = new Consumer();

            Thread one = new Thread(producer);
            Thread two = new Thread(consumer);
            one.start();
            two.start();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result = builder.toString();
            if(result.length() != answer.length()){
                System.out.println("2组数据长度不一致");
            }

            if(!result.equals(answer)){
                System.out.println("不中啊");
                System.out.println(result);
            }
            else {
                System.out.println("中了");
                System.out.println("result = "+result);
            }
        }

        private class Producer implements Runnable{

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
                System.out.println("线程 Producer 完成任务!");
                //因为消费者最后输出还在wait。通知消费者 已完成任务
                synchronized(builder){
                    builder.notify();
                }
            }
        }

        private class Consumer implements Runnable{

            char a = 'A';
            @Override
            public void run() {
                //如果consumer先执行，放弃cpu
                while (builder.length() == 0){
                    Thread.yield();
                }
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
                System.out.println("线程 Consumer 完成任务!");
            }
        }

    }

}
