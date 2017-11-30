package main.java.dynamic.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 2017/11/24.
 *  子序列个数
 *  子序列的定义：对于一个序列a=a[1],a[2],......a[n]。
 *  则非空序列a'=a[p1],a[p2]......a[pm]为a的一个子序列，其中1<=p1<p2<.....<pm<=n。
    例如4,14,2,3和14,1,2,3都为4,13,14,1,2,3的子序列。对于给出序列a，有些子序列可能是相同的，
    这里只算做1个，请输出a的不同子序列的数量。由于答案比较大，输出Mod 10^9 + 7的结果即可。

    穷举算法当然是N个for循环了，每层取0和1。2个状态（0代表不取a[i]，1则相反），然后按照构成的序列判断之前是否计算过相同的子序列。
    这样的时间复杂度是 2^n 。n很大的话，基本无用的算法。

    状态：
        设 dp(i) 是序列中前i个数构成的子序列个数。
    重叠子问题：
        求dp(i)和求dp(j) j<i 是同一性质的问题。
    最优子结构：
        j=i-1 dp(j) 假设有m个子序列，对于dp(i)的话应该是dp(j)*2，即把a[i]是否接到dp(j)所构成的子序列中去
        如 {1 2 3 4} j=3 i=4  dp(j) = {1} {2} {3} {1 2} {1 3} {2 3} {1 2 3} 7个子序列。
        考虑是否把 4 接到这样每个子序列后面去？
        如果接则有 {1 4} {2 4} {3 4} {1 2 4} {1 3 4} {2 3 4} {1 2 3 4} 7个。
        如果不接则 dp(i)=dp(j)
        所以 dp(i) = dp(j)*2
        先求出子问题，由子问题进而求解母问题，符合动态规划思想，但是这里不是最优化问题（子问题没有最优化的性质）
    边界：
        dp(0) = 1 （空集）
    以上是对于前i个数中没有相同数的算法。如果存在重复的数呢？
    a[j]=a[i] j<i。那么计算dp(i)时就会算多了1次，因为a[i]可以代替a[j]。被重复计算了多少的子序列个数？
    a[i]可以替代a[j]，所以dp(j)时 序列中前j项构成的子序列集合中以a[j]结尾的都可以被a[i]替代，
    那么dp(i)时，因为dp(i)包含dp(j)，所以以a[j]结尾的子序列会被重复计算，有多少a[j]结尾的子序列？
    考虑dp(j-1) 如果我们把 a[j] 接到dp(j-1)的序列中去那就是以a[j]结尾了，所以有dp(j-1)个数。
    dp(i) = dp(i-1)*2 - dp(j-1)

    时间复杂度 O(n) 空间复杂度 O(max{a[i]})+O(n) 或 O(n)(哈希表存储下标)
 */
public class SubSequenceNumber {

    static int[] arr = null;
    static long[] dp = null;
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        int n = 0;
        try {
            n = Integer.parseInt(reader.readLine());
            arr = new int[n+1];
            dp = new long[n+1];
            //第0个 存一个空集
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //输出a的不同子序列的数量Mod 10^9 + 7。
        final long m = (long)Math.pow(10,9)+7;

        //存放重复出现的数的下标 key=数 value=下标
        Map<Integer,Integer> indexs = new HashMap<>();

        /*
            dp(i) = dp(j)*2
            a[j]=a[i]   dp(i) = dp(i-1)*2 - dp(j-1)
        */
        for (int i=1; i<arr.length; i++){
            //a[i]是否在之前出现过
            if(indexs.containsKey(arr[i])){
                dp[i] = (dp[i-1]*2 - dp[indexs.get(arr[i])-1] + m) % m;
            }
            else {
                dp[i] = dp[i-1]*2 % m;
            }
            indexs.put(arr[i],i);
        }

        //减除一个空集
        System.out.println(dp[n]-1);
    }

}
