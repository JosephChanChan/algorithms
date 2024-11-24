package pointers;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 1004 medium
 *
 * 时间复杂度：滑动窗口O(n)，哈希前缀O(n)，二分O(n*log(n))
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-08-28
 */
public class MaxConsecutiveOnes3 {

    public int longestOnes(int[] nums, int k) {
        return sliding(nums, k);
    }

    public int hashPrefix(int[] nums, int k) {
        /*
             连续的一段区间，窗口。求符合条件的最大的窗口，每个窗口内的0的个数可以通过预处理得到
             s(i)为0~i区间内0的数量，如果0数量<=k则这个区间全都是1
             如果0数量>k，s(i)-k=d，看看是否有s(j)=d，舍弃s(j)这段，j+1~i的区间都是1
             如此计算得到最大的区间
         */
        int ans = 0;
        int n = nums.length;
        int[] s = new int[n];
        s[0] = nums[0] == 1 ? 0 : 1;

        for (int i = 1; i < n; i++) {
            s[i] = s[i-1] + (nums[i] == 1 ? 0 : 1);
        }

        // 0的前缀数量和 -> 区间右边界
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (s[i] <= k) {
                ans = Math.max(ans, i + 1);
            }
            else {
                int d = s[i] - k;
                //System.out.println("i="+i+" d="+d);
                // 多了d个0，前面的区间如果有d个0，舍弃
                if (map.containsKey(d)) {
                    ans = Math.max(ans, i - map.get(d));
                    //System.out.println("i="+i+" d="+d+" 右边界="+map.get(d));
                }
            }
            if (!map.containsKey(s[i])) {
                map.put(s[i], i);
            }
        }
        return ans;
    }

    private int sliding(int[] nums, int k) {
        /*
            i~j有效窗口，j遇到0用k值补，显然窗口内0最多不超过k值。
            当0超过k值时，i右移回收k值，给j填补新的0并期望j右移遇到更多1组成更长的窗口。
            这是滑动窗口这题的本质思想。
        */
        int[] a = nums;
        int n = a.length;

        if (null == a || n == 0) return 0;
        if (k == 0) return calc(a);

        int i = 0, j = i, max = -1;
            while (i < n) {
            while (j < n) {
                if (a[j] == 0 && k == 0) {
                    break;
                }
                if (a[j] == 0 && k > 0) {
                    k--;
                }
                if (j-i+1 > max) {
                    max = j-i+1;
                }
                j++;
            }
            while (i < j) {
                if (a[i++] == 0) {
                    k++;
                    break;
                }
            }
        }
        return max;
    }

    int calc(int[] a) {
        int c = 0, max = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                max = Math.max(max, c);
                c = 0;
            }
            else {
                c++;
            }
        }
        max = Math.max(max, c);
        return max;
    }

    private int binary(int[] nums, int k) {
        /*
            对于聪明人一个特质是当学会一道题后他实际上学会的不只是会解这一题，
            而是理解了背后的本质能做到举一反三。（羡慕）
            其实二分在这题上背后的本质是加速搜索另一个边界。

            暴力法：找到最长的区间，区间内0的数量<=k。
            要找到这个max{l~r}的区间，枚举每一个l和每一个r，时间n^2
            对于一个区间还要检查区间内的0的数量<=k？总时间n^3
            利用题目特点，a[i]=0/1，则前缀和可以O(1)时间知道一个区间内的0的数量，|i-j+1|-sum[j]-sum[i-1]
            现在只需要枚举l和r，总时间优化到O(n^2)

            二分法：一个题目能用二分需要单调性，因为单调性包含了二分性。
            思考前缀和数组，i<j则sum[j]>=sum[i]这是有单调性的。
            如果固定下标0为左边界，则必定有一个分界点m。
            0~m这个区间内的0数量<=k，0~m+1...n的区间内0的数量>k
            分界点m这个性质就是有二分性，由单调性引出二分性。
            所以我们枚举左边界l，对于每个l，二分搜索右边界r，组成l~r区间0的数量<=k，记录max{l~r}
            总时间 N*logN
         */
        int[] a = nums;
        int n = a.length;

        if (null == a || n == 0) return 0;

        int[] sum = new int[n];
        sum[0] = a[0];
        boolean con = sum[0] > 0;
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i-1] + a[i];
            con = sum[i] > 0;
        }

        if (!con) return k;

        int max = 0;
        // 枚举最大的l~r区间
        for (int i = 0; i < n; i++) {
            int l = i, r = n-1, m ;
            while (l + 1 < r) {
                m = (l + r) >> 1;
                if (check(a, k, sum, i, m)) {
                    l = m;
                }
                else {
                    r = m;
                }
            }
            int j = check(a, k, sum, i, r) ? r : l;
            max = Math.max(max, j-i+1);
        }
        return max;
    }

    boolean check(int[] a, int k, int[] sum, int i, int j) {
        //System.out.println("j-i+1="+(j-i+1)+" tol="+(sum[j] - (i > 0 ? sum[i-1] : 0)));
        return k >= (j-i+1) - (sum[j] - (i > 0 ? sum[i-1] : 0));
    }
}
