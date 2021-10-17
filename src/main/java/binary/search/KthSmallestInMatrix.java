package binary.search;

/**
 * lc 378 medium
 *
 * Analysis:
 *  这题告诉我们，二维二分不一定都是以行或列维度进行二分，还可以是二分答案。
 * 首先要分析出题目性质，左上角是最小点min，右下角是max，设 min<=m<=max，所有小于等于m的数会在哪里分布？
 * 考虑单个m，m作为左上角它的右下部分全都是>=m的，把矩阵中所有m找出来会发现所有<=m的数会在每个m的左上部分。
 * 因此可以计算出每个m左上部分的个数判断和k的大小关系，可知m具有二分性。
 *
 * 时间复杂度：O(log(min~max) * n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-09-19 22:42
 */
public class KthSmallestInMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        if (k == 1) return matrix[0][0];
        if (k == (n*n)) return matrix[n-1][n-1];

        int l = matrix[0][0], r = matrix[n-1][n-1], m ;
        while (l + 1 < r) {
            m = (l + r) >> 1;
            /*
                在矩阵中有c个数<=m
                这里c == k的时候有点意思，该搜左边还是右边？
                假设猜的m是比第k个小的数即答案ans大的数，m和ans在矩阵中大于等于的数可能是一样的。
                若m<ans，则m在矩阵中大于等于的数的个数不可能达到k。
                所以当c == k，此时猜的m可能是答案，但是真正的ans可能在m的左边。
            */
            int c = check(m, matrix);
            if (c < k) {
                l = m;
            }
            else {
                r = m;
            }
        }
        // 这里也有点意思，若l==r，则返回任意一个都行，若l!=r则l和r之间必定有大小关系，第k小的数必定是其中一个
        if (check(l, matrix) == k) return l;
        return r;
    }

    int check(int m, int[][] a) {
        /*
            从左下角计算出<=m的个数
            当前点p<=m，累计个数，右移
            p>m，上移
        */
        int n = a.length, i = n-1, j = 0, sum = 0;
        while (i >= 0 && j < n) {
            if (a[i][j] <= m) {
                sum += (i + 1);
                j++;
            }
            else {
                i--;
            }
        }
        return sum;
    }
}
