package stack;

import java.util.*;

/**
 * lc 85 hard
 *
 * Analysis:
 *  整体还是用 lc 84的思想，只不过题目没有那么直观了。
 * 试想将第i行作为矩形的底，只用考虑第0~i行的矩阵图，第i行第j列如果是0，则将这一列高度视为0。
 * 依次计算每一行，当前行作为矩形底时，从0~i行的每一列的高度看作是柱子的高度，转化为 lc 84。
 * 就可以用单调栈解决。
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2021-02-01 20:59
 */
public class MaxAreaOfRectangle {

    int n, m ;
    int[][] heights ;

    public int maximalRectangle(char[][] matrix) {
        n = matrix.length;
        if (n == 0) return 0;
        m = matrix[0].length;
        if (m == 0) return 0;
        heights = new int[n][m];

        /*
            h(i,j) =  if a[i][j] == 1
                        h(i-1,j)+a[i][j]
                      else
                        0
         */

        for (int j = 0; j < m; j++) heights[0][j] = matrix[0][j] - '0';
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                heights[i][j] = matrix[i][j]-'0' == 0 ? 0 : heights[i-1][j] + 1;
            }
        }

        // 单调栈存每列下标
        int area = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 当前遍历到第i行
            for (int j = 0; j < m; j++) {
                int h = heights[i][j];
                while (!s.isEmpty() && heights[i][s.peek()] > h) {
                    // 计算左右边界宽度
                    int column = s.pop();
                    // 右边界-左边界-1
                    int w = j - (s.isEmpty() ? -1 : s.peek()) - 1;
                    area = Math.max(area, heights[i][column] * w);
                }
                s.push(j);
            }
            // 计算剩余单调栈元素
            int r = m;
            while (!s.isEmpty()) {
                int c = s.pop();
                int h = heights[i][c];
                int w = s.isEmpty() ? r : r - s.peek() - 1;
                area = Math.max(area, h * w);
            }
        }
        return area;
    }
}
