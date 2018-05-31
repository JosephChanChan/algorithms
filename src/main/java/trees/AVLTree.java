package main.java.trees;

/**
 * AVLTree 平衡二叉搜索树
 * 提供了 Demo 级一般的实现. 主要研究该数据结构的优势, 时间复杂度和空间复杂度.
 * 最重要的平衡算法及对应的旋转算法, 查找, 插入, 删除.
 *
 * Created by Joseph at 2018/5/27 0027 19:23.
 */
public class AVLTree {

    private AVLNode root;

    /** tolerable difference height between left tree and right tree */
    private static final int MAX_DIFF_HEIGHT = 1;


    public <T extends Comparable> void insert(T beInsert){
        if (null != beInsert){
            root = insert(beInsert, root);
        }
    }

    /**
     * Insert a T into the tree.
     * Return a sub tree node when every recursion.
     *
     * @param beInsert T
     * @param avlNode tree node
     * @param <T> T
     * @return node
     */
    private <T extends Comparable> AVLNode insert(T beInsert, AVLNode avlNode){
        if (null == avlNode){
            return new AVLNode(beInsert);
        }
        int result = beInsert.compareTo(avlNode.element);
        if (result < 0){
            avlNode.left = insert(beInsert, avlNode.left);
        }
        else if (result > 0){
            avlNode.right = insert(beInsert, avlNode.right);
        }
        else {
            // beInsert equals someone, we needn't it
        }

        // calculate height of node
        avlNode.height = Math.abs(calculateHeight(avlNode.left) - calculateHeight(avlNode.right)) + 1;

        // UnBalance maybe occur after insert, do balance fixing it
        return balance(avlNode);
    }

    public <T extends Comparable> void remove(T removed){
        if (
                null != removed &&
                null != root
           )
        {
            root = remove(removed, root);
        }
    }
    private <T extends Comparable> AVLNode remove(T removed, AVLNode avlNode){
        if (null != avlNode){
            int result = removed.compareTo(avlNode.element);
            if ( result < 0 ){
                avlNode.left = remove(removed, avlNode.left);
            }
            else if ( result > 0 ){
                avlNode.right = remove(removed, avlNode.right);
            }
            else {
                // got the node. lets remove it

                if (null == avlNode.left && null == avlNode.right){
                    avlNode = null;
                }
                // only have left
                else if (null == avlNode.right){
                    avlNode = avlNode.left;
                }
                // only have right
                else if (null == avlNode.left){
                    avlNode = avlNode.right;
                }
                // have left and right
                else {
                    /*
                        we should find a node which greater than all left child
                        and less than all right child. that is most depth left of firstly right child.
                        After the removed was instead of minimum, we must remove the minimum.
                        A recursion process would be called which are same as before.
                     */
                    AVLNode minimum = findMin(avlNode.right);
                    avlNode.element = minimum.element;
                    avlNode.right = remove(minimum.element, avlNode.right);
                }

                // UnBalance maybe occur after remove, do balance fixing it
                return balance(avlNode);
            }
        }
        return avlNode;
    }

    private AVLNode balance(AVLNode avlNode){
        // check if unbalance is occur
        int diff = Math.abs(calculateHeight(avlNode.left) - calculateHeight(avlNode.right));
        if (diff > MAX_DIFF_HEIGHT){
            // then we must fixing it, but we have to confirm which status of unbalance

            /*
                unbalance situation:
                    1, the new node has been inserted at left tree's left side (LL)
                    2, the new node has been inserted at left tree's right side (LR)
                    3, the new node has been inserted at right tree's left side (RL)
                    4, the new node has been inserted at right tree's right side (RR)
                we have 4 algorithm to handle corresponding situation.
                Mapping relationship:
                LL -> rightRotate
                RR -> leftRotate
                LR -> leftRotateThenRightRotate
                RL -> rightRotateThenLeftRotate
             */

            // firstly check is left tree or right tree
            int leftTreeHeight = calculateHeight(avlNode.left);
            int rightTreeHeight = calculateHeight(avlNode.right);

            int leftSideHeight ;
            int rightSideHeight;

            if (leftTreeHeight > rightTreeHeight){
                // confirm further left or right
                leftSideHeight = calculateHeight(avlNode.left.left);
                rightSideHeight = calculateHeight(avlNode.left.right);
                if (leftSideHeight > rightSideHeight){
                    // LL mode just do rightRotate can fixing it

                }
                else {
                    // LR mode do leftRotateThenRightRotate
                }
            }
            else {
                leftSideHeight = calculateHeight(avlNode.right.left);
                rightSideHeight = calculateHeight(avlNode.right.right);
                if (leftSideHeight > rightSideHeight){
                    // RL mode do rightRotateThenLeftRotate
                }
                else {
                    // RR mode do leftRotate
                }
            }


        }

        return avlNode;
    }

    private AVLNode rightRotate(AVLNode avlNode){
        AVLNode k1 = avlNode.left;
        avlNode.right = k1.right;
        k1.right = avlNode;

        // two node height must recalculate
        avlNode.height = Math.max(calculateHeight(avlNode.left), calculateHeight(avlNode.right)) + 1;
        k1.height = Math.max(calculateHeight(k1.left), avlNode.height) + 1;

        return k1;
    }

    private AVLNode findMin(AVLNode avlNode){
        AVLNode minimum = null;
        if (null != avlNode){
            // if it have left child, the most depth left is minimum
            while (null != avlNode.left){
                avlNode = avlNode.left;
            }
            minimum = avlNode;
        }
        return minimum;
    }

    private AVLNode findMax(AVLNode avlNode){
        AVLNode maximum = null;
        if (null != avlNode){
            // the most depth right is maximum
            while (null != avlNode.right){
                avlNode = avlNode.right;
            }
            maximum = avlNode;
        }
        return maximum;
    }

    public <T extends Comparable> boolean contains(T beSearch, AVLNode avlNode){
        boolean exists ;
        int result = beSearch.compareTo(avlNode.element);
        if ( result < 0 ){
            exists = contains(beSearch, avlNode.left);
        }
        else if ( result > 0 ){
            exists = contains(beSearch, avlNode.right);
        }
        else {
            exists = true;
        }
        return exists;
    }

    /** calculate the specific node's height */
    private int calculateHeight(AVLNode avlNode){
        if (null == avlNode){
            return -1;
        }
        return avlNode.height;
    }



    public static void main(String[] args) {
    }


    /** AVLTree Node */
    private static class AVLNode< T extends Comparable<? super T> > {
        int height;
        T element;
        AVLNode left;
        AVLNode right;

        AVLNode(T element){
            this (element, null, null);
        }

        AVLNode(T element, AVLNode left, AVLNode right){
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }
}
