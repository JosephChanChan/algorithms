package trees;

import java.util.*;

/**
 * 剑指Offer 34 medium
 *
 * Analysis:
 *  dfs样例题。36min AC
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-11-29 00:49
 */
public class SumOfTreePaths {

    int sum ;
    List<Integer> list = new LinkedList<>();
    List<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (null == root) return ans;
        if (null == root.left && null == root.right && sum == root.val) {
            list.add(root.val);
            ans.add(list);
            return ans;
        }

        this.sum = sum;
        dfs(root, list, 0);
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> list, int total) {
        list.add(node.val);
        total += node.val;
        if (null == node.left && null == node.right) {
            if (sum == total) ans.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        if (null != node.left) dfs(node.left, list, total);
        if (null != node.right) dfs(node.right, list, total);
        list.remove(list.size() - 1);
    }
}
