package depth.first.search;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * lc 114 medium
 *
 * Question Description:
 *  参见 lc 114
 *
 * Analysis:
 * 题目本身算法不难，遍历整颗树，构造一个链表就行。
 * 但是lc给的要求将树本身结构改变，而不是返回一个链表，
 * dfs走一遍构造个链表，遍历链表将每个节点构造成右子树，这样多了一层线性开销
 *
 * 时间复杂度：O(2n)
 * 空间复杂度：O(logN)
 *
 * @author Joseph
 * @since 2020-07-22 21:55
 */
public class FlattenBinaryTree2LinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(5);
        TreeNode l1 = new TreeNode(3);
        TreeNode r1 = new TreeNode(4);
        TreeNode r2 = new TreeNode(6);
        root.left = l;
        root.right = r;
        l.left = l1;
        l.right = r1;
        r.right = r2;

        FlattenBinaryTree2LinkedList test = new FlattenBinaryTree2LinkedList();
        test.flatten(root);
    }


    public void flatten(TreeNode root) {
        if (null == root) return;

        List<Integer> list = new LinkedList<>();
        dfs(root, list);

        TreeNode node = root, temp ;
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            Integer val = iterator.next();
            temp = new TreeNode(val);
            node.right = temp;
            node = node.right;
        }
        node.left = null;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (null == node) return;

        list.add(node.val);
        dfs(node.left, list);
        dfs(node.right, list);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
