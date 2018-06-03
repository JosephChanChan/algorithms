package main.java.trees;

import java.util.List;

/**
 * Created by Joseph at 2018/6/3 0003 15:13.
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(5);
        avlTree.insert(2);
        avlTree.insert(1);
        avlTree.insert(4);
        avlTree.insert(3);
        avlTree.insert(8);
        avlTree.insert(7);
        List<Comparable> comparables = avlTree.inorderTraversal();
        for (Comparable comparable : comparables) {
            System.out.print(comparable+" ");
        }
    }


}
