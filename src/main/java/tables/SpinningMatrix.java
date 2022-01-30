package tables;

/**
 * lc 48 medium
 *
 * Analysis：
 * 还是基于规律的数学分析题，先找到第i行j列旋转90度会到 倒数第i列j行的规律。
 * 基于这个规律推导公式
 *
 *  时间复杂度：数学公式 O(n^2)   翻转 O(n^2)
 *  空间复杂度：数学公式 O(1)     翻转 O(1)
 * @author Joseph
 * @since 2021-02-28 14:06
 */
public class SpinningMatrix {


    /*
        数学分析法。先找到第i行j列旋转90度会到 倒数第i列j行的规律
        根据规律推导出公式 m(i,j) 旋转90度 -> m(j,n-i-1)
        继续推导，第j行n-1-1列继续旋转90度会到哪个位置？代入公式
        m(j,n-i-1) -> m(n-i-1,n-j-1)
        m(n-i-1,n-j-1) -> m(n-j-1, i)
        m(n-j-1, i) -> m(i,j)
        所以根据上面推导，如果从(i,j)位置开始旋转90度，每次旋转后得到位置坐标
        m(i, j) -> m(j, n-i-1) -> m(n-i-1, n-j-1) -> m(n-j-1, i) -> m(i, j)

        根据上面的公式现在可以做到给一个点(i,j)旋转4次解决4个位置的数。
        一共要枚举多少个点？共有n^2个点，一次枚举解决4个点，需要枚举n^2/4个位置
    */
    void mathAnalysis(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < (n>>1); i++) {
            for (int j = 0; j < (n+1>>1); j++) {
                int old = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = old;
            }
        }
    }

    void rotate(int[][] matrix) {
        int n = matrix.length;

        /*
            m(i,j) -> m(n-i-1,j) -> m(j, n-i-1)
        */
        // 上下水平翻转
        for (int i = 0; i < (n>>1); i++) {
            for (int j = 0; j < n; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n-i-1][j];
                matrix[n-i-1][j] = t;
            }
        }
        // 左对角线为轴翻转
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}
