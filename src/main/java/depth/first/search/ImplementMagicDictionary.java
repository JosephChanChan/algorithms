package depth.first.search;

/**
 * lc 676 medium
 *
 * Analysis:
 * 时间复杂度：O(distinct words)，最坏情况下，对于Trie树的每个分支都搜了一遍，一个分支相当于一个单词
 * 空间复杂度：O(distinct words)
 *
 * @author Joseph
 * @since 2022/7/11
 */
public class ImplementMagicDictionary {

    TrieNode root = new TrieNode('/');

    public ImplementMagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        buildTrie(dictionary);
    }

    public boolean search(String searchWord) {
        char[] ac = searchWord.toCharArray();
        return dfs(root, ac, 0, 0);
    }

    boolean dfs(TrieNode node, char[] ac, int nextChar, int diff) {
        if (nextChar == ac.length) {
            return node.end && diff == 1;
        }
        char c = ac[nextChar];
        if (null == node.haveChild(c)) {
            if (++diff > 1) {
                return false;
            }
            nextChar++;// 跳过下个字符
        }
        else {
            // 下个字符命中，沿着分支搜索
            boolean match = dfs(node.haveChild(c), ac, nextChar+1, diff);
            if (match) {
                return true;
            }
            nextChar++;// 下个字符的分支不匹配，跳过下个字符
            diff++;
        }
        // 都没命中 对当前节点的所有子树做深搜
        for (TrieNode child : node.next) {
            if (null == child || child.c == c) {
                continue;
            }
            boolean match = dfs(child, ac, nextChar, diff);
            if (match) {
                return true;
            }
        }
        return false;
    }

    void buildTrie(String[] dictionary) {
        for (String term : dictionary) {
            TrieNode node = root;
            char[] ac = term.toCharArray();
            for (char c : ac) {
                if (null == node.haveChild(c)) {
                    TrieNode newNode = new TrieNode(c);
                    node.add(newNode);
                }
                node = node.haveChild(c);
            }
            node.end = true;
        }
    }

    class TrieNode {
        char c;
        TrieNode[] next;
        boolean end;
        public TrieNode(char c) {
            this.c = c;
            this.next = new TrieNode[26];
        }
        public TrieNode haveChild(char c) {
            return next[c-'a'];
        }
        public void add(TrieNode child) {
            next[child.c - 'a'] = child;
        }
    }
}
