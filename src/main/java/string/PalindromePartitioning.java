package string;

import java.util.*;

/**
 * @author Joseph
 * @since 2020-05-24 10:30
 *
 * Question Description:
 *  参见 lc 131
 *
 * Analysis:
 *  个人理解 递归 + 回溯 = dfs，递归 + 回溯 + 剪枝 = good dfs
 *
 * 时间复杂度：小于 O(2^n)
 * 空间复杂度：O(2^n)，和所有的合法回文子串有关，最坏的情况就是s全部都是相同的字符如: aaa...aa 这种，有n个相同字符。
 *  那么所有的切割方案都是可行的，无论怎么切都是合法的回文子串。对于每个字符都有2种可能，切与不切。
 *  所以会产生2^n个切割方案，即会产生2^n个合法回文子串，就需要2^n空间存储。
 */
public class PalindromePartitioning {


    public List<List<String>> partition(String s) {
        List<List<String>> allPartitioning = new LinkedList<>();

        char[] sequence = s.toCharArray();
        boolean[][] palindromes = calcAllPalindrome(sequence);
        calcPartitioning(s, palindromes, 0, new ArrayList<>(), allPartitioning);
        return allPartitioning;
    }

    /*
        枚举从第一个位置开始切，第二个位置开始切...一直到第n-1个位置开始切
        下一个切割点的枚举就要从上一个的位置下一个字符开始
        每个切割点都代表以某一个回文串为开头，组合起后面所有字符可能的回文串。
     */
    private void calcPartitioning(String s, boolean[][] palindromes, int slot,
                                  List<String> partition, List<List<String>> allPartitioning) {
        if (slot == s.length()) {
            allPartitioning.add(new ArrayList<>(partition));
            return;
        }

        int i ;
        for (i = slot; i < s.length(); i++) {
            if (i != slot && !palindromes[slot][i]) {
                continue;
            }
            partition.add(s.substring(slot, i+1));
            calcPartitioning(s, palindromes, i+1, partition, allPartitioning);
            partition.remove(partition.size()-1);
        }
    }

    /** O(n^2) 找到字符串中所有回文子串 */
    private boolean[][] calcAllPalindrome(char[] sequence) {
        int n = sequence.length;
        boolean[][] palindrome = new boolean[n][n];

        // calc odd palindrome
        int i, left, right ;
        for (i = 0; i < n; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < n) {
                if (sequence[left] != sequence[right]) {
                    break;
                }
                palindrome[left][right] = true;
                left--;
                right++;
            }
        }

        // calc even palindrome
        for (i = 0; i < n; i++) {
            left = i;
            right = i + 1;
            while (left >= 0 && right < n) {
                if (sequence[left] != sequence[right]) {
                    break;
                }
                palindrome[left][right] = true;
                left--;
                right++;
            }
        }
        return palindrome;
    }
}
