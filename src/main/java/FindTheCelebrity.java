/**
 * lc 277 medium
 *
 * Analysis:
 * 根据名人定义，所有人都认识他，名人只认识自己
 * 考虑任意的二人组，a b
 * a->b a认识b，a自己不是名人
 * a 不认识b，b不是名人
 * 对于任意的二元关系，每一次比较都可以排除一个人，留下一个可能的人
 * 从左到右扫一遍。
 * 假设存在一个名人，名人是i，1~i-1之前，不管是谁，遇到i，问 j->i ? j肯定被淘汰
 * 留下i，继续扫 i+1~n，因为 i对于(i+1~n)，i只认识自己，所以i+1~n 的人全部被淘汰
 *
 * 假设不存在名人，并且数组中每个人都只认识自己，扫下来可能剩下最开始的那个人
 * 所以最后还要再扫一遍，看所有人是否认识i，以及i是否只认识自己
 *
 * 这题一个人可以认识多个人
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-04 16:52
 */
public class FindTheCelebrity {

    public int findCelebrity(int n) {
        int i = 0;
        for (int j = 1; j < n; j++) {
            if (knows(i, j)) {
                i = j;
            }
        }

        // 到这里只能说淘汰了n-1个人，留下最后一个候选人，但是他可能不是名人，比如i只认识自己
        // 选出一个候选人，如果候选人不认识自己，那GG
        if (!knows(i, i)) return -1;

        int k = 0;
        for (int j = 0; j < n; j++) {
            if (j != i) {
                if (knows(j, i)) k++;
                // 在n-1个人中，如果i认识除了自己外还认识其他人，GG
                if (knows(i, j)) return -1;
            }
        }
        // 有n-1个人都认识i且i也认识自己
        return k == n-1 ? i : -1;
    }

    boolean knows(int i, int j) {
        return true;
    }
}
