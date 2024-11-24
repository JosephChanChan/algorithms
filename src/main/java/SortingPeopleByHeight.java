import java.util.Arrays;

/**
 * lc 2418 easy
 *
 * Analysis:
 * 时间复杂度：O(nlog(n))
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/9/26
 */
public class SortingPeopleByHeight {

    public String[] sortPeople(String[] names, int[] heights) {
        int n = heights.length;
        int[][] a = new int[n][2];
        for (int i = 0 ; i < n; i++) {
            a[i][0] = i;
            a[i][1] = heights[i];
        }
        Arrays.sort(a, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o2[1]-o1[1];
        });
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            ans[i] = names[a[i][0]];
        }
        return ans;
    }
}
