package sweep.line;

/**
 * lc 1419 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/9/17
 */
public class MinNumOfFrogCroaking {

    /**
         如果字符串是由若干 croak 组成的有效字符串，则里面的每个字符都是按照croak排列的
         只需要记录c字符出现的次数，因为一个青蛙只有发出完整croak之后才能发第2个c，这时c同时出现的次数则必须要这么多只青蛙同时发出c，前提是字符串是有效的

         最后思考了一下，这个思路其实本质就是扫描线，一个完整的croak蛙鸣声，就是一条线，
         那么每次扫描一条线结束时，统计一下和这条线相交的线数量，就是同时在叫的青蛙数量
     */

    int ans = 0;
    int count = 0;
    int[] h = new int[26];

    public int minNumberOfFrogs(String croakOfFrogs) {
        char[] c = croakOfFrogs.toCharArray();
        for (int i = 0; i < c.length; i++) {
            // 当前字符出现次数
            h[c[i]-'a']++;
            // 当前字符依赖的前置字符出现次数
            int pc = preChar(c[i]);
            // 如果前置字符出现次数不够，则必然不是一个有效的蛙鸣字符串
            if (pc > -1 && h[pc] < h[c[i]-'a']) {
                return -1;
            }
            // 结束字符出现，统计并行的c数量
            if (c[i] == 'k') {
                // 可以想象为一只青蛙叫完了，这个时候统计下有多少只青蛙还在叫的，那些正在叫的青蛙必然已经发出了c
                ans = Math.max(ans, h['c'-'a']);
                h['c'-'a']--;
                h['r'-'a']--;
                h['o'-'a']--;
                h['a'-'a']--;
                h['k'-'a']--;
            }
        }
        // 有效的蛙鸣字符串，最后必须所有字符都抵消了
        if (h['c'-'a'] > 0 || h['r'-'a'] > 0 || h['o'-'a'] > 0 || h['a'-'a'] > 0 || h['k'-'a'] > 0) {
            //System.out.println("no done");
            return -1;
        }
        return ans;
    }

    int preChar(char c) {
        if (c == 'c') {
            return -1;
        }
        if (c == 'r') {
            return 'c'-'a';
        }
        if (c == 'o') {
            return 'r'-'a';
        }
        if (c == 'a') {
            return 'o'-'a';
        }
        if (c == 'k') {
            return 'a'-'a';
        }
        return -1;
    }
}
