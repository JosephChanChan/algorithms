package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * lc 384 medium
 *
 * Analysis:
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2023/1/11
 */
public class ShuffleArray {

    /*
        洗牌算法
        任何一种排列等概率出现，等价于数组中任意元素Ai出现在任意位置的概率相同
        例如：1 2 3，随机打乱，第一次选到2的概率为1/3，放到下标0的位置概率为1/3，共1/9
        那么第二次选到3或1且放到下标0的概率也应该为1/3，第二次选到3或1概率为2/3*1/2，放到下标0概率为2/3*1/2，共1/9
        第三次选到3或1且放到下标0概率：第三次选到3或1概率为2/3*1/2*1，放到下标0概率为2/3*1/2，共1/9
     */

    int[] origin = null;
    Random random = new Random();

    public ShuffleArray(int[] nums) {
        origin = nums;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        int[] ans = new int[origin.length];

        List<Integer> a = Arrays.stream(origin).boxed().collect(Collectors.toList());
        List<Integer> b = new ArrayList<>(origin.length);
        for (int i = 0; i < origin.length; i++) {
            b.add(i);
        }

        while (a.size() > 0) {
            int pick = random.nextInt(a.size());

            // 时间O(n)，移除pick导致移动元素
            // int val = a.remove(pick);

            // O(1) 尾部元素替换到pick位置
            int val = a.get(pick);
            a.set(pick, a.get(a.size()-1));
            a.remove(a.size()-1);

            int idx = random.nextInt(b.size());
            int placed = b.get(idx);
            b.set(idx, b.get(b.size()-1));
            b.remove(b.size()-1);

            ans[placed] = val;

            //System.out.println("val="+val+" pick="+pick+" placed="+placed);
        }

        return ans;
    }
}
