package recursion;

/**
 * @author Joseph
 * @since 2020-01-29 16:17
 *
 * leetcode 74 medium
 *
 * Question Description:
 *  Write an efficient algorithm that searches for a value in an m x n matrix.
 *  This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Example 1:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * Example 2:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 *
 * Analysis:
 *  根据题目特性二分查找。0ms AC。
 *  这题本身不难，遍历矩阵可以 m*n 时间解决，如果面试中肯定会要求log(mn)甚至 m+n 的时间。
 *  m+n 的算法比这个还简单，就是定位到起始行最后一个元素，如果大于就找下一行，小于就倒着遍历此行。
 *
 * 时间复杂度：O(log(mn))
 * 空间复杂度：O(log(m) or log(n)) 将递归变成循环就是O(1)的空间复杂度
 */
public class Search2DMatrix {

    private int column = 0;

    private int[][] matrix = {
            {1,   3,  5,  7},
            {10, 11, 16, 20},
            {23, 30, 34, 50}
    };

    public static void main(String[] args) {
        Search2DMatrix search2DMatrix = new Search2DMatrix();
        boolean res = search2DMatrix.searchMatrix(search2DMatrix.matrix, 23);
        System.out.println(res);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (0 == matrix.length ||
            0 == matrix[0].length)
            return false;

        this.column = matrix[0].length;

        return recursiveBinarySearch(matrix, 0, (matrix.length * column) - 1, target);
    }

    private boolean recursiveBinarySearch(int[][] matrix, int left, int right, int target) {
        if (left == right) {
            return locate(matrix, left) == target;
        }
        if (left > right) return false;

        int pivot = (left + right) >> 1;
        int pivotValue = locate(matrix, pivot);

        if (target == pivotValue) return true;

        if (target > pivotValue) {
            left = pivot + 1;
        }
        else {
            right = pivot - 1;
        }
        return recursiveBinarySearch(matrix, left, right, target);
    }

    private int locate(int[][] matrix, int nums) {
        return matrix[nums / column][nums % column];
    }

}
