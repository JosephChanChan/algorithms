package disjoint.set;


/**
 * leetcode 200 medium
 *
 * Question Description:
 *  参见 lc 200
 *
 * Analysis:
 *  这题用dfs, bfs都可以做，并查集做起来不是很方便。但为了巩固并查集知识，就用了。
 * 时间复杂度：O(n*m)
 * 空间复杂度：O(n*m)
 *
 * @author Joseph
 * @since 2020-08-17 16:49
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'},
        };
        NumberOfIslands test = new NumberOfIslands();
        int i = test.numIslands(grid);
        System.out.println(i);
    }

    int[] parents ;
    int[] heights ;

    public int numIslands(char[][] grid) {
        int n = grid.length;
        if (n == 0) return 0;
        int m = grid[0].length;
        if (m == 0) return 0;

        parents = new int[n*m];
        heights = new int[n*m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                parents[i*m+j] = i*m+j;
            }
        }

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == '0') continue;
            if (i > 0 && grid[i-1][0] == '1') {
                int root1 = findRoot(i, 0, m);
                int root2 = findRoot(i-1, 0, m);
                union(root1, root2);
            }
        }

        for (int j = 0; j < m; j++) {
            if (grid[0][j] == '0') continue;
            if (j > 0 && grid[0][j-1] == '1') {
                int root1 = findRoot(0, j, m);
                int root2 = findRoot(0, j-1, m);
                union(root1, root2);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[i][j] == '0') continue;

                if (grid[i-1][j] == '1' && grid[i][j-1] == '1') {
                    int root1 = findRoot(i, j, m);
                    int root2 = findRoot(i-1, j, m);
                    union(root1, root2);
                    root1 = findRoot(i, j, m);
                    int root3 = findRoot(i, j-1, m);
                    union(root1, root3);
                }
                else if (grid[i-1][j] == '1') {
                    int root1 = findRoot(i, j, m);
                    int root2 = findRoot(i-1, j, m);
                    union(root1, root2);
                }
                else if (grid[i][j-1] == '1') {
                    int root1 = findRoot(i, j, m);
                    int root2 = findRoot(i, j-1, m);
                    union(root1, root2);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && parents[i*m+j] == i*m+j) count++;
            }
        }
        return count;
    }

    private int findRoot(int i, int j, int m) {
        int self = i*m+j;
        int root = parents[i*m+j];
        while (root != self) {
            self = root;
            root = parents[root];
        }
        return root;
    }

    private void union(int root1, int root2) {
        if (root1 == root2) return;
        if (heights[root1] == heights[root2]) {
            parents[root2] = root1;
            heights[root1]++;
        }
        else if (heights[root1] > heights[root2]) {
            parents[root2] = root1;
        }
        else {
            parents[root1] = root2;
        }
    }


}
