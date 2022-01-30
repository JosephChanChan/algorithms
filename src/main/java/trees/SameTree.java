package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * lc 100 easy
 *
 * Question Description:
 *  Given two binary trees, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 * Example 1:
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *  [1,2,3],   [1,2,3]
 * Output: true
 * Example 2:
 * Input:     1         1
 *           /           \
 *          2             2
 *  [1,2],     [1,null,2]
 * Output: false
 * Example 3:
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *  [1,2,1],   [1,1,2]
 * Output: false
 *
 * Analysis:
 *  验证两棵树的结构是否一样，可以对它们进行层级或深度优先的遍历，把节点都装进队列中。
 *  剩下的就是遍历每个对应的节点验证。
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(n)
 *
 *  @author Joseph
 *  @since 2020-01-31 18:13
 */
public class SameTree {


    /** 对2棵树进行层级遍历 */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> pQueue = new LinkedList<>();
        Queue<TreeNode> qQueue = new LinkedList<>();

        pQueue.add(p);
        qQueue.add(q);

        // 如果2棵树是一样的，那么当遍历完一个队列时，另一个队列应该为空
        while (!pQueue.isEmpty()) {
            p = pQueue.remove();
            q = qQueue.remove();

            if (!isSame(p, q)) return false;

            if (null != p) {
                pQueue.add(p.left);
                pQueue.add(p.right);
                qQueue.add(q.left);
                qQueue.add(q.right);
            }
        }

        return qQueue.isEmpty();
    }

    private boolean isSame(TreeNode p, TreeNode q) {
        if (null == p && null == q) return true;
        if (null == p || null == q) return false;
        return p.val == q.val;
    }

    private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

}
