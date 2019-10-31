package trees;

import trees.AVLTree;

import java.util.*;

/**
 * @author Joseph
 * @since 2019-10-31 16:30
 *
 * Question Description:
 *  对于一个由一位十进制整数构成的二叉树，如果深度不超过4，可以用一个三位十进制整数构成的数组表示，具体规则如下：
 * 1、百位数表示树的层次L，1<=L<=4;十位数表示在该层次中的位置P，1<=P<=8;个位数表示数值V。
 * 2、数组里，L一定是单增的，也就是说后一个数的L大于等于前一个数的L；
 * 3、对于同一个L，P也是单增的，就是说在L不变的情况下，后一个数的P大于等于前一个数的P。
 * 例如：[ 113, 215, 221 ]
 *       3
 *     /    \
 *   5       1
 * 现在要求这个树所有到叶子节点的路径和，对于例子中，路径和为 （3+5）+（3+1）=12。
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 */
public class Num2TreeSum {


    public static void main(String[] args) {
        /*
            一层                      5
            二层          4                         8
            三层    2         4               7            9
            四层 1    3    4     5        6      7     8      9
        */
        int[] arr = new int[]{115, 214, 228, 312, 324, 337, 349, 411, 423, 434, 445, 456, 467, 478, 489};
        Map<Integer, List<Integer>> treeMap = transfer2Mapping(arr);
        int sum = calcPathSum(treeMap);
        System.out.println(sum);
    }

    private static Map<Integer, List<Integer>> transfer2Mapping(int[] numList) {
        if (null != numList && numList.length > 0) {
            Map<Integer, List<Integer>> treeMap = new HashMap<>();
            List<Integer> curLevel ;
            for (Integer item : numList) {
                String str = String.valueOf(item);
                char[] chars = str.toCharArray();
                int level = Integer.parseInt(String.valueOf(chars[0]));
                int value = Integer.parseInt(String.valueOf(chars[2]));
                if (null == (curLevel = treeMap.get(level))) {
                    curLevel = new ArrayList<>();
                    treeMap.put(level, curLevel);
                }
                curLevel.add(value);
            }
            return treeMap;
        }
        return null;
    }

    private static int calcPathSum(Map<Integer, List<Integer>> treeMap) {
        int sum = 0;
        // 遍历最底层每个元素，发起递归，计算每条路径和
        List<Integer> bottomLevel = treeMap.get(treeMap.size());
        for (int i = 0; i < bottomLevel.size(); i++) {
            sum += recursiveCalc(treeMap, treeMap.size(), i);
        }
        return sum;
    }

    private static int recursiveCalc(Map<Integer, List<Integer>> treeMap, Integer level, Integer index) {
        if (1 == level) {
            return treeMap.get(1).get(0);
        }
        int parentIndex = index >> 1;
        List<Integer> curLevel = treeMap.get(level);
        return curLevel.get(index) + recursiveCalc(treeMap, level - 1, parentIndex);
    }
}
