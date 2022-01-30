package trees;

/**
 * lc 211 medium
 *
 * Analysis:
 *
 * 时间复杂度：加单词&搜明确的单词 O(length of word)
 *          通配符搜索 O(n) 最坏情况把全部单词都搜索了一遍
 * 空间复杂度：O(n) n个单词
 *
 * @author Joseph
 * @since 2021-02-04 00:03
 */
public class WordDictionary {

    TrieNode root ;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode(' ');
    }

    public void addWord(String word) {
        TrieNode[] childes = root.childes;
        char[] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (null == childes[c[i]-'a']) {
                childes[c[i]-'a'] = new TrieNode(c[i]);
                if (i == c.length-1) {
                    childes[c[i]-'a'].end = true;
                }
            }
            childes = childes[c[i]-'a'].childes;
        }
    }

    public boolean search(String word) {
        return dfs(word.toCharArray(), 0, root);
    }
    private boolean dfs(char[] c, int i, TrieNode node) {
        TrieNode[] childes = node.childes;
        for (int k = 0; k < childes.length; k++) {
            if (c[i] == '.' && null != childes[k]) {
                // 这里要注意下，为什么这里不能直接返回dfs的结果？
                if (i < c.length-1 && dfs(c, i+1, childes[k])) {
                    return true;
                }
                else if (i == c.length-1 && childes[k].end) {
                    return true;
                }
            }
            if (c[i] != '.' && null != childes[c[i]-'a']) {
                if (i == c.length-1) return childes[c[i]-'a'].end;
                // 这里可以直接返回dfs的结果？
                return dfs(c, i+1, childes[c[i]-'a']);
            }
        }
        return false;
    }

    private class TrieNode {
        char c ;
        boolean end = false;
        TrieNode[] childes;

        public TrieNode(char c) {
            this.c = c;
            this.childes = new TrieNode[26];
        }
    }
}
