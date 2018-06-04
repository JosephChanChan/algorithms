package main.java.trees;

import main.java.sort.CheckSortedArr;

import java.util.List;
import java.util.Random;

/**
 * Created by Joseph at 2018/6/3 0003 15:13.
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        test2();
    }

    /**
     * test1 insert N element.
     * The test include the case was fixed by single rotate or double rotate.
     * Executing a inorder traversal to output the elements it should be a ascending sequence.
     * if it's what we expect that mean The AVLTree is correctly work.
     */
    private static void test1(){
        AVLTree avlTree = new AVLTree();
        int[] testArr = {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
        for (int i : testArr){
            avlTree.insert(i);
        }
        List<Comparable> comparables = avlTree.inorderTraversal();
        for (Comparable comparable : comparables) {
            System.out.print(comparable+" ");
        }
    }

    /**
     * test2 insert one hundred thousand or one million element.
     * Then check the tree is ascending sequence.
     * if it's what we expect that mean The AVLTree is correctly work.
     * AVLTree had showed good performance of insert operation.
     * Even if the input size was one million the tree also had costed short time to finished.
     */
    private static void test2(){
        int hundredThousand = 100000;
//        int oneMillion = 1000000;
        int[] randomArr = new int[hundredThousand];
        Random random = new Random();
        for (int i = 0; i < hundredThousand; i++){
            randomArr[i] = random.nextInt(100000);
        }

        AVLTree avlTree = new AVLTree();
        for (int j = 0; j < randomArr.length; j++){
            avlTree.insert(randomArr[j]);
        }

        List<Comparable> comparables = avlTree.inorderTraversal();
        for (Comparable comparable : comparables) {
            System.out.print(comparable+" ");
        }

        // Use Tool to check the array is ascending sequence
        int index = CheckSortedArr.checkAsc(comparables.toArray(new Comparable[comparables.size()]));
        if (-1 == index){
            System.out.println(" \r\n ascending sequence ");
        }
        else {
            System.out.println(index);
        }
    }


}
