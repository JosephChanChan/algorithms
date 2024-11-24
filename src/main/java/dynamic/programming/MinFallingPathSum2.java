package dynamic.programming;

/**
 * lc 1289 hard
 *
 * Analysis：
 *  f(i,j)为i行j列下降路径的最小和
 * f(i,j)=min{f(i-1,k), k!=j}+a[i][j]
 * 边界：
 * f(i,j)=a[i][j], i==0
 *
 * 上面朴素的dp时间是O(n^3)，其实细心点可以发现每次都去枚举了上一步的每个状态，
 * 如果有办法直接得到上一步所有状态中最小的状态值就可以优化到O(n^2)
 *
 * 设g(j)是当前步的状态中除j列外的最小状态值
 * 当前步中肯定有最小值和次小值，如果j列就是最小值那么g(j)=次小值，
 * 否则g(j)=最小值
 *
 * 所以计算f(i,j)时只要记录i行的最小值、次小值就得到g(j)
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-14 16:30
 */
public class MinFallingPathSum2 {

    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        int[] g = new int[n];

        int[][] m = new int[2][2];
        m[0][0] = Integer.MAX_VALUE;
        m[1][0] = Integer.MAX_VALUE;
        // 计算第一行的最小值和次小值
        for (int j = 0; j < n; j++) {
            updateMin(arr[0][j], j, m);
        }
        // 初始化g(i)
        for (int j = 0; j < n; j++) {
            updateG(m, g);
        }

        int globalMin = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            m[0][0] = Integer.MAX_VALUE;
            m[1][0] = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                arr[i][j] += g[j];
                updateMin(arr[i][j], j, m);
                if (i == n-1) globalMin = Math.min(globalMin, arr[i][j]);
            }
            updateG(m, g);
        }
        return globalMin;
    }

    void updateMin(int n, int j, int[][] m) {
        if (m[0][0] > n) {
            int p = m[0][0];
            int q = m[0][1];
            m[0][0] = n;
            m[0][1] = j;
            n = p;
            j = q;
        }
        if (m[1][0] > n) {
            m[1][0] = n;
            m[1][1] = j;
        }
    }

    void updateG(int[][] m, int[] g) {
        for (int j = 0; j < g.length; j++) {
            if (j == m[0][1]) {
                g[j] = m[1][0];
            }
            else {
                g[j] = m[0][0];
            }
        }
    }
}
