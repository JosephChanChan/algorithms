package greedy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Question Description:
 * 约翰认为字符串的完美度等于它里面所有字母的完美度之和。每个字母的完美度可以由你来分配，
 * 不同字母的完美度不同，分别对应一个1-26之间的整数。
 * 约翰不在乎字母大小写。（也就是说字母F和f）的完美度相同。给定一个字符串，输出它的最大可能的完美度。
 * 例如：dad，你可以将26分配给d，25分配给a，这样整个字符串完美度为77。
 *
 * 输入
 * 一个字符串S(S的长度 <= 10000)，S中没有除字母外的其他字符。
 *
 * 输出
 * 由你将1-26分配给不同的字母，使得字符串S的完美度最大，输出这个完美度。
 *
 * 输入示例
 * dad
 *
 * 输出示例
 * 77
 *
 * Analysis:
 * 题目要求最大可能的完美度, 出现次数最多的字母显然应该给26, 然后按照出现次数从大到小，依次分配从高到低的权值。这就是最朴素的贪心思想。
 *
 * created by Joseph
 * at 2018/7/25 14:18
 */
public class PerfectString {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1 << 16);

        String perfectString = null;
        try {
            perfectString = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int sum = calcPerfectString(perfectString);
        System.out.println(sum);
    }

    /**
     * 计算给定的字符串, 自由分配权值, 最大可能出现的分数
     *
     * @param perfectString perfectString
     */
    private static int calcPerfectString(String perfectString){
        /*
            每个字符按Ascii字符集转成逻辑编码, 逻辑编码作为下标存在数组里.
            因为字符都是 A~Z, Ascii由一个字节表示, 所以逻辑编码值不会超过127.
         */
        Integer[] valueCountArray = new Integer[127];

        perfectString = perfectString.toUpperCase();
        char[] charArray = perfectString.toCharArray();

        for (char character : charArray){
            int value = valueCountArray[character];
            value++;
            valueCountArray[character] = value;
        }

        sort.QuickSort.doQuickSort(valueCountArray, 0, valueCountArray.length-1);

        int score = 26, sum = 0;

        for (int value : valueCountArray){
            if (value != 0){
                sum += value * score;
                score--;
            }
        }

        return sum;
    }

}
