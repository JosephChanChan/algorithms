package greedy.algorithm;

import java.util.*;

/**
 * 剑指Offer 45 medium
 *
 * Analysis:
 *  将整数数组拼成最小的字符串。
 * 就要对两个数字怎么拼接做选择，例如：a和b两个数字，将a+b拼成ab，b+a拼成ba
 * 究竟是ab小还是ba小？
 * ab和ba的字符串长度肯定相同。
 * 那就逐位比较ab ba的字符大小。例如 a=3, b=30
 * ab=330, ba=303 每一位字符都是0~9的，所以按照逐位比较字典序就行 '0' ascii码为48
 *
 * 之前错误的做法的思想和正解的思想很相似。我一开始想到也是对a b两个字符作对比，
 * 但是是单独的对比a&b的大小。例如 a=3, b=30，自己写了对比规则对比 '3' > '30'
 * 如果 a.length != b.length，经过逐位对比字符大小后，一直到较短串的最后字符对比较长串的剩下字符。
 * 例如
 * a   '3'     ->        '3'
 *      i                 i
 * b   '3 0'   ->        '3 0'
 *      j                   j
 * 也可以对比出 '3' < '30'，但是这个算法很有迷惑性，在面对 220 case时就错误了。
 * 该算法的反例：a=30, b=302，该算法给出 30302的拼法，正确是 30230
 * 因为当有一个串的尾部字符大于另一个串的除了开头字符的剩余字符时，就给出错误拼法。
 * 而没考虑到 尾部字符<另一个串开头字符。
 * 其实这里面的原理应该是没有考虑整体性，应该从拼接后的ab和ba去比较大小。而不是单独对a b比较。
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(N)
 *
 * @author Joseph
 * @since 2021-01-02 14:35
 */
public class ConcatStringUsingNums {

    public String minNumber(int[] nums) {
        List<String> sorted = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            sorted.add(String.valueOf(nums[i]));
        }
        sorted.sort(new MyComparator());
        StringBuilder b = new StringBuilder();
        for (String a : sorted) {
            b.append(a);
        }
        return b.toString();
    }

    private class MyComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            return s1.concat(s2).compareTo(s2.concat(s1));
        }
    }
}
