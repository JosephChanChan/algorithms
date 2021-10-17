package unionfind;

import java.util.*;

/**
 * lc 128 hard
 *
 * Analysis:
 *  遍历数组，合并每一个连续的元素成集合。
 * 并查集。合并时维护每个集合的size，最后遍历得到最大的集合
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-30 23:01
 */
public class LongestContinuousSequence {

    int max = 0;
    Set<Integer> set = new HashSet<>();
    Map<Integer, Integer> setSize = new HashMap<>();
    Map<Integer, Integer> root = new HashMap<>();

    public int longestConsecutive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            root.put(nums[i], nums[i]);
            setSize.put(nums[i], 1);
        }

        int l, r ;
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            l = c-1; r = c+1;
            // ai-1 存在，先合并 ai-1 ai
            if (set.contains(l)) {
                union(c, l);
            }
            // ai+1 存在，再合并 ai和ai+1
            if (set.contains(r)) {
                union(c, r);
            }
        }
        return max;
    }

    private int find(int k) {
        if (root.get(k) == k) return k;
        root.put(k, find(root.get(k)));
        return root.get(k);
    }

    private void union(int a, int b) {
        int p = find(a);
        int q = find(b);
        if (p != q) {
            int s1 = setSize.get(p);
            int s2 = setSize.get(q);
            // p -> q
            root.put(p, q);
            // size of q has been incr
            setSize.put(q, s1+s2);
            max = Math.max(max, s1+s2);
        }
    }

    // 哈希表对这题更优，O(n) 8ms AC
    public int usingHash(int[] nums) {
        int max = 0;
        int p = (int) -1e9, q = (int) 1e9;

        if (nums.length == 0) return 0;

        Set<Integer> vis = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis.contains(nums[i])) continue;
            int a = nums[i];
            // 找左边界
            int l = a;
            for ( ; l >= p; l--) {
                if (!map.containsKey(l)) {
                    l++; break;
                }
                vis.add(l);
            }
            int r = a;
            for ( ; r <= q; r++) {
                if (!map.containsKey(r)) {
                    r--; break;
                }
                vis.add(r);
            }
            max = Math.max(max, r-l+1);
        }
        return max;
    }
}
