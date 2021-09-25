package depth.first.search;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 425 hard
 *
 * Analysis:
 * 题目要求所有的方案，又是搜索类题目
 * 每个单词长度一样，单词方块应有n行n列
 * 最朴素深搜就是枚举每一行的单词，到最后再check，显然时间 O(n!)
 * 剪枝：
 * 第k行单词的1~k-1个字符要匹配1~k-1行单词的第k个字符
 *
 * 用Trie适合前缀字符搜索
 *
 * L是单词的长度
 * 时间复杂度：小于O(26^L)
 * 空间复杂度：Trie树 26^L
 *
 * @author Joseph
 * @since 2021-04-08 19:14
 */
public class WordSquares {

    int n ;
    Node root = new Node(' ');
    List<String> mem = new ArrayList();
    List<List<String>> ans = new ArrayList();

    public List<List<String>> wordSquares(String[] words) {
        this.n = words[0].length();

        for (String term : words) add(term);

        for (String term : words) {
            // 枚举第一行应该放什么单词
            mem.add(term);
            dfs(1);
            mem.remove(mem.size()-1);
        }
        return ans;
    }

    void dfs(int k) {
        if (k == n) {
            ans.add(new ArrayList(mem));
            return;
        }

        // 放置第k行的单词，该行单词的第1~k-1个字符要满足 1~k-1行单词的第k列字符
        Node node = root;
        for (int i = 0; i < k; i++) {
            char c = mem.get(i).charAt(k);
            if (null == node.childs[c-'a']) return;
            node = node.childs[c-'a'];
        }

        List<String> terms = new ArrayList();
        getTerms(node, terms);

        // terms的前缀都是符合1~k-1行单词第k列字符的前缀，枚举第k行的单词
        for (int i = 0; i < terms.size(); i++) {
            mem.add(terms.get(i));
            dfs(k+1);
            mem.remove(mem.size()-1);
        }
    }

    void getTerms(Node node, List<String> terms) {
        Node p = node;
        if (p.end) {
            // 因为所有单词都是等长，所以没必要继续往下搜
            terms.add(p.term);
            return;
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (null != p.childs[c-'a']) {
                getTerms(p.childs[c-'a'], terms);
            }
        }
    }

    void add(String term) {
        Node node = root;
        char[] c = term.toCharArray();
        for (int i = 0; i < n; i++) {
            if (null == node.childs[c[i]-'a']) {
                node.childs[c[i]-'a'] = new Node(c[i]);
            }
            node = node.childs[c[i]-'a'];
        }
        node.end = true;
        node.term = term;
    }

    class Node {
        char v;
        Node[] childs;
        boolean end = false;
        String term = null;
        public Node(char a) {
            this.v = a;
            childs = new Node[26];
        }
    }

}
