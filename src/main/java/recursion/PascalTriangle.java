package recursion;

import java.util.*;

/**
 * @author Joseph
 * @since 2019-09-03 17:37
 *
 * Question Description:
 *  Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *  In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *  Example:
 *  Input: 5
 *  Output:
 *  [
 *       [1],
 *      [1,1],
 *     [1,2,1],
 *    [1,3,3,1],
 *   [1,4,6,4,1]
 *  ]
 *
 * Analysis:
 * 1. 找递归关系，根据题目所给的每个数字生成方法，很容易可知，
 * 设 f(i,j) 为第i行，第j列的数字，则 f(i,j) = f(i-1,j-1) + f(i-1,j)
 *
 * 2. 确定递归的边界，即base case。
 * 最左边和最右边都是1，则 f(i,j) = 1 , i = 1 or i == j
 *
 * 第1种递归解法使用了备忘录算法，可以使递归树剪枝，去除了所有的重复计算，
 * 时间复杂度：O(row^2)
 * 空间复杂度：O(row^2)
 *
 * 第2种使用自底向上的递推解法，就是大名鼎鼎的动态规划啦。
 * 因为已经给出状态转移方程，很简单就能写出代码。
 * 时间复杂度：O(row^2)
 * 空间复杂度：O(2row)
 */
public class PascalTriangle {


    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();
        /*List<List<Integer>> triangle = pascalTriangle.generate(5);
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j) + " ");
            }
            System.out.println();
        }*/
        List<Integer> list = pascalTriangle.dynamicGenerate(4);
        list.forEach(integer -> System.out.print(integer + " "));
    }

    private List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>(numRows);
        if (numRows == 1) {
            List<Integer> row = new ArrayList<>(1);
            row.add(1);
            pascalTriangle.add(row);
            return pascalTriangle;
        }

        Map<String, Integer> box = new HashMap<>(numRows * numRows);
        for (int i = 1; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>(i);
            for (int c = 1; c <= i; c++) {
                row.add(generateNum(i, c, box));
            }
            pascalTriangle.add(row);
        }

        return pascalTriangle;
    }

    /* 递归备忘录算法 */
    private int generateNum(int row, int column, Map<String, Integer> box) {
        if (1 == row ||
            1 == column ||
            row == column) {
            return 1;
        }
        Integer value = box.get(row + "," + column);
        if (null != value) {
            return value;
        }
        int left = generateNum(row - 1, column - 1, box);
        int right = generateNum(row - 1, column, box);
        box.putIfAbsent(row + "," + column, left + right);
        return left + right;
    }

    /* 动态规划解法 */
    private List<Integer> dynamicGenerate(int row) {
        if (0 == row) {
            List<Integer> result = new ArrayList<>(row);
            result.add(1);
            return result;
        }
        else if (1 == row) {
            List<Integer> result = new ArrayList<>(row);
            result.add(1);
            result.add(1);
            return result;
        }
        // lc 输入从0开始算作1行，这里+1扩大数组容量
        row = row + 1;
        Integer[] arr = new Integer[row];
        Integer[] cur = new Integer[row];
        arr[0] = 1; arr[1] = 1;
        // 从第3行开始计算，第1、2行都是固定的值
        for (int i = 2; i < row; i++) {
            for (int j = 0; j <= i; j++) {
                if (j <= 0 ||
                    i == j) {
                    cur[j] = 1;
                    continue;
                }
                int v1 = getValue(arr, i - 1, j - 1);
                int v2 = getValue(arr, i - 1, j);
                cur[j] = v1 + v2;
            }
            // 使用了2个数组存储计算出来的值，上一行与本行
            if (i != row-1) {
                System.arraycopy(cur, 0, arr, 0, arr.length);
            }
        }
        return Arrays.asList(cur);
    }

    private int getValue(Integer[] arr, int row, int column) {
        if (row <= 0 ||
            column <= 0 ||
            row == column) {
            return 1;
        }
        return arr[column];
    }





}
