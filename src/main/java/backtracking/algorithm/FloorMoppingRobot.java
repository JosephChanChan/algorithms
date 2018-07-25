package backtracking.algorithm;

/**
 * Created by Joseph on 2017/11/10.
 *  现在有很多制造商都在卖扫地机器人，它非常有用，能为忙碌的我们分担家务负担。
 *  不过我们也很难理解为什么扫地机器人有时候会反复清扫某一个地方。

     假设有一款不会反复清扫同一个地方的机器人，它只能前后左右移动。举个例子，
    如果第1 次向后移动，那么连续移动3 次时，就会有以下9 种情况（ 图6 ）。
    又因为第1 次移动可以是前后左右4 种情况，所以移动3 次时全部路径有9×4 ＝ 36 种。

    0       0       0       0           0     3  0      0 3    0            0
    1       1       1       1 2      2  1     2  1      1 2    1 2 3    3 2 1
    2       2 3   3 2         3      3
    3

    求这个机器人移动12 次时，有多少种移动路径？

    算法：
        每一步理论上有4个方向可走，加条件限制后需要判断：
            1，不能走上一步相反的方向
            2，不能走已走过的坐标
        一层循环模拟机器人走的方向，过程加判断条件，递归下一步。
        当下一步走完后，回溯当前步，换另外的方向继续递归。
 */
public class FloorMoppingRobot {

    static int[][] arr = new int[25][25];
//    static int[][] arr = new int[7][7];
    static int count = 0;

    public static void main(String[] args) {
        recursionBack(1,0,12,12);
//        recursionBack(1,0,3,3);

        System.out.println("移动12次后，可行路径为 = "+count);
    }
//360876    324932
    private static void recursionBack(int step,int direction,int x,int y){
        if(step > 12){
            //打印
//            show();
            //检查是否冲突
//            if(notConflict()) {
                count++;
//            }
            return;
        }

        for (int i=1; i<=4; i++){
            if(direction == 1 && i == 2 ||
                    direction == 2 && i == 1){
                continue;
            }
            if(direction == 3 && i == 4 ||
                    direction == 4 && i == 3){
                continue;
            }

            //描点
            switch (i){
                case 1:
                    //向上
                    if(arr[y-1][x] > 0){
                        continue;
                    }else {
                        arr[--y][x] = step;
                    }
                    break;
                case 2:
                    //向下
                    if(arr[y+1][x] > 0){
                        continue;
                    }else {
                        arr[++y][x] = step;
                    }
                    break;
                case 3:
                    //向左
                    if(arr[y][x-1] > 0){
                        continue;
                    }else {
                        arr[y][--x] = step;
                    }
                    break;
                case 4:
                    //向右
                    if(arr[y][x+1] > 0){
                        continue;
                    }else {
                        arr[y][++x] = step;
                    }
            }

            //递归下一步
            recursionBack(step+1,i,x,y);

            //回溯这一步，循环下一个方向
            arr[y][x] = 0;
            if(i == 1){
                ++y;
            }else if(i == 2){
                --y;
            }else if(i == 3){
                ++x;
            }else {
                --x;
            }
        }
    }

    private static boolean notConflict(){
        int sum = 78;
        for (int i=0; i<25; i++){
            for (int j=0; j<25; j++){
                if(arr[i][j] > 0){
                    sum -= arr[i][j];
                }
            }
        }
        return sum == 0;
    }

    private static void show(){
        for (int i=0; i<7; i++){
            for (int j=0; j<7; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
