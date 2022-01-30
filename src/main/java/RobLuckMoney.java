import java.util.*;

/**
 * @author Joseph
 * @since 2021-07-09 11:45
 */
public class RobLuckMoney {

    /*
        给定n元有k人抢红包，每人至少1元，最多不超过0.3n元
     */
    double min = 1.0d, max ;

    public static void main(String[] args) {
        RobLuckMoney m = new RobLuckMoney();
        m.run(100.d, 20);
    }

    void run(double n, int k) {
        remainAvg(n, k);
    }

    void remainAvg(double money, int people) {
        if (money < people || people < 4)
            throw new IllegalArgumentException();

        // 转成分
        money = (money * 100.0d);
        max = money * 0.3d;

        double remainMoney = money, remainPeople = people;
        Random random = new Random();
        double[] ans = new double[people];
        for (int i = 0; i < people-1; i++) {
            // 随机范围 [1, 剩余金额平均值*2]
            double p = (double) random.nextInt((int) ((remainMoney / remainPeople) * 2.0d)) + 1;
            p = Math.max(p, 100);
            p = Math.min(p, max);
            remainMoney -= p;
            remainPeople--;
            ans[i] = p;
        }
        ans[people-1] = remainMoney;
        if (remainMoney > max) {
            // 最后一个红包溢出了，将差值d按照 [1, 剩余金额均值] 随机分
            ans[people-1] = max;
            int i = 0;
            double d = remainMoney-max;
            while (d > 0) {
                if (ans[i] < max) {
                    // 范围[1, min(max-a[i], d)]
                    double p = (double) random.nextInt(Math.min((int) (max-ans[i]), (int) d)) + 1;
                    ans[i] += p;
                    d -= p;
                }
                i = i+1 == people ? 0 : i+1;
            }
        }
        print(ans);
    }

    void randomPoint(double money, int people) {

    }

    void print(double[] ans) {
        double sum = 0.0d;
        for (int i = 0; i < ans.length; i++) {
            sum += ans[i]/100.d;
            System.out.print(ans[i]/100.d+"元 ");
        }
        System.out.println();
        System.out.println("共"+sum+"元");
    }















}
