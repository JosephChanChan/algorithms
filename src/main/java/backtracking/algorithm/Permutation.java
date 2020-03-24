package backtracking.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joseph
 * @since 2020-03-23 23:36
 *
 * leetcode 46 medium
 *
 * Given a collection of distinct integers, return all possible permutations.
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * Analysis:
 *  递归回溯法。因为是全排列，所以没有剪枝。
 *  PS: 其实这题就是之前做过的 FullPermutation
 *  时间复杂度：O(A(n,n)) A(n,m)是排列公式。
 *  空间复杂度：O(A(n,n)) 因为用到额外容器保存所有的排列结果
 */
public class Permutation {


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        Permutation permutation = new Permutation();
        List<List<Integer>> permute = permutation.permute(nums);
        permute.forEach(list -> {
            list.forEach(element -> {
                System.out.print(element + " ");
            });
            System.out.println();
        });
    }


    private List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> permutationList = new ArrayList<>();
        recursion(nums, 0, list, permutationList);
        return permutationList;
    }

    private void recursion(int[] nums, int level, List<Integer> list, List<List<Integer>> permutationList) {
        if (level == nums.length) {
            permutationList.add(new ArrayList<>(list));
            return;
        }

        for (int i = level; i < nums.length; i++) {
            swap(nums, level, i);
            list.add(nums[level]);
            recursion(nums, level+1, list, permutationList);
            swap(nums, level, i);
            list.remove(level);
        }
    }

    private void swap(int[] nums, int level, int j) {
        int temp = nums[level];
        nums[level] = nums[j];
        nums[j] = temp;
    }


}
