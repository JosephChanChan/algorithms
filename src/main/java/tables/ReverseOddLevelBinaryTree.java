package tables;

import trees.TreeNode;
import java.util.*;

/**
 * lc 2415 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/19
 */
public class ReverseOddLevelBinaryTree {

    TreeNode root ;
    Queue<TreeNode> q = new LinkedList<>();

    public TreeNode reverseOddLevels(TreeNode root) {
        this.root = root;
        q.add(root);
        int level = 0;

        while (!q.isEmpty()) {
            int c = q.size();

            if ((level & 1) == 0) {

                LinkedList<Integer> s = new LinkedList<>();
                for (int i = 0; i < c; i++) {
                    TreeNode p = q.poll();
                    if (null != p.left) {
                        s.add(p.left.val);
                        s.add(p.right.val);
                        //System.out.println("p="+p.val+" p.l="+p.left.val+" p.r="+p.right.val);
                    }
                    q.add(p);
                }
                for (int i = 0; i < c; i++) {
                    TreeNode p = q.poll();
                    if (null != p.left && !s.isEmpty()) {
                        p.left.val = s.removeLast();
                        p.right.val = s.removeLast();
                        //System.out.println("p="+p.val+" n1="+p.left.val+" n2="+p.right.val);
                    }
                    if (null != p.left) {
                        q.add(p.left);
                        q.add(p.right);
                    }
                }

            }
            else {
                for (int i = 0; i < c; i++) {
                    TreeNode p = q.poll();
                    if (null != p.left) {
                        q.add(p.left);
                        q.add(p.right);
                    }
                }
            }
            level++;
        }
        return root;
    }
}
