package trees;

import java.util.*;

/**
 * @author Joseph
 * @since 2021-02-06 16:24
 */
public class PalindromePairs {

    public static void main(String[] args) {
        PalindromePairs t = new PalindromePairs();
        t.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"});
    }

    TrieNode root = new TrieNode(' ');
    List<Integer> copy = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        for (int i = 0; i < words.length; i++) {
            add(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            char[] c = words[i].toCharArray();
            find(c, i);
        }
        return ans;
    }

    void find(char[] c, int idx) {
        TrieNode node = root;
        for (int i = 0; i < c.length; i++) {
            if (null == node.childes[c[i]-'a']) {
                if (node.end && isPlindrome(c, i, c.length-1)) {
                    copy.add(idx); copy.add(node.idx);
                    ans.add(new ArrayList(copy));
                    copy.clear();
                    return;
                }
                return;
            }
            node = node.childes[c[i]-'a'];
        }
        if (node.end) {
            copy.add(idx); copy.add(node.idx);
            ans.add(new ArrayList(copy));
            copy.clear();
        }
        if (node.endWithSuffixes.size() > 0) {
            for (int j : node.endWithSuffixes) {
                copy.add(idx); copy.add(j);
                ans.add(new ArrayList(copy));
                copy.clear();
            }
        }
    }

    void add(String word, int idx) {
        char[] c = word.toCharArray();
        TrieNode node = root;
        for (int i = c.length-1; i >= 0; i--) {
            if (null == node.childes[c[i]-'a']) {
                node.childes[c[i]-'a'] = new TrieNode(c[i]);
            }
            node = node.childes[c[i]-'a'];
            // 检查 0~i-1 是否回文串
            if (i > 0 && isPlindrome(c, 0, i-1)) {
                node.endWithSuffixes.add(idx);
            }
        }
        node.idx = idx;
        node.end = true;
    }

    class TrieNode {
        char c ;
        // 该节点是一个单词的结尾，这个单词所在words下标
        int idx ;
        boolean end = false;
        TrieNode[] childes ;
        // 以某个后缀结尾的，剩下的前缀字符都是回文串的字符的words下标
        List<Integer> endWithSuffixes = new ArrayList<>();
        public TrieNode(char c) {
            this.c = c;
            this.childes = new TrieNode[26];
        }
    }

    boolean isPlindrome(char[] c, int l, int r) {
        while (l < r) {
            if (c[l++] != c[r--]) return false;
        }
        return true;
    }
}
