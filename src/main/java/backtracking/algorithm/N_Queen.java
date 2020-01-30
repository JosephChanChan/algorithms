package backtracking.algorithm;

/**
 * leetcode 52 hard（这题应该没有hard难度或者属于hard中的水题）
 *
 * 递归回溯解法：
 * 经典的N皇后问题，即在一个N*N的棋盘上放N个皇后，使得这N个皇后无法互相攻击
 * (任意2个皇后不能处于同一行，同一列或是对角线上)，输出所有可能的摆放情况。
 * 这是道很好的回溯算法题，并且结合递归。
 *
 * 时间复杂度：
 *  该算法形式上采用了递归+回溯实现，本质还是对棋盘上每一个位置枚举，
 *  但加上剪枝，时间会比暴力穷举好很多。
 * 空间复杂度：O(1)
 *
 * Created by Administrator on 2017/4/14 0014.
 */
public class N_Queen {

    static int[] rowsQueen;
    static int count = 0, rows, columns;

    public static void main(String[] args) {
        rowsQueen = new int[8];
        rows = rowsQueen.length;
        columns = rowsQueen.length;
        search(0);
        System.out.println(count);
    }

    private static void search(int row) {
        if (row == rows) {
            count++;
            printDiagram();
            return;
        }
        for (int i = 0; i < columns; i++) {
            rowsQueen[row] = i;// 这一步相当于回溯, 该行将会有一个 new column
            if (canPlace(row)) {
                search(row + 1);
            }
        }
    }

    private static boolean canPlace(int row) {
        for (int k = 0; k < row; k++) {
            if (rowsQueen[row] == rowsQueen[k] ||
                row - k == rowsQueen[row] - rowsQueen[k]||
                row - k == rowsQueen[k] - rowsQueen[row])
                return false;
        }
        return true;
    }

    private static void printDiagram() {
        System.out.println();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < columns; j++) {
                if (rowsQueen[i] == j) {
                    System.out.print("1 ");
                } else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }

}

