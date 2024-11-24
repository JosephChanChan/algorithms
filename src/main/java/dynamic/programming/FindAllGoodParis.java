package dynamic.programming;

import java.util.*;

/**
 * lc 2420 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/25
 */
public class FindAllGoodParis {

    /**
         分析题+DP

         按照题目的要求只需要计算 k+1~n-k-1 范围的元素
         [k+1, n-k-1]这个范围每个元素都是i，对于每个i要校验是否满足2个条件，
         如果对每个i采取遍历方式校验，则时间O(n^2)，太慢。观察可以发现存在重复子问题，如果对每个i，通过O(1)时间知道
         它的左边或右边是否有超过长度k的非递增序列或非递减序列，则时间为O(n)

         f(i)为以Ai结尾的左边i个元素的最长非递增子数组长度
         f(i)=f(i-1)+1, Ai-1 >= Ai

         为什么打比赛时没想到f(i)的递推公式？主要卡在f(i)的状态定义上了，f(i)状态定义错了
         没想到可以只记录i的最长非递增子数组长度，然后和k对比
     */

    int[] leftLens ;
    int[] rightLens ;

    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        leftLens = new int[n];
        rightLens = new int[n];

        leftLens[0] = 1;
        for (int i = 1; i < n; i++) {
            leftLens[i] = nums[i - 1] >= nums[i] ? leftLens[i - 1] + 1 : 1;
        }
        rightLens[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightLens[i] = nums[i + 1] >= nums[i] ? rightLens[i + 1] + 1 : 1;
        }

        List<Integer> ans = new LinkedList<>();
        for (int i = k; i <= n - k - 1; i++) {
            if (leftLens[i - 1] >= k && rightLens[i + 1] >= k) {
                ans.add(i);
            }
            //System.out.println("i="+i+" leftLens[i-1]="+leftLens[i-1]+" rightLens[i+1]="+rightLens[i+1]);
        }
        return ans;
    }
}
