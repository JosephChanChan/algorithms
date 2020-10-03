package pointers;

import java.util.*;

/**
 * leetcode 15 medium
 *
 * Analysis:
 * 难点在于去重，对于三数之和，找一个a+b+c=0的式子，可以固定a，剩下的b/c用2sum的做法解决。
 * 枚举a O(n)，找b和c用双指针 O(n)，总共是O(n^2)。问题是枚举到a，找到b,c 使得a+b+c=0，
 * 枚举到b，找到a,c 使得b+a+c=0，重复了怎么办？
 * 可以参考 leetcode 39，其实对于去重的题目一个通用思想是排序后按顺序枚举，避免重复。
 * 因为是从左到右枚举a，a如果可以和b,c组成式子，假设数列: x x x b x c x x a x ... x
 * b+c+a=0，当枚举到a时，b和c就不可再从a的左边枚举了，因为b,c肯定已经和a组合过。
 * 假设还存在b1,c1可以和a组合成立，那么b1只可能在a右边才不会和b重复。
 * 假设存在 a+b+c=0，每一次枚举a时，从a右边开始枚举b,c，就不会出现 b+a+c=0, c+a+b=0 重复组合的情况。
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-09-26 22:28
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        if (nums.length < 3) return ans;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return ans;
            if (i > 0 && nums[i] == nums[i-1]) continue;

            int a = nums[i];
            int l = i + 1, r = nums.length - 1;

            while (l < r) {
                int b = nums[l], c = nums[r];
                if (a + b + c == 0) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(a); list.add(b); list.add(c);
                    ans.add(list);

                    while (l < r && b == nums[l]) l++;
                    while (l < r && c == nums[r]) r--;
                }
                else if (a + b + c < 0) {
                    while (l < r && b == nums[l]) l++;
                }
                else {
                    while (l < r && c == nums[r]) r--;
                }
            }
        }
        return ans;
    }
}
