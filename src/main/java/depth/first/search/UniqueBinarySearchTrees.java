package depth.first.search;

/**
 * @author Joseph
 * @since 2020-05-10 16:43
 *
 * lc 96 medium
 *
 * Question Description:
 *  参见 lc 96
 *
 * Analysis:
 *  求1~n组成的所有不同的二叉树方案数，这题跟 lc 95 一样思想。
 *  1 2 3 ... n 中每一个元素i都有可能做根，根据二叉树性质，左子树只可能是 1~i-1，右子树是i+1~n。
 *  设f(i)为i为根节点时的所有不同二叉树的数量。
 *  左子树是 l = sigma(1, i-1){f(k)}，同理右子树是 r = sigma(i+1, n){f(k)}
 *  当左右子树都存在时需要组合左右子树的每一种情况，左子树有l种情况，右子树有r种情况，则 f(i)=l*r
 *  这题如果这么做还不够，题的标签是DP，但是根据我想到的公式不是典型的DP方程，倒是很符合dfs思想。
 *  提交后遇到 n=19时就会超时。画出递归树会发现有冗余计算，尝试用备忘录剪枝，顺利AC
 *
 *  时间复杂度：O(n^2)+剪枝?
 *  空间复杂度：O(n^2)
 */
public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        UniqueBinarySearchTrees uniqueBinarySearchTrees = new UniqueBinarySearchTrees();
        int i = uniqueBinarySearchTrees.numTrees(5);
        System.out.println(i);
    }

    public int numTrees(int n) {
        int[][] cache = new int[n+2][n+2];
        for (int i = 0; i < n+2; i++) {
            for (int j = 0; j < n+2; j++) {
                cache[i][j] = -1;
            }
        }

        int[] f = dfs(1, n, cache);
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += f[j];
        }
        return sum;
    }

    private int[] dfs(int left, int right, int[][] cache) {
        if (left > right) {
            cache[left][right] = 0;
            return new int[0];
        }
        if (left == right) {
            cache[left][right] = 1;
            int[] trees = new int[1];
            trees[0] = 1;
            return trees;
        }

        int i, l, r, t;
        int[] f = new int[(right - left) + 1];
        for (i = left, t = 0; i <= right; i++) {
            if (cache[left][i-1] > -1) {
                l = cache[left][i-1];
            }
            else {
                int[] leftTrees = dfs(left, i-1, cache);
                l = sum(leftTrees);
            }

            if (cache[i+1][right] > -1) {
                r = cache[i+1][right];
            }
            else {
                int[] rightTrees = dfs(i+1, right, cache);
                r = sum(rightTrees);
            }

            cache[left][i-1] = l;
            cache[i+1][right] = r;
            if (l > 0 && r > 0) {
                f[t++] = l * r;
            }
            else if (l > 0) {
                f[t++] = l;
            }
            else if (r > 0) {
                f[t++] = r;
            }
        }
        return f;
    }

    private int sum(int[] trees) {
        int sum = 0;
        if (trees.length > 0) {
            for (int tree : trees) {
                sum += tree;
            }
        }
        return sum;
    }
}
