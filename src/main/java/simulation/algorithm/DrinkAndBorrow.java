package simulation.algorithm;

/**
 * Created by Administrator on 2017/10/14 0014.
 *
 * 小明同学正进行大促销活动：5元一瓶饮料，4个瓶盖或者2个空瓶可以换1瓶饮料，现在给你20元，理论上最多可喝几瓶饮料？
     1.20元买4瓶，喝完后，有4个空瓶和4个盖；
     2.4个空瓶换2瓶，4盖换1瓶，喝完后，有3个空瓶和3个盖；
     3.2个空瓶换1瓶，喝完后，有2个空瓶和4个盖；
     4.2个空瓶换1瓶，4盖换1瓶，喝完后，有2个空瓶和2个盖；
     5.2个空瓶换1瓶，喝完后，有1个空瓶和3个盖；
     6.借1瓶，喝完后，有2个空瓶和4个盖；换2瓶，还1瓶，还剩1瓶
     7.借1瓶，2瓶喝完后，有2个空瓶和2个盖；换1瓶还上，还剩2个盖；
     8.借2瓶，喝完后，有2个空瓶和4个盖；换2瓶还上，不再剩下瓶和盖。
     所以共有：4+3+1+2+1+2+1+2＝16

    此题看了别人的解答后写了程序去计算，因为是模拟上面的逻辑的所以实际意义不是很大，属于模拟算法，锻炼一下思维逻辑。
 */
public class DrinkAndBorrow {

    public static void main(String[] args) {
        int count = 0;
        int money = 20;
        int bottle = 0;
        int cap = 0;
        int owe = 0;

        while (money > 0 || bottle > 0 || cap > 0){
            //正常流程
            if(bottle >= 2){
                count++;
                cap++;
                bottle -= 1;
            }else if(cap >= 4){
                count++;
                bottle++;
                cap -= 3;
            }else if(money >= 5){
                count++;
                bottle++;
                cap++;
                money -= 5;
            }

            //没钱了，空瓶和盖子也不够，但是空瓶或盖子大于0，开始借
            if(bottle > 0){
                owe++;
                count++;
                bottle++;
                cap++;
            }
            if(cap > 0){
                //cap = 1 || 2 || 3
                int t = 4 - cap;
                owe += t;
                count += t;
                bottle += t;
                cap += t;
            }

            //借了酒后，立即还上，以免透支
            while (owe > 0 && bottle >= 2 || cap >= 4){
                if(bottle >= 2){
                    int t = bottle/2;
                    bottle -= t * 2;
                    owe -= t;
                }else if(cap >= 4){
                    int t = cap/4;
                    cap -= t * 4;
                    owe -= t;
                }
            }

        }

        System.out.println("C = "+count);
        System.out.println("还剩 money = "+money);
        System.out.println("还剩 bottle = "+bottle);
        System.out.println("还剩 cap = "+cap);
        System.out.println("还欠 oew = "+owe);
    }
}
