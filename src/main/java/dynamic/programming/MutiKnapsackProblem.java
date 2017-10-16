import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Joseph on 2017/10/13.
 * 多重背包问题
     一个背包，承量有限为W，有n种物体，第i种物体，价值Vi，占用重量为 Wi，且有Ci件，选择物品若干放入背包，
     使得总重量不超过背包的承重。总价值最大？

 【分析】我们把第i种物品看成单个的，一个一个的，我们想想二进制，任何一个数都可以由二的幂表示。
 我们试试看，比如Ci  = 14，我们可以把它化成如下4个物品：

 重量是Wi，价值是Vi
 重量是2 * Wi , 价值是2 * Vi
 重量是4 * Wi , 价值是4 * Vi
 重量是7 * Wi , 价值是7 * Vi

 注意最后我们不能取，重量是8 * Wi , 体积是8 * Vi 因为那样总的个数是1 ＋ 2 ＋ 4 ＋ 8 ＝ 15个了，我们不能多取对吧？
 我们用这4个物品代替原来的14个物品，大家可以试试原来物品无论取多少个，重量和体积都可以靠我们这几个物品凑出来，
 这说明我们这种分配方式和原来是等价的。
 我们转化为一般方法，对于Ci ,我们的拆分方法是：
     1，2，4，8…… 同时Ci减去这些值，如果Ci不够减了，则把最后剩余的算上，同时我们体积也对应乘以这些系数。
     这样Ci个同一种物品，被我们变成了logCi个物品了。于是按照0-1背包的做法，时间复杂变为O(W * sigma(logCi))了，降了很多。

 输入:
     第1行，2个整数，N和W中间用空格隔开。N为物品的种类，W为背包的容量。(1 <= N <= 100，1 <= W <= 50000)
     第2 - N + 1行，每行3个整数，Wi，Pi和Ci分别是物品重量、价值和数量。(1 <= Wi, Pi <= 10000， 1 <= Ci <= 200)
 *
 */
public class MutiKnapsackProblem {

    static int num = 0, wei= 0;
    static int[] wi = null, pi = null, ci = null;
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        try {
            String[] s = reader.readLine().split(" ");
            num = Integer.parseInt(s[0]);
            wei = Integer.parseInt(s[1]);

            wi = new int[num];  //重量
            pi = new int[num];  //价值
            ci = new int[num];  //件数
            for(int i=0; i<num; i++){
                String[] values = reader.readLine().split(" ");
                wi[i] = Integer.parseInt(values[0]);
                pi[i] = Integer.parseInt(values[1]);
                ci[i] = Integer.parseInt(values[2]);
            }

            //采用01背包算法。将每1种物品的ci件转化为LogCi件，这样时间复杂度降低了
            for(int k=1; ; k++){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
