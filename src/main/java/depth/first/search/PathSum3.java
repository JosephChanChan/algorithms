package depth.first.search;

import trees.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 437 medium
 *
 * Analysis:
 * 双dfs本质是枚举起点，以某个节点为起点再枚举终点。
 *
 * 前缀和本质是枚举终点，根据当前节点的前缀和s，如果s!=t，那么看s和t的差d。
 * 如果当前路径上有一小段路径的前缀和也是d，那么丢弃这一小段路径使得 s-d=t，代表快速找到一个起点，
 * 从起点到当前节点累计的和可以满足t。所以说是快速寻找起点。
 *
 * 时间复杂度：双dfs O(n^2) 前缀和 O(n)
 * 空间复杂度：双dfs 树变链表情况下最坏递归n次 O(n) 前缀和 O(n)
 *
 * @author Joseph
 * @since 2021-03-23 11:44
 */
public class PathSum3 {

    int t ;
    int c = 0;

    Map<Integer, Integer> prefixes = new HashMap<>();


    public int pathSum(TreeNode root, int sum) {
        return usingPrefix(root, sum);
    }

    int usingPrefix(TreeNode root, int sum) {
        /*
            前缀和是从根到当前节点的累计和，可以帮助我们快速找到起点。
            双dfs中需要枚举起点和终点。假设我们只枚举终点，将根到当前节点的和累计设为s。
            题目要求的sum设为t。
            如果s==t，代表根到当前节点的累计和刚好，起点就是根。
            如果s!=t，s-t=d，d就是那个差，不管s>t还是s<t，d会是正数或负数。
            我们要找的就是根到当前节点的路径中，是否有路径的累计和是d的。
            如果有，也就是说把这一小段路径给抛弃，那么s-d=t。
        */
        if (null == root) {
            if (sum == 0) return 0;
            return 0;
        }
        t = sum;
        dfs(0, root);
        return c;
    }
    void dfs(int s, TreeNode node) {
        s += node.val;

        // root->node 当前路径的累计和刚好和t相等
        if (s == t) {
            c++;
        }
        // s和t的差d，如果当前路径中有一小段路径和刚好是d，那么抛弃这段d，剩下的路径和就是t
        if (prefixes.containsKey(s-t)) {
            c += prefixes.get(s-t);
        }

        prefixes.put(s, prefixes.getOrDefault(s, 0)+1);

        if (null != node.left) dfs(s, node.left);
        if (null != node.right) dfs(s, node.right);

        prefixes.put(s, prefixes.get(s)-1);
    }

    int doubleDfs(TreeNode root, int sum) {
        this.t = sum;
        dfsStart(root);
        return c;
    }
    void dfsStart(TreeNode node) {
        if (null == node) return;
        dfsEnd(node, 0);
        dfsStart(node.left);
        dfsStart(node.right);
    }
    void dfsEnd(TreeNode node, int sum) {
        if (sum + node.val == t) c++;

        if (null != node.left) dfsEnd(node.left, sum + node.val);
        if (null != node.right) dfsEnd(node.right, sum + node.val);
    }
}
