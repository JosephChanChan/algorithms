package trees;

import java.util.*;

/** lc 212 hard
 *
 * Analysis:
 *  将所有单词建Trie，dfs遍历网格，试探每个字符在Trie中是否存在
 *
 * 时间复杂度：下界 O(nm * 单词平均长度) 涉及dfs不好评估
 * 空间复杂度：O(words)
 *
 * @author Joseph
 * @since 2021-02-05 15:55
 */
public class WordSearch2 {

    Node root = new Node(' ');
    boolean[][] vis ;
    int n, m ;
    int[][] d = new int[][]{{-1,0}, {1,0}, {0,-1}, {0, 1}};
    char[][] board ;

    List<String> ans = new ArrayList();

    public List<String> findWords(char[][] board, String[] words) {
        n = board.length;
        m = board[0].length;
        vis = new boolean[n][m];
        this.board = board;

        buildTrie(words);

        Node node ;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (null != (node = root.childs[board[i][j]-'a'])) {
                    vis[i][j] = true;
                    b.append(board[i][j]);
                    dfs(i, j, node, b);
                    b.delete(b.length()-1, b.length());
                    vis[i][j] = false;
                }
            }
        }
        return ans;
    }

    void dfs(int i, int j, Node node, StringBuilder b) {
        if (node.end) {
            ans.add(b.toString());
            node.end = false;
        }

        for (int k = 0; k < 4; k++) {
            int r = i+d[k][0];
            int c = j+d[k][1];
            if (r >= 0 && r < n && c >= 0 && c < m && !vis[r][c] && null != node.childs[board[r][c]-'a']) {
                vis[r][c] = true;
                b.append(board[r][c]);
                dfs(r, c, node.childs[board[r][c]-'a'], b);
                b.delete(b.length()-1, b.length());
                vis[r][c] = false;
            }
        }
    }

    void buildTrie(String[] words) {
        for (String word : words) {
            add(word);
        }
    }

    void add(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (null == node.childs[word.charAt(i)-'a']) {
                node.childs[word.charAt(i)-'a'] = new Node(word.charAt(i));
            }
            node = node.childs[word.charAt(i)-'a'];
        }
        node.end = true;
        //System.out.println(node.v+" & "+word);
    }

    class Node {
        char v;
        Node[] childs;
        boolean end = false;
        public Node(char v) {
            this.v = v;
            this.childs = new Node[26];
        }
    }
}
