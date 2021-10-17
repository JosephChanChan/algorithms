package breadth.first.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * lc 1197 medium
 *
 * Analysis:
 *
 * 因为题目没有给出棋盘范围，可以假设是无限大。每次走的时候会往终点水平方向靠近，至少有另一面的4个坐标被排除。
 * 可假设为logN时间
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(logN)
 *
 * @author Joseph
 * @since 2021-04-10 16:18
 */
public class MinimumKnightMoves {

    Set<Node> vis = new HashSet();
    int[][] d = new int[][]{{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;

        Queue<Node> q = new LinkedList();
        Node root = new Node(0, 0);
        q.add(root);
        vis.add(root);

        int step = 0;
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                Node cor = q.poll();

                for (int k = 0; k < d.length; k++) {
                    int row = cor.y + d[k][0];
                    int column = cor.x + d[k][1];
                    if (row == y && column == x) return ++step;

                    // 终点在当前左边，并且新坐标也在当前左边
                    // 终点在当前右边，并且新坐标也在当前右边
                    if ((left(cor.x, x) && left(cor.x, column)) || (!left(cor.x, x) && !left(cor.x, column))) {
                        Node n = new Node(row, column);
                        if (vis.contains(n)) continue;

                        q.add(n);
                        vis.add(n);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    boolean left(int j, int x) {
        return j <= x;
    }

    class Node {
        int y;
        int x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int hashCode() {
            return 31*y*x;
        }
        public boolean equals(Object o) {
            Node n = (Node) o;
            return n.y == this.y && n.x == this.x;
        }
    }
}
