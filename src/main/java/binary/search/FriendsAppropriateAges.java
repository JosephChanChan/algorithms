package binary.search;

import java.util.Arrays;

/**
 * lc 825 medium
 *
 * Analysis:
 * 时间复杂度：O(n*log(n))
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/2/20 10:22 PM
 */
public class FriendsAppropriateAges {

    int n ;
    int[] a ;

    public int numFriendRequests(int[] ages) {
        /**
             排序后二分，枚举x对每个x找它的一个边界y
             这个y的左边是能够对x加好友的，y的右边是不能对x加好友的
         */
        a = ages;
        n = a.length;
        Arrays.sort(a);

        int ans = 0;

        for (int i = 0; i < n; i++) {
            // 对于i找一个可以给它发请求的边界好友
            int l = searchL(i);
            int r = searchR(i);
            int t1 = (i - l);
            int t2 = (r - i);
            int add = t1+t2;
            //System.out.println("i="+i+" add="+add+" t1="+t1+" t2="+t2);
            ans += add;
        }
        return ans;
    }

    int searchR(int i) {
        int l = i, r = n-1, mid ;
        while (l + 1 < r) {
            mid = (l+r) >> 1;
            // mid是不是可以给i发好友？
            if (!noJoin(mid, i)) {
                l = mid;
            }
            else {
                r = mid;
            }
        }
        if (!noJoin(r, i)) {
            return r;
        }
        return l;
    }

    int searchL(int i) {
        int l = 0, r = i, mid ;
        while (l + 1 < r) {
            mid = (l+r) >> 1;
            // mid是不是可以给i发好友？
            if (!noJoin(mid, i)) {
                r = mid;
            }
            else {
                l = mid;
            }
        }
        if (!noJoin(l, i)) {
            return l;
        }
        return r;
    }

    boolean noJoin(int x, int y) {
        return
                (a[y] > 100 && a[x] < 100) ||
                        (a[y] > a[x]) ||
                        (a[y] <= ((int) (a[x] * 0.5d) + 7));
    }
}
