package trees;

import java.util.*;

/**
 * lc 987 hard
 *
 * Analysis:
 * 列标记保证同列的元素归类，最小堆保证行的元素顺序
 * 写起来比较麻烦。
 *
 * 时间复杂度：O(n*logK) K是同行同列的元素个数
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-07 16:30
 */
public class BinaryTreeVerticalOrderTraversal2 {

    int left = 0, right = 0, row = 0;
    // Map<列, Map<行, 行内元素升序>>
    Map<Integer, Map<Integer, Queue<Integer>>> map = new HashMap<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();

        if (null == root) return ans;

        Queue<Node> q = new PriorityQueue<>(1, (o1, o2) -> o1.n.val-o2.n.val);
        q.add(new Node(0, 0, root));

        bfs(q);

        for ( ; left <= right; left++) {
            Map<Integer, Queue<Integer>> col = map.get(left);
            List<Integer> v = new ArrayList();
            for (int lev = 0; lev <= row; lev++) {
                Queue<Integer> t = col.get(lev);
                if (null == t) continue;
                for (int i = 0, len = t.size(); i < len; i++) {
                    v.add(t.poll());
                }
            }
            ans.add(v);
        }
        return ans;
    }

    void bfs(Queue<Node> q) {
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                Node n = q.poll();

                Map<Integer, Queue<Integer>> p = map.getOrDefault(n.c, new HashMap());
                Queue<Integer> que = p.getOrDefault(n.r, new PriorityQueue<>());
                que.add(n.n.val);
                p.put(n.r, que);
                map.put(n.c, p);

                row = Math.max(row, n.r);
                if (null != n.n.left) {
                    left = Math.min(left, n.c-1);
                    q.add(new Node(n.r+1, n.c-1, n.n.left));
                }
                if (null != n.n.right) {
                    right = Math.max(right, n.c+1);
                    q.add(new Node(n.r+1, n.c+1, n.n.right));
                }
            }
        }
    }

    class Node {
        int r;
        int c;
        TreeNode n;
        public Node(int r, int c, TreeNode n) {
            this.r = r;
            this.c = c;
            this.n = n;
        }
    }











}
