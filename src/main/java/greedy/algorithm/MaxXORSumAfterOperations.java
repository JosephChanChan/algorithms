package greedy.algorithm;

import java.util.*;

/**
 * lc 2317 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/10/2
 */
public class MaxXORSumAfterOperations {

    /**
         贪心+位运算分析

         从题目的要求入手思考分析。最后要求所有Ai做异或和返回结果，所有Ai异或和怎样最大？
         当然是期望每个Ai的每个二进制位的1都是奇数，则保证结果的每个二进制位都是1，可以最大。
         再按照题目给出的例子分析，为什么选6 & (6^4)，可看出[3,2,4,6]异或结果按照二进制位算，在第2位有2个1导致异或是0，
         6 & (6^4)将6变2，并且这个二进制位是0，让整体异或结果的该二进制位可以是1。
         贪心策略，让偶数个1的二进制位变成奇数个1。
         那对于某个二进制位都是0的，可不可以变成奇数个1？
         不行，由于题目限定了nums[i] AND (nums[i] XOR x)操作，会发现最后与运算让原本异或运算生成的1又被抹掉了。
         所以这个操作只能消除1，不能增加1
     */

    // 二进制位 -> 该位要被改的第一个数
    Map<Integer, Integer> h = new HashMap<>();

    public int maximumXOR(int[] nums) {
        return solution2(nums);
    }

    int solution2(int[] a) {
        // 其实更简单更清晰的思路是，既然期望每个bit都是1，那根据分析不能将bit位全是0的生成1，就只能借助原来bit位有奇数个1的异或。
        // 直接Ai做或运算，每个bit有奇数个1的自然保留下来
        int ans = a[0];
        for (int i = 1; i < a.length; i++) {
            ans = ans | a[i];
        }
        return ans;
    }

    int solution1(int[] nums) {
        int bi = 1;
        for (int i = 0; i < 30; i++) {
            int c = 0;
            int firstIdx = -1;
            for (int j = 0; j < nums.length; j++) {
                int Ai = nums[j];
                if ((Ai & bi) > 0) {
                    c++;
                    if (firstIdx == -1) {
                        firstIdx = j;
                    }
                }
            }
            if ((c & 1) == 0 && c >= 2) {
                h.put(i, firstIdx);
            }
            bi = bi << 1;
        }
        for (int i = 0; i < 30; i++) {
            Integer v = h.get(i);
            if (null == v) {
                continue;
            }
            int first = nums[v];
            bi = 1;
            bi = bi << i;
            first = first & (first ^ bi);
            nums[v] = first;
        }
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }
}
