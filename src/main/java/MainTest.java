import depth.first.search.Permutation2;
import tables.DesignTweet;

import java.util.*;

public class MainTest {

    /*
        ["getNewsFeed","unfollow","getNewsFeed","getNewsFeed","unfollow","getNewsFeed","getNewsFeed"]
        [[2],[2,1],[1],[2],[1,2],[1],[2]]
     */

    public static void main(String[] args) {
        Permutation2 m = new Permutation2();
        m.permuteUnique(new int[]{1,1,2});
    }

    static int[] calcSpace(int[] a, int[] b) {
        int x = a[0];
        int y = a[1];
        int x1 = a[0] + a[2];
        int y1 = a[1] - a[3];

        int j = b[0];
        int i = b[1];
        int j1 = b[0] + b[2];
        int i1 = b[1] - b[3];

        int lx = Math.max(x, j);
        int ly = Math.min(y, i);
        int rx = Math.min(x1, j1);
        int ry = Math.max(y1, i1);
        return new int[]{lx, ly, Math.abs(lx-rx), Math.abs(ly-ry)};
    }




}
