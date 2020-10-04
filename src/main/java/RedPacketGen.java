import java.util.Random;

/**
 * 现有一个需求：
 *  10元抽一次红包，要求后台控制红包生成的概率。
 *  假设
 *  1~5元红包生成概率 20%
 *  5~10元红包生成概率 50%
 *  10~15元红包生成概率 20%
 *  15~20元红包生成概率 10%
 *  以上概率自由设置
 *
 *  Created by Joseph on 2017/7/31.
 */
public class RedPacketGen {

    private static final double fifteenReachTwentyDollars = 0.1;
    private static final double tenReachFifteenDollars = 0.3;
    private static final double oneReachFiveDollars = 0.5;
    private static final double fiveReachTenDollars = 1;

    private static int tenReachFifteen = 0;
    private static int fifteenReachTwenty = 0;
    private static int oneReachFive = 0;
    private static int fiveReachTen = 0;

    private static double tenReachFifteens = 0;
    private static double fifteenReachTwentys = 0;
    private static double oneReachFives = 0;
    private static double fiveReachTens = 0;

    public static void main(String[] args){
        Random random = new Random();
        Random moneyGen = new Random();
        int count = 100000;
        double luck = 0;
        double base = 0;
        double sum = 0;
        for(int i=0; i<count; i++){
            double rand = random.nextInt(100) * 1.0f / 100d;
            luck = 0;
            base = 0;
            if(rand <= fifteenReachTwentyDollars){
                fifteenReachTwenty++;
                base = 15;
                luck = base + moneyGen.nextInt(500) / 100d;
                fifteenReachTwentys += luck;
            }else if(rand <= tenReachFifteenDollars){
                tenReachFifteen++;
                base = 10;
                luck = base + moneyGen.nextInt(500) / 100d;
                tenReachFifteens += luck;
            }else if(rand <= oneReachFiveDollars){
                oneReachFive++;
                base = 1;
                luck = base + moneyGen.nextInt(400) / 100d;
                oneReachFives += luck;
            }else {
                fiveReachTen++;
                base = 5;
                luck = base + moneyGen.nextInt(500) / 100d;
                fiveReachTens += luck;
            }

            sum += luck;
            System.out.println("本次随机数为="+rand*100.0f+" 产生的红包为="+luck);
        }

        System.out.println("本次算法运行了"+count+"次。统计到付出去的奖金和为"+sum);
        System.out.println("1到5元区间共产生了 "+oneReachFive+" 次。付出奖金为 "+oneReachFives);
        System.out.println("5到10元区间共产生了 "+fiveReachTen+" 次。付出奖金为 "+fiveReachTens);
        System.out.println("10到15元区间共产生了 "+tenReachFifteen+" 次。付出奖金为 "+tenReachFifteens);
        System.out.println("15到20元区间共产生了 "+fifteenReachTwenty+" 次。付出奖金为 "+fifteenReachTwentys);
    }

}
