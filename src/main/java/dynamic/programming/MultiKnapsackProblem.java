package dynamic.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 多重背包问题
 *      一个背包，承量有限为W，有n种物体，第i种物体，价值Vi，占用重量为 Wi，且有Ci件，选择物品若干放入背包，
 *      使得总重量不超过背包的承重。总价值最大
 *
 *  【分析】我们把第i种物品看成单个的，一个一个的，我们想想二进制，任何一个数都可以由二的幂表示。
 *  我们试试看，比如Ci  = 14，我们可以把它化成如下4个物品：
 *
 *  重量是Wi，价值是Vi
 *  重量是2 * Wi , 价值是2 * Vi
 *  重量是4 * Wi , 价值是4 * Vi
 *  重量是7 * Wi , 价值是7 * Vi
 *
 *  注意最后我们不能取，重量是8 * Wi , 价值是8 * Vi 因为那样总的个数是1 ＋ 2 ＋ 4 ＋ 8 ＝ 15个了，我们不能多取对吧？
 *  我们用这4个物品代替原来的14个物品，大家可以试试原来物品无论取多少个，重量和价值都可以靠我们这几个物品凑出来，
 *  这说明我们这种分配方式和原来是等价的。
 *  我们转化为一般方法，对于Ci ,我们的拆分方法是：
 *      1，2，4，8…… 同时Ci减去这些值，如果Ci不够减了，则把最后剩余的算上，同时我们体积也对应乘以这些系数。
 *      这样Ci个同一种物品，被我们变成了logCi个物品了。于是按照0-1背包的做法，时间复杂变为O(W * sigma(logCi))了，降了很多。
 *
 *  输入:
 *      第1行，2个整数，N和W中间用空格隔开。N为物品的种类，W为背包的容量。(1 <= N <= 100，1 <= W <= 50000)
 *      第2 - N + 1行，每行3个整数，Wi，Pi和Ci分别是物品重量、价值和数量。(1 <= Wi, Pi <= 10000， 1 <= Ci <= 200)
 *
 * Created by Joseph on 2017/10/13.
 */
public class MultiKnapsackProblem {

    static int num = 0, wei= 0;
    static int[] wi = null, vi = null, ci = null;
    static List<Goods> calcedGoods = new ArrayList<>();
    //存放第i-1件和第i件物品的1~j重量的价值情况
    static int[][] form = null;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        try {
            String[] s = reader.readLine().split(" ");
            num = Integer.parseInt(s[0]);
            wei = Integer.parseInt(s[1]);

            wi = new int[num];  //重量
            vi = new int[num];  //价值
            ci = new int[num];  //件数
            for(int i=0; i<num; i++){
                String[] values = reader.readLine().split(" ");
                wi[i] = Integer.parseInt(values[0]);
                vi[i] = Integer.parseInt(values[1]);
                ci[i] = Integer.parseInt(values[2]);
            }

            //采用01背包算法。将每1种物品的ci件转化为LogCi件，这样时间复杂度降低了 O(w * sigma(logCi))
            for(int k=0; k<wi.length; k++){
                calcLog2N(ci[k],k,wi[k],vi[k]);
            }

            form = new int[2][wei+1];
            initializeForm();

            //将多重背包问题转化为01背包后。每一种物品原本有Ci件，现转为logCi件，然后全部物品加在一个List中，按照01背包算法计算
            /* 设前i件物品重量不超过j获得的最大价值 f(i,j)
            *   对于第i件物品取不取？
            *   如果取则 f(i,j) = f(i-1,j-wi) + vi
            *   不取则 f(i,j) = f(i-1,j)
            *   对于2种情况选取价值最大化   f(i,j) = max{f(i-1,j), f(i-1,j-wi) + vi}
            * */
            for(int i=0; i<calcedGoods.size(); i++){
                for(int j=1; j<=wei; j++){
                    Goods goods = calcedGoods.get(i);
                    if(j < goods.getWeight()){
                        form[1][j] = form[0][j];
                    }else {
                        int i1 = form[0][j];
                        int i1j = form[0][j-goods.getWeight()] + goods.getValue();
                        if(i1 < i1j){
                            form[1][j] = i1j;
                        }else {
                            form[1][j] = i1;
                        }
                    }
                }
                cleari1();
            }

            System.out.println(form[1][wei]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cleari1(){
        for(int i=0; i<form[0].length; i++){
            form[0][i] = form[1][i];
        }
    }

    private static void initializeForm(){
        for(int i=0; i<form.length; i++){
            form[0][i] = 0;
            form[1][i] = 0;
        }
    }

    private static void calcLog2N(int n,int goodNo,int wi,int vi){
        if(n < 0 || n == 0){
            return;
        }
        //1 2 4 8 16 32 64 128 ...
        int power = 1;
        while ((n - power) > 0){
            calcedGoods.add(assembleGoods(power,goodNo,wi,vi));
            n = n - power;
            power = power << 1;
        }

        //加入剩余的
        calcedGoods.add(assembleGoods(n,goodNo,wi,vi));
    }

    private static Goods assembleGoods(int power,int goodNo,int wi,int vi){
        Goods good = new Goods();
        good.setNo(goodNo);
        good.setWeight(wi * power);
        good.setValue(vi * power);
        return good;
    }

    private static class Goods{
        private int weight;
        private int value;
        //隶属第几种物品
        private int no;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }
}
