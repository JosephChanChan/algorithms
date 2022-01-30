package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lc 199 medium
 *
 * Analysis:
 *  根右左遍历，保证每层先访问到最右侧节点。记录每层的最右侧节点。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：最坏时O(n)
 *
 * @author Joseph
 * @since 2021-07-03 20:50
 */
public class BinaryTreeRightSideView {

    Map<Integer, Integer> map = new HashMap();

    public List<Integer> rightSideView(TreeNode root) {
        if (null == root) return new ArrayList();

        dfs(root, 0);

        List<Integer> ans = new ArrayList(map.size());
        for (int i = 0; i < map.size(); i++) {
            ans.add(map.get(i));
        }
        return ans;
    }

    void dfs(TreeNode node, int k) {
        if (!map.containsKey(k)) {
            map.put(k, node.val);
        }
        if (null != node.right) {
            dfs(node.right, k+1);
        }
        if (null != node.left) {
            dfs(node.left, k+1);
        }
    }
}
