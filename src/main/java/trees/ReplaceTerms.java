package trees;

import java.util.List;

/**
 * lc 648 medium
 *
 * Analysis:
 * 时间复杂度：O(terms)
 * 空间复杂度：O(distinct terms)
 *
 * @author Joseph
 * @since 2022/7/9
 */
public class ReplaceTerms {

    TrieNode root ;

    public String replaceWords(List<String> dictionary, String sentence) {
        buildTree(dictionary);
        String[] arr = sentence.split(" ");
        StringBuilder b = new StringBuilder();
        for (String term : arr) {
            String rootTerm = hitTerm(term);
            if (null != rootTerm) {
                b.append(rootTerm).append(" ");
            }
            else {
                b.append(term).append(" ");
            }
        }
        b.delete(b.length()-1, b.length());
        return b.toString();
    }

    String hitTerm(String term) {
        char[] c = term.toCharArray();
        TrieNode node = root;
        StringBuilder b = new StringBuilder();
        for (char cc : c) {
            if (node.end) {
                return b.toString();
            }
            b.append(cc);
            if (node.hit(cc)) {
                node = node.getNext(cc);
            }
            else {
                //System.out.println(b.toString());
                return null;
            }
        }
        if (node.end) {
            return b.toString();
        }
        return null;
    }

    void buildTree(List<String> dics) {
        root = new TrieNode('/');
        for (String term : dics) {
            TrieNode node = root;
            char[] ca = term.toCharArray();
            for (char c : ca) {
                if (node.hit(c)) {
                    node = node.getNext(c);
                }
                else {
                    node = node.set(c);
                }
            }
            node.endNode();
        }
    }

    class TrieNode {
        char c;
        int[] childs;
        TrieNode[] next ;
        boolean end;
        public TrieNode(char c) {
            this.c = c;
            childs = new int[26];
            next = new TrieNode[26];
        }
        public void endNode() {
            this.end = true;
        }
        public boolean hit(char c) {
            return childs[c-'a'] > 0;
        }
        public TrieNode set(char c) {
            childs[c-'a']++;
            next[c-'a'] = new TrieNode(c);
            return next[c-'a'];
        }
        public TrieNode getNext(char c) {
            return next[c-'a'];
        }
    }
}
