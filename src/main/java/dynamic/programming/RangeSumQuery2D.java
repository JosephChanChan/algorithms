package dynamic.programming;

/**
 * lc 304 medium
 *
 * Analysis:
 * 时间复杂度：dp区域和O(1) 前缀数组O(n)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2022/2/13 8:30 PM
 */
public class RangeSumQuery2D {

    int[][] s ;
    int[][] f ;

    public RangeSumQuery2D(int[][] matrix) {
        buildDP(matrix);
    }

    public void buildPrefixSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        s = new int[n][m];
        for (int i = 0; i < n; i++) {
            s[i][0] = matrix[i][0];
            for (int j = 1; j < m; j++) {
                s[i][j] = s[i][j-1] + matrix[i][j];
            }
        }
    }

    public void buildDP(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        f = new int[n][m];
        // f(i,j)=f(i-1,j)+f(i,j-1)-f(i-1,j-1)
        f[0][0] = a[0][0];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i-1][0] + a[i][0];
        }
        for (int j = 1; j < m; j++) {
            f[0][j] = f[0][j-1] + a[0][j];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                f[i][j] = f[i-1][j] + f[i][j-1] - f[i-1][j-1] + a[i][j];
                //System.out.println("i="+i+" j="+j+" "+f[i][j]+" "+f[i-1][j]+" "+f[i][j-1]);
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp(row1, col1, row2, col2);
    }

    /**
     * 前缀和，时间O(n)
     */
    public int prefixSum(int row1, int col1, int row2, int col2) {
        // 计算从r1到r2，每一行的区间和，时间O(n)，n是行数
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            if (col1 == 0) {
                sum += s[i][col2];
                continue;
            }
            sum += s[i][col2] - s[i][col1-1];
        }
        return sum;
    }

    /**
     * 矩阵dp，时间O(1)
     * s(x, y, i, j)是计算左上角(x,y)和右下角(i,j)的矩阵面积
     * f(i,j)是左上角原点到右下角(i,j)的矩阵面积
     * f(i,j)=f(i-1,j)+f(i,j-1)-f(i-1,j-1)+m(i,j)
     * 边界：
     * f(0,0)=m(0,0)
     * f(0,j)=f(0,j-1)
     * f(i,0)=f(i-1,0)
     *
     * s(x,y,i,j)=f(i,j)-f(y-1,j)-f(i,x-1)+f(y-1,x-1)
     * 边界：
     * f(i,j)=0, i<0 or j<0
     *
     * 这题和之前一题矩阵DP类似，有相关经验就容易想到DP解法，这题本质是解决重复子问题
     */
    public int dp(int row1, int col1, int row2, int col2) {
        int rightTop = row1 - 1 < 0 ? 0 : f[row1-1][col2];
        int leftBut = col1 - 1 < 0 ? 0 : f[row2][col1-1];
        int leftTop = ((row1 - 1 < 0) || (col1 - 1 < 0)) ? 0 : f[row1-1][col1-1];
        //System.out.println("rt="+rightTop+" lb="+leftBut+" lt="+leftTop+" f="+f[row2][col2]);
        return f[row2][col2] - rightTop - leftBut + leftTop;
    }


}
