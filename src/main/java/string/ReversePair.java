package string;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 美团2020校招笔试
 *
 * Question Description:
 * 小团最近对逆序数（将一个数字逐位逆序，例如1234的逆序数为4321）特别感兴趣，但是又觉得普通的逆序数问题有点太乏味了。
 * 于是他想出了一个新的定义：如果一个数的4倍恰好是它的逆序数，那么称这两个数是新定义下的逆序对。
 * 接下来给定一正整数n，问：不超过n的正整数中有多少对新定义下的逆序对？
 *
 * 输入描述
 * 输入一个正整数n，n<1e7。
 *
 * 输出描述
 * 第一行输出在不超过n的前提下有多少对逆序数，接下来每一行输出一对逆序数，以空格分隔。如果有多组逆序数，按照第一个数升序输出。
 * 如果没有一对逆序数则直接输出0即可。
 *
 * 样例输入
 * 10000
 * 样例输出
 * 1
 * 2178 8712
 *
 * @author Joseph
 * @since 2020-08-15 18:12
 */
public class ReversePair {

    static int n, count = 0;
    static Map<Integer, Integer> result = new LinkedHashMap<>(100000);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        int i ;
        Outer:
        for (i = 1; i <= n; i++) {
            int t = i << 2;
            String s1 = String.valueOf(i);
            String s2 = String.valueOf(t);
            if (s1.length() == s2.length()) {
                int l = 0, r = s1.length() - 1;
                while (l < s1.length()) {
                    if (s1.charAt(l++) != s2.charAt(r--)) continue Outer;
                }
                count++;
                result.put(i, t);
            }
        }

        System.out.println(count);
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " "+entry.getValue());
        }
    }

}
