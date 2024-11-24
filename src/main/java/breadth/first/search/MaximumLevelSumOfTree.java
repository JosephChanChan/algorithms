package breadth.first.search;

import trees.TreeNode;
import java.util.*;

/**
 * lc 1161 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/8/1
 */
public class MaximumLevelSumOfTree {

    int max = 0, maxLevel = 1;

    public int maxLevelSum(TreeNode root) {
        if (null == root) {
            return 0;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        max = root.val;
        bfs(q, 1);
        return maxLevel;
    }

    void bfs(LinkedList<TreeNode> q, int level) {
        if (q.isEmpty()) {
            return;
        }
        int c = q.size(), sum = 0;
        for (int i = 0; i < c; i++) {
            TreeNode node = q.removeFirst();
            sum += node.val;
            if (null != node.left) {
                q.add(node.left);
            }
            if (null != node.right) {
                q.add(node.right);
            }
        }
        if (max < sum) {
            max = sum;
            maxLevel = level;
        }
        bfs(q, level + 1);
    }
}
