package depth.first.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * lc 101 easy
 *
 * Question Description:
 *  参见 lc 101 镜像树
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(树的深度)
 *
 * @author Joseph
 * @since 2020-07-18 22:30
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (null == root) return true;
        List<TreeNode> left = new ArrayList<>(128);
        List<TreeNode> right = new ArrayList<>(128);

        if (null == root.left && null != root.right ||
            null == root.right && null != root.left)
            return false;

        // 到这里，要么两个都空/两个都不空
        if (null == root.left) return true;
        if (root.left.val != root.right.val) return false;

        left.add(root.left);
        right.add(root.right);
        return bfs(left, right);
    }

    /* DFS的解法比较不容易想到 */
    private boolean dfs(TreeNode t1, TreeNode t2) {
        if (null == t1 && null == t2) return true;
        // 至少有一个不为空，那么如果一空一个有值就不对称
        if (null == t1 || null == t2) return false;

        /*
            对比两棵树是否对称，根节点和其左右子节点分别对称，则两棵树当前层是对称的，
            按照镜像规则继续分解下一层节点 1对称n，2对称n-1 ...
         */
        return t1.val == t2.val && dfs(t1.left, t2.right) && dfs(t1.right, t2.left);
    }

    /* 代码写的太菜了 BFS */
    private boolean bfs(List<TreeNode> l, List<TreeNode> r) {
        if (l.size() == 0 && r.size() == 0) return true;
        if (l.size() == 0 || r.size() == 0) return false;

        List<TreeNode> lc = new LinkedList<>();
        List<TreeNode> rc = new LinkedList<>();
        loopNode(l, lc);
        loopNode(r, rc);

        int i, j ;
        for (i = 0, j = rc.size() - 1; i < lc.size(); i++, j--) {
            if (null == lc.get(i) && null != rc.get(j) ||
                null == rc.get(j) && null != lc.get(i))
                return false;
            if (null == lc.get(i) && null == rc.get(j)) continue;
            if (lc.get(i).val != rc.get(j).val) return false;
        }
        return bfs(lc, rc);
    }

    private void loopNode(List<TreeNode> l, List<TreeNode> lc) {
        for (TreeNode n : l) {
            if (null == n) continue;
            if (null == n.left) {
                lc.add(null);
            }
            else {
                lc.add(n.left);
            }
            if (null == n.right) {
                lc.add(null);
            }
            else {
                lc.add(n.right);
            }
        }
    }


    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
}
