package trees;

/**
 * Trie树模板 lc 208 medium
 *
 * 简单的模板，用了数组存储节点的子集，因为这道题的数据范围是'a'~'z'
 * Trie树适合快速判断字符前缀，适合一个一个字符查找的场景，也就是Trie适合搜索单词和判断前缀场景。
 *
 * 对于字符串的查找，时间Trie树和Hash一致，空间比Hash更优。
 * 例如："a" "aa" "aaa" "aaaa" "aaaaa" Trie树存储5个节点，Hash存储全部字符串
 *
 * @author Joseph
 * @since 2021-01-30 21:00
 */
public class TrieTree {

    TrieNode dummy ;

    /** Initialize your data structure here. */
    public TrieTree() {
        dummy = new TrieNode(' ');
    }

    /**
     * 依次将每个字符插入Trie树。
     * 在每个节点的子集中看当前字符是否已存在。
     *
     * @param word word
     */
    public void insert(String word) {
        if (word.length() == 0) return;

        TrieNode node = dummy;
        char[] c = word.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (null == node.childes[c[i]-'a']) {
                node.childes[c[i]-'a'] = new TrieNode(c[i]);
            }
            node = node.childes[c[i]-'a'];
        }
        node.end = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word.length() == 0) return false;
        return doSearch(word, true);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix.length() == 0) return false;
        return doSearch(prefix, false);
    }

    /**
     * 单词匹配，匹配每个字符最后判断字符是否闭合。
     * 前缀匹配，匹配完全部字符就可以。
     *
     * @param word word
     * @param term 单词/前缀匹配
     * @return 0/1
     */
    private boolean doSearch(String word, boolean term) {
        char[] c = word.toCharArray();
        TrieNode node = dummy;
        for (int i = 0; i < c.length; i++) {
            if (null == node.childes[c[i]-'a']) return false;
            node = node.childes[c[i]-'a'];
        }
        if (term) return node.end;
        return true;
    }

    private class TrieNode {
        char c ;
        boolean end = false;
        TrieNode[] childes ;

        public TrieNode(char c) {
            this.c = c;
            this.childes = new TrieNode[26];
        }
    }
}
