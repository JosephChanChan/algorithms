package binary.search;

/**
 * lc 240 medium & 剑指Offer 4
 *
 * <p>
 * Question Description:
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 * Consider the following matrix:
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 * <p>
 *
 * Analysis:
 * 分治递归。
 * 根据题目特性，从每一个矩阵开始，算出一个中间列 Xi，从中间列遍历找一个 Yi-1 < target < Yi 的数字确定行。
 * 得到一个中枢点 P(Xi, Yi)。
 * 这个P点的后面及下面的子矩阵全部都是 > target 的称 bottom-right 丢弃。
 * P2(Xi, Yi-1)自己及上面的和它左边的全都是 < target 的称 top-left 丢弃。
 * 所以实际搜索区域就剩下 bottom-left、top-right 2个区域。
 * 对2个区域递归做以上步骤，直到找到或触碰到边界。
 *
 * 时间复杂度：仔细分析会发现每次都淘汰大约一般元素，所以是O(log(mn))，m行数，n列数
 * 空间复杂度：递归深度*每次递归所需的空间，O(log(m) or log(n))
 *
 * @author Joseph
 * @since 2020-01-28 16:38
 */
public class Search2DMatrix2 {

    private int[][] matrix = {
            {1,  4,  7,  11, 15},
            {2,  5,  8,  12, 19},
            {3,  6,  9,  16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
    };

    private int target = 0;

    public static void main(String[] args) {
        Search2DMatrix2 search2DMatrix2 = new Search2DMatrix2();
        boolean result = search2DMatrix2.searchMatrix(search2DMatrix2.matrix, 15);
        System.out.println(result);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        this.target = target;
        int x1 = 0,
            x2 = matrix[x1].length - 1,
            y1 = 0,
            y2 = matrix.length - 1;
        return divideSearch(x1, x2, y1, y2);
    }

    private boolean divideSearch(int x1, int x2, int y1, int y2) {
        if (x1 > x2 || y1 > y2) return false;
        if (x1 == x2 && y1 == y2) return matrix[y1][x1] == target;
        // 只有一列或一行，直接遍历搜索
        if (x1 == x2) return columnSearch(x1, y1, y2);
        if (y1 == y2) return rowSearch(y1, x1, x2);

        // 找到中间列
        int midColumn = (x2 + x1) >> 1;
        // 从中间列遍历找到一个 Yi-1 < target < Yi
        int pivot = -1;
        for (int i = y1; i <= y2; i++) {
            if (matrix[i][midColumn] == target) return true;
            if (matrix[i][midColumn] > target) {
                pivot = i;
                break;
            }
        }
        // 中间列全部 > target，只搜右边区域
        if (pivot == -1) {
            return divideSearch(midColumn + 1, x2, y1, y2);
        }

        // 先搜索 bottom-left 区域
        int bottomX1 = x1,
            bottomX2 = midColumn - 1,
            bottomY1 = pivot,
            bottomY2 = y2;
        if (divideSearch(bottomX1, bottomX2, bottomY1, bottomY2)) {
            return true;
        }

        // 搜索 top-right 区域
        int topX1 = midColumn + 1,
            topX2 = x2,
            topY1 = y1,
            topY2 = pivot - 1;
        return divideSearch(topX1, topX2, topY1, topY2);
    }

    private boolean columnSearch(int column, int y1, int y2) {
        for (int i = y1; i <= y2; i++) {
            if (matrix[i][column] == target) {
                return true;
            }
        }
        return false;
    }

    private boolean rowSearch(int row, int x1, int x2) {
        for (int i = x1; i <= x2; i++) {
            if (matrix[row][i] == target) {
                return true;
            }
        }
        return false;
    }


}
