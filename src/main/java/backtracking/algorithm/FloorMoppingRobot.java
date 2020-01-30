package backtracking.algorithm;

/**
 * 现在有很多制造商都在卖扫地机器人，它非常有用，能为忙碌的我们分担家务负担。
 * 不过我们也很难理解为什么扫地机器人有时候会反复清扫某一个地方。
 * <p>
 * 假设有一款不会反复清扫同一个地方的机器人，它只能前后左右移动。举个例子，
 * 如果第1 次向后移动，那么连续移动3 次时，就会有以下9 种情况（ 图6 ）。
 * 又因为第1 次移动可以是前后左右4 种情况，所以移动3 次时全部路径有9×4 ＝ 36 种。
 * <p>
 * 0       0       0       0           0     3  0      0 3    0            0
 * 1       1       1       1 2      2  1     2  1      1 2    1 2 3    3 2 1
 * 2       2 3   3 2         3      3
 * 3
 * <p>
 * 求这个机器人移动12 次时，有多少种移动路径？
 * <p>
 * 算法：
 * 每一步理论上有4个方向可走，加条件限制后需要判断：
 * 1，不能走上一步相反的方向
 * 2，不能走已走过的坐标
 * 一层循环模拟机器人走的方向，过程加判断条件，递归下一步。
 * 当下一步走完后，回溯当前步，换另外的方向继续递归。
 * <p>
 *
 * Created by Joseph on 2017/11/10.
 */
public class FloorMoppingRobot {


    int[][] arr;
    int count = 0, column = 0;

    public static void main(String[] args) {
        FloorMoppingRobot robot = new FloorMoppingRobot();
        robot.column = 25;
        robot.arr = new int[robot.column][robot.column];

        // 将原点置为已访问过，原先以为原点也可以访问，坑了我好久。
        int originalX = 12, originalY = 12;
        robot.arr[originalY][originalX] = -1;

        robot.recursionBack(1, originalX, originalY);

        System.out.println("移动12次后，可行路径为 = " + robot.count);
    }

    private void recursionBack(int step, int x, int y) {
        if (step > 12) {
            /*show();*/
            count++;
            return;
        }

        for (int i = 1; i <= 4; i++) {
            // 描点
            switch (i) {
                default:
                    throw new RuntimeException("没有方向了！");
                case 1:
                    //向上
                    if (arr[y - 1][x] != 0)
                        continue;
                    else
                        arr[--y][x] = step;
                    break;
                case 2:
                    //向下
                    if (arr[y + 1][x] != 0)
                        continue;
                    else
                        arr[++y][x] = step;
                    break;
                case 3:
                    //向左
                    if (arr[y][x - 1] != 0)
                        continue;
                    else
                        arr[y][--x] = step;
                    break;
                case 4:
                    //向右
                    if (arr[y][x + 1] != 0)
                        continue;
                    else
                        arr[y][++x] = step;
            }

            // 递归下一步
            recursionBack(step + 1, x, y);

            // 回溯这一步，循环下一个方向
            arr[y][x] = 0;
            if (i == 1) {
                ++y;
            } else if (i == 2) {
                --y;
            } else if (i == 3) {
                ++x;
            } else {
                --x;
            }
        }
    }

    private boolean notConflict() {
        int sum = 78;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] > 0) {
                    sum -= arr[i][j];
                }
            }
        }
        return sum == 0;
    }

    private void show() {
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] > 0) {
                    if (arr[i][j] < 10)
                        System.out.print(arr[i][j] + " ");
                    else
                        System.out.print(arr[i][j]);
                }
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
