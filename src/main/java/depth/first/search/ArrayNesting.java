package depth.first.search;

/**
 * lc 565 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/7/17
 */
public class ArrayNesting {

    /**
         因为nums中每个ai都不相同，且ai作为下标指向的另一个位置一直到遇到重复的数字
         这一条路径可以看做环，路径上的每个节点就是环的每个节点
         因为nums有若干条分支，就有若干个环，找出长度最大的环
         也就是枚举每个起点深搜环的长度，但是时间n^2
         仔细想想是不是可以避免重复搜索相同的环，因为环搜索完后里面的每个节点肯定都访问过了，再以访问过的节点作为起点
         最后也只是搜索一次环，长度还是一样。
     */
    int max = 0;

    public int arrayNesting(int[] nums) {
        int n = nums.length;

        // 枚举每个环的起点
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                continue;
            }
            int v = nums[i];
            nums[i] = -1;
            int len = dfs(v, nums, 1);
            max = Math.max(len, max);
        }
        return max;
    }

    int dfs(int k, int[] a, int len) {
        //System.out.println(k);
        if (a[k] < 0) {
            return len;
        }
        len++;
        int v = a[k];
        a[k] = -1;
        return dfs(v, a, len);
    }
}
