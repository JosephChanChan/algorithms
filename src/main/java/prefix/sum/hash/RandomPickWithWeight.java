package prefix.sum.hash;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

/**
 * lc 528 medium
 *
 * Analysis:
 *  随机性，提交有很小几率失败
 *
 * 时间复杂度：朴素遍历 O(n) 二分O(log(n))
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/2/19 7:18 PM
 */
public class RandomPickWithWeight {

    int n, sum ;
    int[][] p ;
    Random r ;
    TreeSet<int[]> tree = new TreeSet<>((o1, o2) -> o1[0]-o2[0]);

    public RandomPickWithWeight(int[] w) {
        r = new Random();

        n = w.length;
        p = new int[n][2];
        for (int i = 0; i < n; i++) {
            p[i][0] = w[i];
            p[i][1] = i;
            sum += p[i][0];
        }
        // 先按权重排序
        Arrays.sort(p, (o1, o2) -> o1[0] - o2[0]);
        // 概率数组前缀和，放大100倍
        p[0][0] = (int) (((double) p[0][0] / sum) * 100);
        tree.add(new int[]{p[0][0], p[0][1]});
        for (int i = 1; i < n; i++) {
            p[i][0] = ((int) (((double) p[i][0] / sum) * 100)) + p[i-1][0];
            tree.add(new int[]{p[i][0], p[i][1]});
        }
    }

    public int pickIndex() {
        // 随机一个数字在 0~max(a[i])
        int d = r.nextInt(p[n-1][0]+1);
        return logN(d);
    }

    public int plain(int d) {
        // 朴素解法，遍历O(n)
        for (int i = 0; i < n; i++) {
            if (d <= p[i][0]) {
                return p[i][1];
            }
        }
        return p[n-1][1];
    }

    public int logN(int d) {
        // 二叉树
        return tree.ceiling(new int[]{d, n-1})[1];
    }

}
