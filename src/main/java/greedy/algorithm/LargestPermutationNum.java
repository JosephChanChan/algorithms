package greedy.algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * lc 179 medium
 *
 * Analysis:
 * 时间复杂度：O(n*log(n)*avg(a[i]))，n是数组长度，avg(a[i])是数字的平均长度
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/12/28
 */
public class LargestPermutationNum {

    /*
        假设所有数字排列后组成一个数字长度为n，越高位的数位希望数字越大
        则对于任意数字Ai > Aj，则Ai应该排在越高位。
        对于长度不同的数字，例如 3和34，考虑 334和343，显然如果先选了3再选34，则数字更小
        也就是长度不同的数字要考虑数字每位更大的排在前面。
        但是对于 432和43243，就很难判断谁放在前面。最好的办法是a+b拼接起来，b+a，再判断哪个大。
        原本直接判断a和b大小，时间是O(a+b)，拼接后再判断时间还是O(3(a+b))，复杂度没升高
     */

    Queue<Integer> q = new PriorityQueue<>((o1, o2) -> {
        String a = String.valueOf(o1);
        String b = String.valueOf(o2);
        if (a.length() == b.length()) {
            return o2 - o1;
        }
        int i = 0, j = 0;
        char[] ca = (a + b).toCharArray();
        char[] cb = (b + a).toCharArray();
        while (i < ca.length) {
            if (ca[i] == cb[j]) {
                i++; j++;
                continue;
            }
            if (ca[i] > cb[j]) {
                return -1;
            }
            return 1;
        }
        return 0;
    });

    public String largestNumber(int[] nums) {
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        boolean allZero = true;
        for (int i : nums) {
            if (i > 0) {
                allZero = false;
            }
            q.add(i);
        }
        StringBuilder b = new StringBuilder();
        while (!q.isEmpty()) {
            b.append(q.poll());
        }
        if (allZero) {
            return "0";
        }
        return b.toString();
    }
}
