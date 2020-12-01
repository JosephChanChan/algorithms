package trees;

import java.util.*;

/**
 * 剑指Offer 32 medium
 *
 * Analysis:
 *  力扣很坑啊，题目数据范围是1000，实际数据有个case到了1009
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-11-28 17:52
 */
public class LevelOrderTraversalTree {

    int total = 0, count = 0;
    TreeNode[] nodes = new TreeNode[1010];
    int[] ans ;

    public int[] levelOrder(TreeNode root) {
        if (null == root) return new int[0];
        nodes[total++] = root;
        bfs(0, 1);

        ans = new int[total];
        for (int i = 0; i < total; i++) {
            ans[i] = nodes[i].val;
        }
        return ans;
    }

    private void bfs(int l, int count) {
        if (count == 0) return;
        int p = 0;
        for (int i = 0; i < count; i++, l++) {
            TreeNode node = nodes[l];
            if (null != node.left) {
                p++;
                nodes[total++] = node.left;
            }
            if (null != node.right) {
                p++;
                nodes[total++] = node.right;
            }
        }
        count = p;
        bfs(l, count);
    }

    Queue<TreeNode> list = new LinkedList<>();
    List<List<Integer>> ans2 = new LinkedList<>();

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (null == root) return ans2;

        list.add(root);
        bfs(1);
        return ans2;
    }

    private void bfs(int count) {
        if (count == 0) return;
        int p = 0;
        List<Integer> vals = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            TreeNode node = list.remove();
            vals.add(node.val);
            if (null != node.left) {
                p++;
                list.add(node.left);
            }
            if (null != node.right) {
                p++;
                list.add(node.right);
            }
        }
        ans2.add(vals);
        count = p;
        bfs(count);
    }
}
