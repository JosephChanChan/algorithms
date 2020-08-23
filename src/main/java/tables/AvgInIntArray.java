package tables;

/**
 * 腾讯2020校招面试
 *
 * Question Description:
 *  给定一个长度n的数组a[]，1<=ai<=1e9，只用int求出a的平均数，这个平均数保证在int范围内
 *
 * Analysis:
 * 思想是将ai平均分给n，将每个ai的结果累加就等于 先将ai累加再平分n，是一样的。
 * 将ai/n累加，这部分是平均数的整数部分，再将ai%n 余数累加，最后余数/n加到整数中。
 *
 * @author Joseph
 * @since 2020-08-23 14:56
 */
public class AvgInIntArray {

    public static void main(String[] args) {
        AvgInIntArray test = new AvgInIntArray();
        // (2029909210+229430492+390238098+42323+5343)÷5 = 529925093.2
        int[] a = {2029909210,229430492,390238098,42323,5343};
        int i = test.calcAvg(a);
        System.out.println(i);
    }

    private int calcAvg(int[] a) {
        int integrity = 0, decimal = 0, n = a.length;
        for (int i = 0; i < a.length; i++) {
            integrity += a[i] / n;
            decimal += a[i] % n;
        }
        integrity += decimal / n;
        // 剩下的decimal是不够n的，即被n除是小数，题目要求只能用int，那么只能求到整数部分的精度？
        return integrity;
    }
}
