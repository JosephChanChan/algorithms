package tables;

import java.util.HashMap;
import java.util.Map;

/**
 * lc medium 454
 *
 * Analysis：
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2023/1/13
 */
public class FourSum2 {

    /*
        哈希+一点数学
        原本是a+b+c+d=0，要枚举4个变量，加个Hash降一维到3个变量，还是超时。
        其实可以优化成a+b=A，c+d=B，记录下构成A的数量和构成B的数量，A+B=0，A*B就可以知道a+b+c+d=0数量
        本质思想是利用了组合原理，把多个变量合成一个变量，记录下合成这个变量的记录数，以此降低时间复杂度
     */

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        Map<Integer, Integer> A = new HashMap<>();
        Map<Integer, Integer> B = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = nums1[i];
            int b = nums3[i];
            for (int j = 0; j < n; j++) {
                int v = a + nums2[j];
                int v1 = b + nums4[j];
                A.put(v, A.getOrDefault(v, 0)+1);
                B.put(v1, B.getOrDefault(v1, 0)+1);
            }
        }
        int ans = 0;

        for (Map.Entry<Integer, Integer> a : A.entrySet()) {
            Integer key = a.getKey();
            Integer freq = a.getValue();
            int diff = 0-key;
            Integer freq2 = B.getOrDefault(diff, 0);
            ans += (freq * freq2);
        }

        return ans;
    }
}
