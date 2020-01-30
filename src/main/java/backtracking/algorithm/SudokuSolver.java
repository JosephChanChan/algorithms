package backtracking.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joseph
 * @since 2020-01-30 20:33
 *
 * leetcode 37 hard
 *
 * Question Description:
 *  Write a program to solve a Sudoku puzzle by filling the empty cells.
 *  A sudoku solution must satisfy all of the following rules:
 *      Each of the digits 1-9 must occur exactly once in each row.
 *      Each of the digits 1-9 must occur exactly once in each column.
 *      Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 *  Empty cells are indicated by the character '.'.
 *
 *  Note:
 *      The given board contain only digits 1-9 and the character '.'.
 *      You may assume that the given Sudoku puzzle will have a single unique solution.
 *      The given board size is always 9x9.
 *
 * Analysis:
 *  题目就是要求解一个 9*9 的数独，具体规则可自行搜索。这里贴不了 leetcode 上的图片。
 * 时间复杂度：上界是 O(9^step) 但是剪枝绝对小于上界。 step 是数独中留空数。
 * 空间复杂度：O(1)
 */
public class SudokuSolver {

    int count = 0;
//    char[][] board = null;
    List<Cell> cellList = new ArrayList<>();

    char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };


    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        solver.solveSudoku(solver.board);
        solver.print();
    }

    public void solveSudoku(char[][] board) {
        calculateCount(board);
        this.board = board;
        recursiveFill(0);
    }


    private boolean recursiveFill(int step) {
        if (step == count) {
            // 全部填完
            return true;
        }

        Cell cell = cellList.get(step);
        for (int candidate = 1; candidate <= 9; candidate++) {
            char value = convert(candidate);
            int[] location = mappingMatrix(cell.x, cell.y);
            if (conflictInRow(cell.y, value) ||
                conflictInColumn(cell.x, value) ||
                conflictInSubMatrix(location, value)) {
                continue;
            }
            board[cell.y][cell.x] = value;
            if (recursiveFill(step + 1)) {
                return true;
            }
            else {
                board[cell.y][cell.x] = '.';
            }
        }

        return false;
    }



    private char convert(int i) {
        return (char) (i + 48);
    }

    private boolean conflictInColumn(int x, char candidate) {
        for (int i = 0; i < 9; i++) {
            if (board[i][x] == candidate) return true;
        }
        return false;
    }

    private boolean conflictInRow(int y, char candidate) {
        for (int i = 0; i < 9; i++) {
            if (board[y][i] == candidate) return true;
        }
        return false;
    }

    private boolean conflictInSubMatrix(int[] location, char candidate) {
        int rowEnd = location[1] + 2,
            columnEnd = location[0] + 2;
        for (int y = location[1]; y <= rowEnd; y++) {
            for (int x = location[0]; x <= columnEnd; x++) {
                if (board[y][x] == candidate) return true;
            }
        }
        return false;
    }

    /**
     * 计算此单元格所在的九宫格。
     *
     * @param x x
     * @param y y
     * @return 九宫格左上角的坐标
     */
    private int[] mappingMatrix(int x, int y) {
        int[] location = new int[2];
        // 向下取整
        location[0] = (x / 3) * 3;
        location[1] = (y / 3) * 3;
        return location;
    }

    private void calculateCount(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    count++;
                    cellList.add(new Cell(j, i));
                }
            }
        }
    }

    private void print() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.')
                    System.out.print(board[i][j] + " ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }

    private class Cell {
        private int x;
        private int y;

        public Cell (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }



}
