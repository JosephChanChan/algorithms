package trees;

import java.util.*;

/**
 * lc 919 medium
 *
 * Analysis:
 * 时间复杂度：插入 O(1)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/17
 */
public class CompleteBinaryTreeInserter {

    TreeNode root ;
    // 左到右保存未满子节点的节点
    Queue<TreeNode> q = new LinkedList<>();

    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
        // 层序遍历，左往右
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode node = list.poll();
            int child = 0;
            if (null != node.left) {
                child++;
                list.add(node.left);
            }
            if (null != node.right) {
                child++;
                list.add(node.right);
            }
            if (child < 2) {
                q.add(node);
            }
        }
    }

    public int insert(int val) {
        TreeNode node = q.peek();
        TreeNode child = new TreeNode(val);
        if (null == node.left) {
            node.left = child;
        }
        else {
            node.right = child;
            q.poll();
        }
        q.add(child);
        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
