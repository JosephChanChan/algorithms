package math;

/**
 * lc 264 medium
 *
 * Analysis:
 * time: O(n)
 * space: O(1)
 *
 * @author Joseph
 * @since 2023/7/29
 */
public class UglyNum2 {

    public static void main(String[] args) {
        UglyNum2 m = new UglyNum2();
        m.nthUglyNumber(10);
    }

    int[] f ;

    public int nthUglyNumber(int n) {
        f = new int[n];
        /*
            丑数要满足两个条件：
                1.能被2、3、5整除
                2.没有其它质因数
            假设x是丑数，则2x 3x 5x也肯定能被2 3 5整除
            且x本身是丑数保证了没其它质因数，因此2x 3x 5x也可以是丑数，因为只是多了2 3 5因子
            假设x是最后一个丑数，它肯定是前面的某个丑数lastX乘2/3/5得到
            f(n)= min{a*2, b*3, c*5} | 2a>f(n-1) 3b>f(n-1) 5c>f(n-1)
        */
        int i = 0, j = 0, k = 0;
        int[] f = new int[n];
        f[0]=1;
        for (int p = 1; p < n; ) {
            int a = f[i]*2;
            int b = f[j]*3;
            int c = f[k]*5;
            int newUg = Math.min(a, Math.min(b, c));
            if (newUg == a) {
                i++;
            }
            if (newUg == b) {
                j++;
            }
            if (newUg == c) {
                k++;
            }
            if (newUg > f[p-1]) {
                f[p++] = newUg;
            }
        }
        /*for ( i = 0; i < n; i++) {
            System.out.print(f[i]+" ");
        }*/
        return f[n-1];
    }
}
