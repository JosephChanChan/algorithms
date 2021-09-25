package depth.first.search;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 93 medium
 *
 * Analysis:
 * 深搜所有方案。
 * 枚举3个整数，枚举每个整数分割点。最后一个整数不用枚举分割点，是一个整体
 * 剪枝：
 * 每一个整数做有效性检查
 * 每一次枚举整数分割点，对剩下字符数做检查，如果大于剩下整数最大长度或小于最小长度，舍弃
 *
 * 时间复杂度：O(2^n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-12 17:06
 */
public class RestoreIPAddresses {

    List<String> ans = new ArrayList();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) return ans;

        dfs(0, 1, s, new StringBuilder());
        return ans;
    }

    void dfs(int i, int k, String s, StringBuilder b) {
        if (k == 5) {
            ans.add(b.toString());
            return;
        }

        // 本层整数从i开始，枚举分割点
        for (int j = i; j < i+3 && j < s.length(); j++) {
            // 剪枝1
            if (i < j && s.charAt(i) == '0') return;

            // 剪枝2
            if (Integer.parseInt(s.substring(i, j+1)) > 255) return;

            // 剪枝3 剩下字符数超过剩下整数最大长度 || 小于剩下整数最小长度
            int max = (4-k)*3, min = 4-k;
            if (s.length()-(j+1) > max || s.length()-(j+1) < min) continue;

            int start = b.length();
            b.append(s.substring(i, j+1));
            if (k < 4) b.append(".");

            dfs(j+1, k+1, s, b);
            b.delete(start, b.length());
        }
    }
}
