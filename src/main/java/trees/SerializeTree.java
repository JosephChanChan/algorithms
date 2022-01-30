package trees;

import java.util.*;

/**
 * 剑指Offer 37 hard & lc 297 hard
 *
 * Analysis:
 *  序列化：bfs层级遍历每个节点。
 *
 *  反序列化：[1,2,3,null,null,null,4,null,null]
 *  在序列化时我们保证了每个节点必定有两个子节点，并且序列化顺序是从每层的左->右。
 *  将当前层节点入队，设i指向当前层的子节点
 *  在下一层中按左->右顺序给队列中的节点分配两个左右子节点。例如：
 *  1,2,3,null,null,null,4,null,null
 *    i
 *  1,2,3,null,null,null,4,null,null
 *      i
 *  1,2,3,null,null,null,4,null,null
 *
 *  剑指Offer书上给出的解法是前序遍历dfs。
 * q是前序遍历的队列，对于每一个非null节点都是根。根的左节点肯定是当前位置的下一个节点。
 * 但是根的右节点就不一定是下下个，因为按照前序，会递归左子树，下下个节点可能是左节点的子节点
 * 所以要递归左节点计算完左子树，返回时队列头已经指向根的右节点
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * 需要三刷 54min
 *
 * @author Joseph
 * @since 2020-12-05 10:08
 */
public class SerializeTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) return null;

        StringBuilder b = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        bfs(queue, b);
        return b.toString();
    }
    void bfs(Queue<TreeNode> queue, StringBuilder b) {
        int n = queue.size();
        for (int i = 0; i < n; i++) {
            TreeNode node = queue.remove();
            b.append(null == node ? "null" : node.val).append(",");
            if (null != node) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        if (queue.size() > 0) bfs(queue, b);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data || data.length() == 0) return null;

        String[] s = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        buildTree(1, s, queue);
        return root;
    }
    void buildTree(int i, String[] s, Queue<TreeNode> queue) {
        if (i >= s.length) return;

        int n = queue.size();
        for (int k = 0; k < n; k++) {
            TreeNode root = queue.remove();
            if (!s[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(s[i]));
                root.left = left;
                queue.add(left);
            }
            i++;
            if (i < s.length && !s[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(s[i]));
                root.right = right;
                queue.add(right);
            }
            i++;
        }
        buildTree(i, s, queue);
    }
}
