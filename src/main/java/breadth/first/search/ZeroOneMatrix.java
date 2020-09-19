package breadth.first.search;

import java.util.*;

/**
 * leetcode 542 medium
 *
 * Analysis:
 *  把所有0看成整体，是源点，从源点扩展。按层级数算距离。因为矩阵中每个点最多被访问一次，所以时间是r*c
 * 时间复杂度：O(rc)
 * 空间复杂度：O(rc)
 *
 *  还有DP的做法，对于(i,j)来说，0可能存在这个点的四个方向，左上、左下、右上、右下。要分别计算四个方向的0到(i,j)的距离。
 * 设f(i,j)为点(i,j)到最近的0的距离，如果0在左上的方向则：
 *  f(i,j) =
 *      (i,j) == 1, min{f(i-1,j), f(i,j-1)} + 1
 *      (i,j) == 0, 0
 *
 *  同理0还可能在左下，右上，右下，计算完4个方向，最后取四个方向的最小数。
 * 时间复杂度：O(4rc)
 * 空间复杂度：O(4rc)
 *
 * 实测 bfs 22ms faster than 36%，dp 15ms faster than 54%
 *
 * @author Joseph
 * @since 2020-09-18 23:21
 */
public class ZeroOneMatrix {

    int r, c, max = Integer.MAX_VALUE;
    int[][] m ;
    int[][] ans, upLeft, downLeft, upRight, downRight ;
    int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] updateMatrix(int[][] matrix) {
        r = matrix.length;
        c = matrix[0].length;

        m = matrix;
        ans = new int[r][c];
        upLeft = new int[r][c];
        downLeft = new int[r][c];
        upRight = new int[r][c];
        downRight = new int[r][c];

        dp();

        return ans;
    }

    private void bfs() {
        Queue<Integer[]> q = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (m[i][j] == 0) {
                    q.add(new Integer[]{i, j});
                }
            }
        }
        doBfs(q);
    }

    private void doBfs(Queue<Integer[]> q) {
        int stair = 0;

        while (!q.isEmpty()) {
            stair++;
            int size = q.size();
            for (int k = 0; k < size; k++) {
                Integer[] p = q.poll();
                int i = p[0];
                int j = p[1];
                for (int t = 0; t < d.length; t++) {
                    int y = i + d[t][0];
                    int x = j + d[t][1];
                    if (valid(y, x) && m[y][x] == 1 && ans[y][x] == 0) {
                        q.add(new Integer[]{y, x});
                        ans[y][x] = stair;
                    }
                }
            }
        }
    }

    private void dp() {
        // calc up-left
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (m[i][j] == 0) {
                    upLeft[i][j] = 0;
                    continue;
                }
                int min = max;
                if (valid(i-1, j)) {
                    min = Math.min(min, upLeft[i-1][j]);
                }
                if (valid(i, j-1)) {
                    min = Math.min(min, upLeft[i][j-1]);
                }
                upLeft[i][j] = min == max ? min : min + 1;
            }
        }
        for (int i = r-1; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                if (m[i][j] == 0) {
                    downLeft[i][j] = 0;
                    continue;
                }
                int min = max;
                if (valid(i+1, j)) {
                    min = Math.min(min, downLeft[i+1][j]);
                }
                if (valid(i, j-1)) {
                    min = Math.min(min, downLeft[i][j-1]);
                }
                downLeft[i][j] = min == max ? min : min + 1;
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = c-1; j >= 0; j--) {
                if (m[i][j] == 0) {
                    upRight[i][j] = 0;
                    continue;
                }
                int min = max;
                if (valid(i-1, j)) {
                    min = Math.min(min, upRight[i-1][j]);
                }
                if (valid(i, j+1)) {
                    min = Math.min(min, upRight[i][j+1]);
                }
                upRight[i][j] = min == max ? min : min + 1;
            }
        }
        for (int i = r-1; i >= 0; i--) {
            for (int j = c-1; j >= 0; j--) {
                if (m[i][j] == 0) {
                    downRight[i][j] = 0;
                    continue;
                }
                int min = max;
                if (valid(i+1, j)) {
                    min = Math.min(min, downRight[i+1][j]);
                }
                if (valid(i, j+1)) {
                    min = Math.min(min, downRight[i][j+1]);
                }
                downRight[i][j] = min == max ? min : min + 1;
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (m[i][j] == 1) {
                    int min1 = Math.min(upLeft[i][j], downLeft[i][j]);
                    int min2 = Math.min(upRight[i][j], downRight[i][j]);
                    ans[i][j] = Math.min(min1, min2);
                }
            }
        }
    }

    private boolean valid(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }

}
