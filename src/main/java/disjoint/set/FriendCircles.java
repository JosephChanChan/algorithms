package disjoint.set;

/**
 * leetcode 547 medium
 *
 * Question Description:
 *  参见 lc 547
 *
 * Analysis:
 *
 * @author Joseph
 * @since 2020-08-17 18:19
 */
public class FriendCircles {

    int[] parents ;
    int[] heights ;

    public int findCircleNum(int[][] M) {
        int n = M.length;
        if (n == 0) return 0;
        if (n == 1) return 1;

        parents = new int[n];
        heights = new int[n];

        // 0~n-1
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        // 如果a b两个人是朋友那么会在同一个社交圈内
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (M[i][j] == 1) {
                    int root1 = findRoot(i);
                    int root2 = findRoot(j);
                    // 合并a b的社交圈
                    union(root1, root2);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == i) count++;
        }
        return count;
    }

    private int findRoot(int a) {
        while (a != parents[a]) {
            a = parents[a];
        }
        return a;
    }

    private void union(int a, int b) {
        if (a == b) return;
        if (heights[a] == heights[b]) {
            parents[a] = b;
            heights[b]++;
        }
        else if (heights[a] > heights[b]) {
            parents[b] = a;
        }
        else {
            parents[a] = b;
        }
    }
}
