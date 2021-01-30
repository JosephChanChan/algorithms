package depth.first.search;

import java.util.*;

/**
 * @author Joseph
 * @since 2020-01-31 13:39
 *
 * leetcode 77 medium
 *
 * Question Description:
 *  Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * Example:
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * Analysis:
 *  一开始没想到避免重复的办法，写了个排列再去重，超时。
 *  后面发现枚举时只往后枚举，就可以避免重复。
 *
 *  时间复杂度：小于 O(n^k)
 *  空间复杂度：O(k)
 */
public class Combinations {

    private int n = 0, k = 0;
    private int[] mark;
    private Set<String> basket = new HashSet<>();
    private List<List<Integer>> combinationList = new ArrayList<>();

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        List<List<Integer>> combine = combinations.combine(12, 10);
        System.out.println(combinations.combinationList.size());
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        /*mark = new int[n+1];
        permutation(0, new ArrayList<>());*/
        combine(0, 1, new ArrayList<>());
        return combinationList;
    }

    /** AC */
    private void combine(int step, int head, List<Integer> list) {
        if (step == k) {
            combinationList.add(new ArrayList<>(list));
            return;
        }

        for (int i = head; i <= n; i++) {
            list.add(i);
            combine(step + 1, i + 1, list);
            list.remove(Integer.valueOf(i));
        }
    }

    /** Time Limit Exceed. Passing 23/27 test case. */
    private void permutation(int step, List<Integer> list) {
        if (step == k) {
            distinct(list);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!isValid(i)) continue;

            mark[i] = 1;
            list.add(i);
            permutation(step + 1, list);
            mark[i] = 0;
            list.remove(Integer.valueOf(i));
        }
    }

    private boolean isValid(int num) {
        return mark[num] == 0;
    }

    private void distinct(List<Integer> list) {
        Collections.sort(list);
        int size = list.size();
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sequence.append(list.get(i));
        }
        if (basket.add(sequence.toString())) {
            combinationList.add(new ArrayList<>(list));
        }
    }



}
