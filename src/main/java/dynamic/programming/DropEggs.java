package dynamic.programming;

/**
 * lc 887 hard
 *
 * Analysis:
 *
 * 时间复杂度：dp1 O(n*Answer) dp2 O(k*n^2)
 * 空间复杂度：dp1 O(nk)       dp2 O(nk)
 *
 * @author Joseph
 * @since 2021-04-09 23:21
 */
public class DropEggs {

    public int superEggDrop(int k, int n) {
        return prefectDP(n, k);
    }

    int prefectDP(int n, int k) {
        /*
            f(t,j)为有t次机会和j个鸡蛋，最多能够测出多少楼的临界值
            固定j为给定的k个鸡蛋，当f(t,j)>=n，找到最小的t就是答案

            不用思考具体在哪楼扔鸡蛋，只需关注随便在一层x楼扔，会发生什么以及能测出多少楼

            如果鸡蛋碎了，x以上的楼层不用再测
            还可以用t-1次机会和j-1个鸡蛋测出f(t-1,j-1)层楼的临界值

            如果没碎，当前楼x以及它下面的楼f(t-1,j-1)层楼都被确定不是临界值，现在测出来f(t-1,j-1)+1层楼高。
            那么还剩t-1次机会和j个鸡蛋，把当前x层看做地面，可以继续测f(t-1,j)层楼高

            所以综上，当有t词机会和j个鸡蛋，一共能确定f(t,j)=1+f(t-1,j-1)+f(t-1,j)

            边界：
            f(1,j)=1
            f(t,1)=t
        */
        int[][] f = new int[n+1][k+1];

        for (int t = 1; t <= n; t++) {
            for (int j = 1; j <= k; j++) {
                if (t == 1) {
                    f[t][j] = 1;
                }
                else if (j == 1) {
                    f[t][j] = t;
                }
                else {
                    f[t][j] = 1 + f[t-1][j-1] + f[t-1][j];
                }
                // 当枚举到t次机会，j个鸡蛋能测出来的楼高 >= 题目要求的楼高时，此时的t是最少扔的次数
                if (f[t][j] >= n) return t;
            }
        }
        return -1;
    }

    // 朴素DP，TLE O(k*n^2) pass 84/121 cases
    int plainDP(int n, int k) {
        /*
            f(i,j)为i层楼扔j个鸡蛋，最少需要多少次确定出临界值p
            枚举k，在第k层楼扔，如果碎了，证明p在1~k-1层楼
            没碎，证明p在k~i层楼中。两种情况对应的两种子问题：
            用j-1个鸡蛋测试k-1层楼需要的最少次数，f(k-1,j-1)
            用j个鸡蛋测试i-k层楼需要的最少次数，f(i-k,j)
            楼层数都变少了，具备DP方向性
            现在面对两个子问题怎么转移到现在的状态？
            如果知道当前在k层扔鸡蛋碎不碎就可以决定去左边、还是右边
            但是现在不知道在k层扔会不会碎，所以需要两种情况都考虑
            f(k-1,j-1)=a
            f(i-k,j)=b
            设 a < b
            如果p在左边，那么取a是对的。但如果p在右边？取a就不对了，也就是最坏的情况下需要b次在右边找出p
            所以面对两种情况，最坏的情况下就是需要两者间最多次的操作数确定p
            f(i,j)=min{ max{f(k-1,j-1), f(i-k,j)} +1 | 1 <= k <= i}

            边界：
            f(1,j)=1
            f(i,1)=i
        */

        int[][] f = new int[n+1][k+1];
        for (int j = 1; j <= k; j++) {
            f[1][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            f[i][1] = i;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (int x = 1; x <= i; x++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[x-1][j-1], f[i-x][j]) + 1);
                }
            }
        }
        return f[n][k];
    }
}
