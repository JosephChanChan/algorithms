import sort.CheckSortedArr;

import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        Main m = new Main();
        m.test();
    }

    void test() {
        FooBar fooBar = new FooBar(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.foo(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.bar(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("done");
    }

    class FooBar {
        /**
         用wait notify机制来协调2个线程工作。
         每次要让foo线程执行，再让bar执行。如果bar先执行怎么办？所以要有个条件检测，不满足条件则bar等待。
         这个条件可以是奇数 偶数。因为只有2个线程。并且为了安全，更改条件只能在锁中更改。
         */
        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        public synchronized void foo(Runnable printFoo) throws InterruptedException {
            // i < n 还需要执行
            for (int i = 0; i < n; ) {
                // 奇数，条件不满足 wait
                if ((i & 1) > 0) {
                    this.notify();
                    this.wait();
                }
                else {
                    // 条件满足，输出
                    System.out.println("foo");
                    i++;
                }
            }
            // 工作完成后，唤醒其它等待中的线程
            this.notify();
        }

        public synchronized void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; ) {
                if ((i & 1) == 0) {
                    this.notify();
                    this.wait();
                }
                else {
                    System.out.println("bar");
                    i++;
                }
            }
            // 工作完成后，唤醒其它等待中的线程
            this.notify();
        }
    }







}
