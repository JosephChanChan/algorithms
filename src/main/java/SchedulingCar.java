import java.util.Scanner;

/**
 * 美团2020校招笔试
 *
 * Question Description:
 *  小团是美团汽车租赁公司的调度师，某个时刻A和B两地都向该公司提交了租车的订单，分别需要a和b辆汽车。
 *  此时，公司的所有车辆都在外运营，通过北斗定位，可以得到所有车辆的位置，小团分别计算了每辆车前往A地和B地完成订单的利润。
 *  作为一名精明的调度师，当然是想让公司的利润最大化了。
 * 请你帮他分别选择a辆车完成A地的任务，选择b辆车完成B地的任务。使得公司获利最大,每辆车最多只能完成一地的任务。
 *
 * 输入第一行包含三个整数n，a，b，分别表示公司的车辆数量和A，B两地订单所需数量,保证a+b<=n。(1<=n<=2000)
 * 接下来有n行，每行两个正整数x，y，分别表示该车完成A地任务的利润和B地任务的利润。
 *
 * 输出仅包含一个正整数，表示公司最大获得的利润和。
 *
 * 样例输入
 * 5 2 2
 * 4 2
 * 3 3
 * 5 4
 * 5 3
 * 1 5
 * 样例输出
 * 18
 *
 * @author Joseph
 * @since 2020-08-15 17:40
 */
public class SchedulingCar {

    static int n, a, b ;
    static int[][] profit ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] arr = line.split(" ");
        n = Integer.parseInt(arr[0]);
        a = Integer.parseInt(arr[1]);
        b = Integer.parseInt(arr[2]);

        int i = 0;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.equals(" ")) continue;
            if (line.equals("")) break;
            arr = line.split(" ");
            int ap = Integer.parseInt(arr[0]);
            int bp = Integer.parseInt(arr[1]);
            profit[i++][0] = ap;
            profit[i][1] = bp;
        }

        /*
            f(i, a, b) 是前i车去a/b地获取到的最大利润
            f(i, a, b) = max{f(i-1, a, b), f(i-1, a-1, b) + v[ap], f(i-1, a, b-1)+ v[bp]}
            边界：
                f(i, a, b) = 0  i=a=b=0

         */
        int[][] totalProfit = new int[n][2];
        // 初始化，第1车去a地和b地的利润
        totalProfit[0][0] = profit[0][0];
        totalProfit[0][1] = profit[0][1];

        // each car
        for (i = 1; i < n; i++) {
            // 第i车不选

        }

    }
}
