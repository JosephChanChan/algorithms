package simulation.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 54 medium
 *
 * Analysis:
 *  模拟旋转过程
 *
 * 时间复杂度：O(n*m)
 * 空间复杂度：O(n*m)
 *
 * @author Joseph
 * @since 2021-07-03 21:30
 */
public class SpiralMatrix {

    boolean[][] vis ;
    int[][] d = new int[][]{{1,0}, {0,1}, {-1,0}, {0,-1}};

    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        vis = new boolean[n][m];

        List<Integer> ans = new ArrayList(n*m);

        int x = 0, y = 0, k = 0;
        ans.add(matrix[y][x]);
        vis[y][x] = true;

        int banned = 0;
        while (banned < 4) {
            if ((x+d[k][0]) >= 0 && (x+d[k][0]) < m && (y+d[k][1]) >= 0 && (y+d[k][1]) < n && !vis[y+d[k][1]][x+d[k][0]]) {
                banned = 0;
                x += d[k][0];
                y += d[k][1];
                //System.out.println(y+" "+x);
                ans.add(matrix[y][x]);
                vis[y][x] = true;
            }
            else {
                banned++;
                //System.out.println("qie");
                k = (k+1 == d.length ? 0 : k+1);
            }
        }
        return ans;
    }
}
