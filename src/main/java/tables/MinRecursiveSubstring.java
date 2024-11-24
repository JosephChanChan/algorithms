package tables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joseph
 * @since 2020-04-16 23:25
 *
 * 美团实习校招笔试
 *
 * Question Description:
 *  给定四个数字 a b m x
 *  while(true) {
 *      x = (a*x+b)%m
 *      print(x)
 *  }
 *  输出的x由于在取模的意义下，所以会出现循环。
 *  例如 a=2 b=1 m=5 x=2 时，输出序列如下：
 *  0 1 3 2 0 1 3 2 ...
 *  其中 0 1 3 2 为最短循环子串
 *  给定 a b m x 情况下，请计算最短循环子串的长度输出
 *
 *  样例输入：
 *  2 1 5 2
 *  样例输出：
 *  4
 *  数据范围：
 *  1 <= a,b,m,x <= 100000
 *
 * Analysis:
 *
 *
 */
public class MinRecursiveSubstring {


    private static long a, b, m, x ;
    private static int p = 0, q = 1, secondSeqStart = -1, maxLen = 0;
    private List<Long> sequence = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String[] inputArray = input.split(" ");
        a = Long.parseLong(inputArray[0]);
        b = Long.parseLong(inputArray[1]);
        m = Long.parseLong(inputArray[2]);
        x = Long.parseLong(inputArray[3]);

        MinRecursiveSubstring instance = new MinRecursiveSubstring();
        /*instance.test();*/
        instance.calc();
    }

    private void calc() {
        x = (a * x + b) % m;
        sequence.add(x);

        while (true) {
            x = (a * x + b) % m;
            sequence.add(x);

            if (sequence.get(p).equals(sequence.get(q))) {
                if (secondSeqStart == -1) {
                    secondSeqStart = q;
                }
                p++;
                maxLen++;
            }
            else {
                p = 0;
                maxLen = 0;
                secondSeqStart = -1;
            }
            if (p == secondSeqStart) {
                System.out.println(maxLen);
                StringBuilder builder = new StringBuilder();
                for (int i = secondSeqStart; i <= q; i++) {
                    builder.append(sequence.get(i)).append(" ");
                }
                System.out.println(builder.toString());
                break;
            }
            q++;
        }
    }

    private void test() {
        while (true) {
            x = (a * x + b) % m;
            System.out.print(x+" ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
