package depth.first.search;

import java.util.*;

/**
 * @author Joseph
 * @since 2019-10-06 22:26
 *
 * lc 39 medium
 *
 * Question Description:
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * Analysis:
 * 其实这题在lc上是回溯标签的题，但是我印象中有一道找钱的dp题和这个题目类似，就想或许能用dp解决。
 * 给定一个正整数数组，用数组中的数字(可以重复选同一个数字)做加法运算凑出一个给定的目标数字。
 * 设 f(i) 为用给定数字凑成的方案数。举例：2,3,5，t=8
 * f(i)这个状态是如何从上一个状态走过来的？
 * f(i)的上一个状态显然不是简单的 f(i-1)，f(8)表示凑成8块钱的方案数和f(7)没什么关系，
 * 因为 7 + [2,3,5] 显然不等于8，所以f(8)的上一个状态和f(6) f(5) f(3)有关。
 * f(6)表示有i个方案可以凑成6块钱，那么再加2块钱就可以凑成8快，同理f(5) f(3)
 * 那么就得到状态转移方程：
 *      f(i) = f(i-c[i1]) + ... + f(i-c[in])
 * 其中 c[i1] 表示给定的正整数数组的第一个元素，一直到 c[in]
 * 边界：
 *      f(i) = 0, i < 0
 *      f(i) = 1, i = 0
 * ok，dp的算法有了，但是这个算法有问题，会重复计算方案数，例如你用Example1 计算下 f(5)会发现有2个加法方案：
 * 2+3 和 3+2 这2个属于不同的排列但是同一个组合，按照题目要求需要排除。
 * 感觉这应该才是题目的难点，去重统计。
 * 我的算法很笨，没有想到好方法，只能找到每一个状态的所有加法链去重统计。
 * 最后在lc上ac了，但是比 95% 的提交都要慢... 应该就是去重没做好，或者dp的方程不是最优的。
 *
 * 时间复杂度：O(target * candidates.length)
 * 空间复杂度：O(target)
 *
 * 2020-05-31 有空来更新下DFS的做法
 *  DFS，搜索每个数字开头的和为t的方案。
 * 注意：1. 数字可重复选。2. 组合方案要唯一，如 t=7 [2,2,3]等价[2,3,2]
 *
 * 时间复杂度：不好估计
 * 空间复杂度：解的个数
 */
public class CombinationSum {

    /*
        正解：DFS，搜索每个数字开头的和为t的方案。
        注意：1. 数字可重复选。2. 组合方案要唯一，如 t=7 [2,2,3]等价[2,3,2]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> solutions = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<>(), solutions);
        return solutions;
    }

    private void dfs(int[] candidates, int target, int index, int sum,
                     List<Integer> solution, List<List<Integer>> solutions) {
        if (sum == target) {
            solutions.add(new ArrayList<>(solution));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) return;

            solution.add(candidates[i]);
            // 搜索所有以 i 开头的和为t的数字组合方案
            dfs(candidates, target, i, sum + candidates[i], solution, solutions);
            solution.remove(solution.size() - 1);
        }
    }



    /*
        以下为DP解法，DP不适合做这种求全部解的题目
        f(t) = f(t-c[i1]) + ... + f(t-c[in])
        border:
        f(t) = 0, t < 0;
        f(t) = 1, t = 0;
     */

    /*private HashMap<Integer, CombinationPlan> memory ;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (target == 0) return new ArrayList<>();

        memory = new HashMap<>(candidates.length);

        for (int i = 1; i <= target; i++) {

            CombinationPlan combinationPlan = new CombinationPlan();
            HashSet<String> additionList = new HashSet<>();
            List<List<Integer>> elementList = new ArrayList<>();
            combinationPlan.setAdditionList(additionList);
            combinationPlan.setElementList(elementList);

            for (int j = 0; j < candidates.length; j++) {
                int prevI = i - candidates[j];

                // 负数不计算
                if (prevI < 0) continue;

                // 初始化数就加上他自己
                if (prevI == 0) {
                    additionList.add(String.valueOf(candidates[j]));
                    List<Integer> itemList = new ArrayList<>();
                    itemList.add(candidates[j]);
                    elementList.add(itemList);
                    continue;
                }

                // 拿出所有加法链
                CombinationPlan prevPlan = memory.get(prevI);
                HashSet<String> prevAdditionList = prevPlan.getAdditionList();
                for (String list : prevAdditionList) {
                    // 接上本次的数字后，排序，再放入容齐中去重，统计出第i个数字的加法方案
                    list += "+" +candidates[j];
                    String newList = sortList(list);
                    // 如果加法链之前不存在
                    if (additionList.add(newList)) {
                        elementList.add(convert(newList));
                    }
                }
            }

            memory.put(i, combinationPlan);
        }

        return memory.get(target).getElementList();
    }

    private List<Integer> convert(String newList) {
        String[] split = newList.split("\\+");
        List<Integer> list = new ArrayList<>();
        for (String item : split) {
            list.add(Integer.parseInt(item));
        }
        return list;
    }

    private String sortList(String list) {
        String[] split = list.split("\\+");
        Integer[] convert = convert(split);
        Arrays.sort(convert);
        *//*QuickSort.doQuickSort(convert, 0, convert.length - 1);*//*
        return convert(convert);
    }

    private Integer[] convert(String[] split) {
        Integer[] array = new Integer[split.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(split[i]);
        }
        return array;
    }

    private String convert(Integer[] split) {
        String array = "";
        for (int i = 0; i < split.length; i++) {
            array += +split[i] + "+";
        }
        return array.substring(0, array.lastIndexOf("+"));
    }

    private class CombinationPlan {
        private HashSet<String> additionList;//                 升序的加法链
        private List<List<Integer>> elementList;//              升序的元素

        public HashSet<String> getAdditionList() {
            return additionList;
        }

        public void setAdditionList(HashSet<String> additionList) {
            this.additionList = additionList;
        }

        public List<List<Integer>> getElementList() {
            return elementList;
        }

        public void setElementList(List<List<Integer>> elementList) {
            this.elementList = elementList;
        }
    }*/

}
