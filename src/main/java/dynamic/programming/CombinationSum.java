package dynamic.programming;

import sort.QuickSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Joseph
 * @since 2019-10-06 22:26
 *
 * leetcode 39 medium
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
 *
 *
 */
public class CombinationSum {

    public static void main(String[] args) {
        String test = "223";
        String[] split = test.split("");
        System.out.println(split.length);
    }


    /*
        f(t) = f(t-c[i1]) + ... + f(t-c[in])
        border:
        f(t) = 0, t < 0;
        f(t) = 1, t = 0;
     */

    private HashSet<Integer> borderInt = new HashSet<>();

    private HashMap<Integer, CombinationPlan> memory ;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (target == 0) return new ArrayList<>();

        memory = new HashMap<>(candidates.length);

        for (int candidate : candidates) {
            borderInt.add(candidate);
        }

        for (int i = 1; i <= target; i++) {

            CombinationPlan combinationPlan = new CombinationPlan();
            HashSet<String> additionList = new HashSet<>();
            List<List<Integer>> elementList = new ArrayList<>();
            combinationPlan.setAdditionList(additionList);
            combinationPlan.setElementList(elementList);

            for (int j = 0; j < candidates.length; j++) {
                // 2 3 6 7

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
                    list += candidates[j];
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
        String[] split = newList.split("");
        List<Integer> list = new ArrayList<>();
        for (String item : split) {
            list.add(Integer.parseInt(item));
        }
        return list;
    }

    private String sortList(String list) {
        String[] split = list.split("");
        Integer[] convert = convert(split);
        QuickSort.doQuickSort(convert, 0, convert.length - 1);
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
            array += split[i];
        }
        return array;
    }

    private int calc(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        CombinationPlan combinationPlan = memory.get(n);
        return combinationPlan.getAdditionList().size();
    }




    private class CombinationPlan {
        private HashSet<String> additionList;//                 升序的加法链
        private List<List<Integer>> elementList;//              升序的元素


        public int haveValue() {
            if (null == elementList) return 0;
            return elementList.size();
        }

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
    }









}
