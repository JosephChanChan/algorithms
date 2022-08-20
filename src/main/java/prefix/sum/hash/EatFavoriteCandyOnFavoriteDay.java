package prefix.sum.hash;

/**
 * lc 1744 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/3/20
 */
public class EatFavoriteCandyOnFavoriteDay {

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        /**
            s(j)为0~j类糖的糖果数量
            Cmax为每天最多能吃的糖数

            根据题目条件分析，可推出2个成功条件：
            1.需要在0~i-1天把0~j-1类糖吃完
            i-1*Cmax >= s(j-1)
            如果吃不完，则期望剩余糖数<Cmax
            remain < Cmax

            上面条件保证按照最大速度吃，可以在第i天前吃到第j类糖至少一颗
            但是有可能0~j类糖数在第i天前被吃完
            2.按最慢速度吃，0~j类糖数能在第i天有剩余
            s(j) >= i

            除了上面的逻辑分析，还可以画数轴，求[最慢速度吃~最快速度吃]这个区间是否和j类糖区间有交集
            有交集则可以在第i天吃到j类糖
         */
        int n = candiesCount.length;
        long[] f = new long[n];
        f[0] = candiesCount[0];
        for (int i = 1; i < n; i++) {
            f[i] = f[i-1] + candiesCount[i];
        }

        boolean[] ans = new boolean[queries.length];
        for (int k = 0; k < queries.length; k++) {
            // 期望在第i天吃到j类糖至少一颗
            long i = queries[k][1];
            int j = queries[k][0];
            // 每天最多吃c颗糖
            long c = queries[k][2];

            if (!(f[j] >= i+1)) {
                ans[k] = false;
                //System.out.println(j+"类糖，不够吃"+i+1+"天");
                continue;
            }

            if (j == 0) {
                ans[k] = true;
                //System.out.println("第一种糖最慢速度吃，在第"+(i+1)+"天能吃到");
                continue;
            }

            long remain = f[j-1] - i*c;
            if ((i * c) >= f[j-1] || remain < c) {
                //System.out.println("第"+j+"种糖，最快速度吃能吃到");
                ans[k] = true;
                continue;
            }
            //System.out.println("第"+j+"种糖，最快速度吃，都不能吃到 "+f[j-1]+" "+i*c+" remain="+remain);
        }
        return ans;
    }
}
