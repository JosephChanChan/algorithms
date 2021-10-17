package stack;

import java.util.Stack;

/**
 * lint code 285 medium
 *
 * Analysis:
 *  比较考思维，要认真读题发现只要是比当前楼高的楼都可以被看到，只要它前面没有被挡住
 * 递减栈可以保证，当前栈中元素都是递减的，中间不会有某些楼被它前面的楼挡住的情况。
 * 所以对于第i个楼，i左边的楼不管是比它矮，比它高，都可以看到，中间被遮挡的楼全部被排除了。
 * 栈中元素就是i能看到的。
 *
 * @author Joseph
 * @since 2021-04-01 16:41
 */
public class WatchingHighBuildings {

    public int[] tallBuilding(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) ans[i] = 1;

        // 下标
        Stack<Integer> leftSee = new Stack<>();
        leftSee.push(0);

        for (int i = 1; i < n; i++) {
            // 栈中的楼全都是当前楼可以看到的，因为都是递减的
            ans[i] += leftSee.size();
            // 当前楼会入栈，把i左边的比它矮或一样高的楼排除掉，因为这些楼会被i挡住，对于i右边的楼不可见
            while (!leftSee.isEmpty() && arr[i] >= arr[leftSee.peek()]) {
                leftSee.pop();
            }
            leftSee.push(i);
        }

        Stack<Integer> rightSee = new Stack<>();
        rightSee.push(n-1);
        for (int i = n-2; i >= 0; i--) {
            ans[i] += rightSee.size();
            while (!rightSee.isEmpty() && arr[i] >= arr[rightSee.peek()]) {
                rightSee.pop();
            }
            rightSee.push(i);
        }
        return ans;
    }
}
