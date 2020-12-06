package trees;

import java.util.*;

/**
 * 剑指Offer 37 hard & lc 297 hard
 *
 * Analysis:
 *  序列化：bfs层级遍历，不过这里得注意给没有子节点的节点时添加null节点，使得每个节点都具备两个子节点。
 *  这些子节点可能是真实节点或null。这么做是为反序列化做铺垫。
 *
 *  反序列化：[1,2,3,null,null,null,4,null,null]
 *  在序列化时我们保证了每个节点必定有两个子节点，并且序列化顺序是从每层的左->右。
 *  设两个指针 i, j。i指向当前层的节点，j指向下一层节点。
 *  在下一层中按左->右顺序给i节点分配两个左右子节点。例如：
 *  1,2,3,null,null,null,4,null,null
 *  i j
 *  1,2,3,null,null,null,4,null,null
 *  i   j
 *  1,2,3,null,null,null,4,null,null
 *    i    j
 *  1,2,3,null,null,null,4,null,null
 *    i         j
 *
 *  剑指Offer书上给出的解法是前序遍历dfs。
 * q是前序遍历的队列，对于每一个非null节点都是根。根的左节点肯定是当前位置的下一个节点。
 * 但是根的右节点就不一定是下下个，因为按照前序，会递归左子树，下下个节点可能是左节点的子节点
 * 所以要递归左节点计算完左子树，返回时队列头已经指向根的右节点
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-12-05 10:08
 */
public class SerializeTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) return "";

        StringBuilder b = new StringBuilder();
        //Queue<TreeNode> q = new LinkedList<>();
        //q.add(root);
        //b.append(root.val).append(",");

        preTraversal(b, root);
        return b.delete(b.length()-1, b.length()).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data || data.length() == 0) return null;

        StringBuilder b = new StringBuilder(data);
        String[] s = b.toString().split(",");
        Queue<TreeNode> q = new LinkedList<>();

        for (int i = 0; i < s.length; i++) {
            if (s[i].equals("null")) {
                q.add(null);
                continue;
            }
            q.add(new TreeNode(Integer.parseInt(s[i])));
        }
        TreeNode root = q.peek();
        preTraversalBuild(q);
        return root;
    }

    private TreeNode levelTraversalBuild(TreeNode[] a) {
        int i = 0, j = i + 1;
        while (j < a.length) {
            if (null == a[i]) {
                i++;
                continue;
            }
            TreeNode root = a[i];
            root.left = a[j++];
            if (j >= a.length) break;
            root.right = a[j++];
            i++;
        }
        return a[0];
    }

    private void levelTraversal(int size, StringBuilder b, Queue<TreeNode> q) {
        if (q.isEmpty()) return;
        int k = 0;
        for (int i = 0; i < size; i++) {
            TreeNode n = q.poll();
            b.append(null == n.left ? "null" : n.left.val).append(",");
            b.append(null == n.right ? "null" : n.right.val).append(",");
            if (null != n.left) {
                q.add(n.left);
                k++;
            }
            if (null != n.right) {
                q.add(n.right);
                k++;
            }
        }
        levelTraversal(k, b, q);
    }

    private void preTraversal(StringBuilder b, TreeNode node) {
        b.append(node.val).append(",");
        if (null != node.left) {
            preTraversal(b, node.left);
        }
        else {
            b.append("null,");
        }
        if (null != node.right) {
            preTraversal(b, node.right);
        }
        else {
            b.append("null,");
        }
    }

    private TreeNode preTraversalBuild(Queue<TreeNode> q) {
        TreeNode node = q.poll();
        if (null == node) return null;
        node.left = preTraversalBuild(q);
        node.right = preTraversalBuild(q);
        return node;
    }
}
