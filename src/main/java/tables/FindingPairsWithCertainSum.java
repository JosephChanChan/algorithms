package tables;

import java.util.*;

/**
 * lc 1865 medium
 *
 * Analysis:
 * 时间复杂度：count O(a.length)
 * 空间复杂度：O(b.length)
 *
 * @author Joseph
 * @since 2022/9/26
 */
public class FindingPairsWithCertainSum {

    int[] a, b ;
    // 值 -> 下标
    Map<Integer, Set<Integer>> val2Idxs = new HashMap<>();

    public FindingPairsWithCertainSum(int[] nums1, int[] nums2) {
        this.a = nums1;
        this.b = nums2;
        for (int i = 0; i < nums2.length; i++) {
            Set<Integer> set = val2Idxs.getOrDefault(nums2[i], new HashSet<>());
            set.add(i);
            val2Idxs.put(nums2[i], set);
        }
    }

    public void add(int index, int val) {
        int oldVal = b[index];
        b[index] += val;
        int newVal = b[index];
        Set<Integer> set = val2Idxs.getOrDefault(oldVal, new HashSet<>());
        set.remove(index);

        Set<Integer> set2 = val2Idxs.getOrDefault(newVal, new HashSet());
        set2.add(index);
        val2Idxs.put(newVal, set2);
    }

    public int count(int tot) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            //System.out.println("a[i]="+a[i]+" tot="+tot);
            int diff = tot - a[i];
            if (diff <= 0) {
                //System.out.println("break a[i]="+a[i]+" diff="+diff+" tot="+tot);
                continue;
            }
            Set<Integer> set = val2Idxs.get(diff);
            if (null == set) {
                //System.out.println("null a[i]="+a[i]+" diff="+diff+" tot="+tot);
                continue;
            }
            //System.out.println("a[i]="+a[i]+" diff="+diff+" tot="+tot+" set="+set.size());
            ans += set.size();
        }
        return ans;
    }
}
