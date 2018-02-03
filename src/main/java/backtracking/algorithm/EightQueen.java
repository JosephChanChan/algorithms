package main.java.backtracking.algorithm;

/**
 * Created by Administrator on 2017/4/14 0014.
 *
 * 经典的八皇后问题，即在一个8*8的棋盘上放8个皇后，使得这8个皇后无法互相攻击
 * (任意2个皇后不能处于同一行，同一列或是对角线上)，输出所有可能的摆放情况。

    这是道很好的回溯算法题，并且结合递归。
    算法不是我想的，是网上的，惭愧，算法渣。

 思路：
     8皇后是个经典的问题，如果使用暴力法，每个格子都去考虑放皇后与否，一共有264 种可能。
    所以暴力法并不是个好办法。由于皇后们是不能放在同一行的， 所以我们可以去掉“行”这个因素，
    即我第1次考虑把皇后放在第1行的某个位置， 第2次放的时候就不用去放在第一行了，因为这样放皇后间是可以互相攻击的。
    第2次我就考虑把皇后放在第2行的某个位置，第3次我考虑把皇后放在第3行的某个位置， 这样依次去递归。每计算1行，递归一次，
    每次递归里面考虑8列， 即对每一行皇后有8个可能的位置可以放。找到一个与前面行的皇后都不会互相攻击的位置，
    然后再递归进入下一行。找到一组可行解即可输出，然后程序回溯去找下一组可靠解。
     我们用一个一维数组来表示相应行对应的列，比如c[i]=j表示， 第i行的皇后放在第j列。如果当前行是r，
    皇后放在哪一列呢？c[r]列。 一共有8列，所以我们要让c[r]依次取第0列，第1列，第2列……一直到第7列，
    每取一次我们就去考虑，皇后放的位置会不会和前面已经放了的皇后有冲突。 怎样是有冲突呢？同行，同列，对角线。
    由于已经不会同行了，所以不用考虑这一点。 同列：c[r]==c[j]; 同对角线有两种可能，即主对角线方向和副对角线方向。
    主对角线方向满足，行之差等于列之差：r-j==c[r]-c[j]; 副对角线方向满足， 行之差等于列之差的相反数：r-j==c[j]-c[r]。 只有满足了当前皇后和前面所有的皇后都不会互相攻击的时候，才能进入下一级递归。

 递归回溯解法：
 */
public class EightQueen {

    static int[] rowsQuen = new int[20];
    static int count = 0;
    static int columns = 8;
    static int rows = 8;

    public static void search(int row){
        if(row == rows){
            count++;
            printDiagram();
            return;
        }
        for(int i=0; i<columns; i++){
            rowsQuen[row] = i;
            boolean can = true;
            for(int k=0; k<row; k++){
                if(rowsQuen[row] == rowsQuen[k]
                        || row-k == rowsQuen[row] - rowsQuen[k]
                        || row-k == rowsQuen[k] - rowsQuen[row]){
                    can = false;
                    break;
                }
            }
            if(can){
                search(row+1);
            }
        }
    }

    public static void printDiagram(){
        System.out.println();
        for(int i=0; i<columns; i++){
            for(int j=0; j<columns; j++){
                if(rowsQuen[i]==j){
                    System.out.print("1 ");
                }else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        search(0);
        System.out.println(count);
    }


    //暴力穷举解法：
    /**

     static int count = 0;
     static int[] rows = new int[8];
     public static void main(String[] args){

     //枚举1行，rows[0]=0 标识第1行从第1列开始枚举到第8列
     for(rows[0]=0; rows[0]<8; rows[0]++){
     for(rows[1]=0; rows[1]<8; rows[1]++){
     for(rows[2]=0; rows[2]<8; rows[2]++){
     for(rows[3]=0; rows[3]<8; rows[3]++){
     for(rows[4]=0; rows[4]<8; rows[4]++){
     for(rows[5]=0; rows[5]<8; rows[5]++){
     for(rows[6]=0; rows[6]<8; rows[6]++){
     for(rows[7]=0; rows[7]<8; rows[7]++){
     //                                        print();
     if(!isConflict(8)){
     count++;
     print();
     }
     }
     }
     }
     }
     }
     }
     }
     }
     System.out.println(count);
     }

     public static boolean isConflict(int x){
     boolean flag = false;
     for(int k=1; k<x; k++){
     for(int i=0; i<k; i++){
     if(rows[i]==rows[k] || k-i==rows[k]-rows[i] || k-i==rows[i]-rows[k]){
     flag = true; break;
     }
     }
     }
     return flag;
     }

     public static void print(){
     System.out.println();
     for(int i=0; i<8; i++){
     for(int k=0; k<8; k++){
     if(rows[i]==k){
     System.out.print("1 ");
     }else System.out.print("0 ");
     }
     System.out.println();
     }
     }
     */
}

