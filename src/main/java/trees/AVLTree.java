package main.java.trees;

/**
 * AVLTree 平衡二叉搜索树
 * 提供了 Demo 级一般的实现. 主要研究该数据结构的优势, 时间复杂度和空间复杂度.
 * 最重要的旋转算法, 查找, 插入, 删除.
 *
 * Created by Joseph at 2018/5/27 0027 19:23.
 */
public class AVLTree {

    private AVLNode root;

    public <T extends Comparable> void insert(T beInsert){
        if (null != beInsert){
            root = insert(beInsert, root);
        }
    }
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
        return avlNode;
    }

    public <T extends Comparable> AVLNode remove(T removed){
        if (null == removed){
            return null;
        }
        if (null == root){
            return null;
        }
        return remove(removed, root);
    }
    private <T extends Comparable> AVLNode remove(T removed, AVLNode avlNode){
        int result = removed.compareTo(avlNode.element);
        if ( result < 0 ){
            return remove(removed, avlNode.left);
        }
        else if ( result > 0 ){
            return remove(removed, avlNode.right);
        }
        else {
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
                    and less than all right child. that is most depth left of firstly right child
                 */
                AVLNode right = avlNode.right;
                AVLNode min = findMin(avlNode.right);
                avlNode = min;
                remove(min.element, right);
            }
        }
        return avlNode;
    }

    private AVLNode findMin(AVLNode avlNode){
        // if it have left child, the most depth left is minimum
        if (null != avlNode.left){
            return findMin(avlNode.left);
        }
        else {
            return avlNode;
        }
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
            return 0;
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
