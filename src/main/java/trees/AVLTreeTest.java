package main.java.trees;

import main.java.sort.CheckSortedArr;

import java.util.List;
import java.util.Random;

/**
 * Created by Joseph at 2018/6/3 0003 15:13.
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        test5();
    }

    /**
     * test1 insert N element.
     * The test include the case that the tree's unbalance fixed by single rotate or double rotate.
     * Executing a inorder traversal to output the elements which should be a ascending sequence.
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
     * test2 insert one hundred thousand or one million elements.
     * Then check the tree is ascending sequence.
     * if it's what we expect that mean The AVLTree is correctly work.
     * AVLTree had showed good performance of insert operation.
     * Even if the input size was one million the tree also had costed short time to finish.
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

    /**
     * This method will testing remove case.
     * Include single rotate case and double rotate case.
     */
    private static void test3(){
        AVLTree avlTree = new AVLTree();
        int[] testArr = {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
        for (int i : testArr){
            avlTree.insert(i);
        }

        // show the tree is correct
        System.out.println(" Initially tree after insert ");
        List<Comparable> comparables = avlTree.inorderTraversal();
        for (Comparable comparable : comparables) {
            System.out.print(comparable+" ");
        }
        System.out.println();

        // remove 11, the unbalance occur then fixed by double rotate
        avlTree.remove(11);

        // show the tree is correct
        System.out.println(" After 11 was removed ");
        comparables = avlTree.inorderTraversal();
        for (Comparable comparable : comparables) {
            System.out.print(comparable+" ");
        }
        System.out.println();

        // remove 14 and 16, tree' ll be unbalance then fixed by single rotate
        avlTree.remove(14);
        avlTree.remove(16);

        // show the tree is correct
        System.out.println(" After 14, 16 was removed ");
        comparables = avlTree.inorderTraversal();
        for (Comparable comparable : comparables) {
            System.out.print(comparable+" ");
        }
    }

    /**
     * Construct twice remove
     */
    private static void test4(){
        AVLTree avlTree = new AVLTree();
        int[] testArr = {60,30,75,20,50,70,80,72};
        for (int i : testArr){
            avlTree.insert(i);
        }

        // show the tree is correct
        System.out.println(" Initially tree after insert ");
        List<Comparable> comparables = avlTree.inorderTraversal();
        for (Comparable comparable : comparables) {
            System.out.print(comparable+" ");
        }
        System.out.println();

        avlTree.remove(60);

        // show the tree is correct
        System.out.println(" After root was removed ");
        comparables = avlTree.inorderTraversal();
        for (Comparable comparable : comparables) {
            System.out.print(comparable+" ");
        }
    }

    private static void test5(){
        int hundredThousand = 100000;
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

        // Use Tool to check the array is ascending sequence
        int index = CheckSortedArr.checkAsc(comparables.toArray(new Comparable[comparables.size()]));
        if (-1 == index){
            System.out.println(" \r\n ascending sequence \r\n");
        }
        else {
            System.out.println(index);
        }

        // Executing 50000 time random remove
        for (int k = 0; k < (hundredThousand >> 1); k++){
            int ranIndex = random.nextInt(randomArr.length-1);
            int beRemoved = randomArr[ranIndex];
//            try {
                avlTree.remove(beRemoved);
//            }
//            catch (Exception ex){
//                System.out.println(ex.getMessage());
//                System.out.println(" error occur when time = "+k);
//                System.out.println(" value is " + beRemoved);
//            }
        }

        comparables = avlTree.inorderTraversal();

        // Use Tool to check the array is ascending sequence
        // After random remove
        index = CheckSortedArr.checkAsc(comparables.toArray(new Comparable[comparables.size()]));
        if (-1 == index){
            System.out.println(" \r\n After random remove, ascending sequence \r\n");
        }
        else {
            System.out.println(index);
        }
    }

}
