package trees;

/**
 * @author Joseph
 * @since 2020-03-28 16:58
 */
public class TreeNode {

    protected int val;

    protected TreeNode left;

    protected TreeNode right;


    /* Constructor method */

    public TreeNode() {}

    public TreeNode(int x) { val = x; }


    /* Setter And Getter */

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
