package tables;

import java.util.Arrays;

/**
 * @author Joseph
 * @since 2020-03-22 19:42
 *
 * leetcode 945 medium
 *
 * Question Description:
 *  Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
 * Return the least number of moves to make every value in A unique.
 * Example 1:
 * Input: [1,2,2]
 * Output: 1
 * Explanation:  After 1 move, the array could be [1, 2, 3].
 *
 * Example 2:
 * Input: [3,2,1,2,1,7]
 * Output: 6
 * Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 * Note:
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 *
 * Analysis:
 *  贪心策略。遍历使得数组中任意i和i-1相差1
 *  时间复杂度：O(nlogn)
 *  空间复杂度：O(1)
 */
public class MinimumIncrement2MakeArrayUnique {

    public static void main(String[] args) {
        int[] A = {3,2,1,2,1,7};
        MinimumIncrement2MakeArrayUnique unique = new MinimumIncrement2MakeArrayUnique();
        int i = unique.minIncrementForUnique(A);
        System.out.println(i);
    }

    public int minIncrementForUnique(int[] A) {
        if (A.length < 2) return 0;
        Arrays.sort(A);

        int moves = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i])
                continue;
            if (A[i - 1] == A[i]) {
                A[i] = ++A[i];
                moves++;
            }
            else {
                int diff = Math.abs(A[i-1] - A[i]);
                for (int j = 0; j <= diff; j++) {
                    A[i] += 1;
                    moves += 1;
                }
            }
        }
        return moves;
    }


}
