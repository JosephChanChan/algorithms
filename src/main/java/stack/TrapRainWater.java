package stack;

import java.util.Stack;

/**
 * lc 42 hard
 *
 * Analysis：
 *  时间复杂度：递减栈 O(n)，双指针 O(n)
 *  空间复杂度：递减栈 O(n)，双指针 O(1)
 *
 * @author Joseph
 * @since 2021-02-27 19:52
 */
public class TrapRainWater {

    public int trap(int[] height) {
        return trap2(height);
    }

    /*
        递减栈可以帮助找到每个低位的左右边界。
        知道每个低位的左右边界后，选出较低的边界高度，较低边界高度和当前低位的高度差就是低位最多接水的高度。
        宽度就是左右边界宽度。
     */
    int decreaseStack(int[] height) {
        int ans = 0;
        Stack<Integer> s = new Stack<>();

        int n = height.length;
        if (n == 1) {
            return ans;
        }
        // 遍历每个位置，计算该位置接的水
        for (int i = 0; i < n; i++) {
            int h = height[i];
            if (s.isEmpty() || height[s.peek()] > h) {
                s.push(i);
                continue;
            }
            while (!s.isEmpty() && height[s.peek()] <= h) {
                int low = s.pop();
                // 当前低位没有左边界
                if (s.isEmpty()) {
                    break;
                }
                int min = height[s.peek()] > h ? h : height[s.peek()];
                int width = i - s.peek() - 1;
                ans += ((min - height[low]) * width);
            }
            s.push(i);
        }
        return ans;
    }

    /*
        双指针。l r分别往中间移动，l r往低处灌水。直到遇到比它高的停下。
        停下后判断当前l r哪个更低，低的往高的一方移动。
        因为把l r分别看做左右边界，就是更低的那个边界是l~r范围内灌水可达的最高线。
     */
    public int trap2(int[] height) {
        int n = height.length;
        int l = 0, r = n-1;

        int sum = 0;
        while (l < r) {
            if (height[l] <= height[r]) {
                if (height[l] > height[l+1]) {
                    sum += height[l]-height[l+1];
                    height[l+1] = height[l];
                }
                l++;
            }
            else {
                if (height[r] > height[r-1]) {
                    sum += height[r]-height[r-1];
                    height[r-1] = height[r];
                }
                r--;
            }
        }
        return sum;
    }
}
