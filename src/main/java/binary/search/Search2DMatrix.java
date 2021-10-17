package binary.search;

/**
 * lc 74 medium
 *
 * Analysis:
 * 二维二分，先选定一个维度二分。这里根据特点选行。
 * t < matrix[m][0]代表m以及m+1后面的行都不存在t，反之亦然，可以淘汰一半行。
 * m+n 的算法比这个还简单，就是定位到起始行最后一个元素，如果大于就找下一行，小于就倒着遍历此行。
 *
 * 时间复杂度：O(log(n)+log(m))
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-01-29 16:17
 */
public class Search2DMatrix {

    int n, m;

    public boolean searchMatrix(int[][] matrix, int target) {
        n = matrix.length;
        m = matrix[0].length;

        if (n == 1 && m == 1) return matrix[0][0] == target;

        int p = 0, q = n-1, k ;
        while (p + 1 < q) {
            k = (p + q) >> 1;
            if (matrix[k][0] == target) return true;
            if (matrix[k][0] < target) {
                p = k;
            }
            else {
                q = k;
            }
        }
        int row = q;
        if (matrix[q][0] == target) return true;
        if (target < matrix[q][0]) row = p;
        // 在最终行找t
        int l = 0, r = m-1;
        while (l + 1 < r) {
            k = (l + r) >> 1;
            if (matrix[row][k] == target) return true;
            if (matrix[row][k] < target) {
                l = k;
            }
            else {
                r = k;
            }
        }
        if (matrix[row][l] == target) return true;
        if (matrix[row][r] == target) return true;
        return false;
    }

}
