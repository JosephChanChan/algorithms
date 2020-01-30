package backtracking.algorithm;

import java.util.*;

/**
 * 蓝桥杯省赛的一题
 *
 *  * 某财务部门结账时发现总金额不对头。很可能是从明细上漏掉了某1笔或几笔。
 *  * 如果已知明细账目清单，能通过编程找到漏掉的是哪1笔或几笔吗？
 *  * 如果有多种可能，则输出所有可能的情况。
 *  * 我们规定：用户输入的第一行是：有错的总金额。
 *  * 接下来是一个整数n，表示下面将要输入的明细账目的条数。
 *  * 再接下来是n行整数，分别表示每笔账目的金额。
 *  *
 *  * 要求程序输出：所有可能漏掉的金额组合。每个情况1行。金额按照从小到大排列，中间用空格分开。
 *  * 比如：
 *  * 用户输入：
  	6
  	5
  	3
  	2
  	4
  	3
  	1
 * 表明：有错的总金额是6；明细共有5笔。
 * 此时，程序应该输出：
 *     1 3 3
 *     1 2 4
 *     3 4
 *     为了方便，不妨假设所有的金额都是整数；每笔金额不超过1000，金额的明细条数不超过100。
 *
 * 时间复杂度：有了回溯剪枝，应该不会到 O(2^n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2019-11-12 18:30
 */
public class CalculateBill {

    static int errorAmount, count, targetAmount ;
    static List<Integer> billList = new ArrayList<>();
    static List<List<Integer>> resultList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        errorAmount = scanner.nextInt();
        count = scanner.nextInt();
        int realAmount = 0;
        for (int i = 0; i < count; i++) {
            int k = scanner.nextInt();
            billList.add(k);
            realAmount += k;
        }

        targetAmount = realAmount - errorAmount;

        recursiveCalc(0, 0, new ArrayList<>());

        HashSet<String> resultStr = new HashSet<>();
        for (List<Integer> list : resultList) {
            TreeSet treeSet = new TreeSet();
            for (Integer value : list) {
                treeSet.add(value);
            }

            StringBuilder builder = new StringBuilder();
            Iterator iterator = treeSet.iterator();
            while (iterator.hasNext()) {
                builder.append(iterator.next()+" ");
            }
            builder.deleteCharAt(builder.length()-1);

            resultStr.add(builder.toString());
        }

        System.out.println(resultStr);
    }


    private static void recursiveCalc(int index, int accumulation, List<Integer> link) {
        for (int i = index; i < billList.size(); i++) {
            Integer iValue = billList.get(i);
            int temp = iValue + accumulation;

            if (temp == targetAmount) {
                // 找到一个解，记录后，再继续搜寻下一个解
                link.add(iValue);
                List<Integer> list = new ArrayList<>(link);
                resultList.add(list);

                link.remove(iValue);
            }
            else if (temp > targetAmount) {
                // 当前数字不满足，抛弃，继续在本层搜寻
                continue;
            }
            else {
                // 去选择下一个数字
                link.add(iValue);
                recursiveCalc(i + 1, temp, link);
                // 回溯本层选择的数字，继续选择下一个数字
                link.remove(iValue);
            }
        }
    }



}
