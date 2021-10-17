package binary.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * lc 302 hard
 *
 * Analysis:
 *
 * 时间复杂度：二分 O(n*log(m) + m*log(n))
 * 空间复杂度：二分 O(1)
 *
 * @author Joseph
 * @since 2021-04-10 19:45
 */
public class SmallestRectangleEnclosingBlackPixels {

    public int minArea(char[][] image, int x, int y) {
        return bs(image, x, y);
    }

    int bs(char[][] a, int row, int column) {
        /*
            用二分来找上边界和下边界，同理左右边界
            以找左边界为例：
            原始起点[i, j]，即i行j列
            左边界查找范围应在[0~j]，因为右边界是j，能确保图像在搜索范围内
            0~j 选出中点m作为中间列，从顶部往下搜第一个1，找到后代表什么？
            代表m列有可能是左边界，因为比之前更靠左了。继续搜左边找更靠左的1的列。
            如果在m列，上到下搜完，没找到1。代表左边界在右侧，继续搜右边。

            就这样每次通过O(n)时间从上到下搜1，用log(m)时间确定边界，总共需要n*log(m) 找到一个边界。
        */
        int left = leftRightBoundary(0, column, a, true);
        int right = leftRightBoundary(column, a[0].length-1, a, false);
        int top = topDownBoundary(0, row, a, true);
        int down = topDownBoundary(row, a.length-1, a, false);

        return (Math.abs(left-right)+1) * (Math.abs(top-down)+1);
    }

    int leftRightBoundary(int l, int r, char[][] a, boolean left) {
        while (l + 1 < r) {
            int m = (l+r)>>1, i = 0;
            for ( ; i < a.length; i++) {
                if (a[i][m] == '1') break;
            }
            if (left) {
                if (i < a.length) {
                    r = m;
                }
                else {
                    l = m;
                }
            }
            else {
                if (i < a.length) {
                    l = m;
                }
                else {
                    r = m;
                }
            }
        }
        if (left) {
            for (int i = 0; i < a.length; i++) if (a[i][l] == '1') return l;
            return r;
        }
        else {
            for (int i = 0; i < a.length; i++) if (a[i][r] == '1') return r;
            return l;
        }
    }

    int topDownBoundary(int top, int down, char[][] a, boolean up) {
        while (top + 1 < down) {
            int m = (top+down)>>1, i = 0;
            for ( ; i < a[0].length; i++) {
                if (a[m][i] == '1') break;
            }
            if (up) {
                if (i < a[0].length) {
                    down = m;
                }
                else {
                    top = m;
                }
            }
            else {
                if (i < a[0].length) {
                    top = m;
                }
                else {
                    down = m;
                }
            }
        }
        if (up) {
            for (int i = 0; i < a[0].length; i++) if (a[top][i] == '1') return top;
            return down;
        }
        else {
            for (int i = 0; i < a[0].length; i++) if (a[down][i] == '1') return down;
            return top;
        }
    }

    // O(nm) 28min 勉强AC 13ms faster than 6%
    int bfs(char[][] a, int x, int y) {
        int up = Integer.MAX_VALUE, down = Integer.MIN_VALUE, left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;

        int n = a.length, m = a[0].length;

        int[][] d = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] vis = new boolean[n][m];
        Queue<Integer[]> q = new LinkedList();

        vis[x][y] = true;
        q.add(new Integer[]{x, y});

        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                Integer[] coo = q.poll();

                up = Math.min(up, coo[0]);
                down = Math.max(down, coo[0]);
                left = Math.min(left, coo[1]);
                right = Math.max(right, coo[1]);

                for (int j = 0; j < 4; j++) {
                    int row = coo[0]+d[j][0];
                    int column = coo[1]+d[j][1];
                    if (row >= 0 && row < n && column >= 0 && column < m && !vis[row][column] && a[row][column] == '1') {
                        vis[row][column] = true;
                        q.add(new Integer[]{row, column});
                    }
                }
            }
        }
        return (Math.abs(up-down)+1) * (Math.abs((left-right))+1);
    }
}
