package dynamic.programming;

/**
 * @author Joseph
 * @since 2019/8/27 14:44
 */
public class HouseRobber {


    public static void main(String[] args) {
        // 状态是可能影响下一个步骤决策的因素

        int[] nums = {2,1,1,2};
        HouseRobber houseRobber = new HouseRobber();
        int profit = houseRobber.rob(nums);
        System.out.println(profit);
    }

    public int rob(int[] nums) {
        Integer step = 0, currentMoney = 0, max = 0, robed = 0;
        rob(nums, step, currentMoney, max, robed, true);
        return max;
    }


    private void rob(int[] arr, Integer step, Integer currentMoney, Integer max, Integer robed, Boolean doRob) {
        if (step == arr.length - 1) {
            return;
        }
        if (doRob) {
            if (1 == robed) {
                robed = 0;
                rob(arr, step + 1, currentMoney, max, robed, doRob);
                doRob = false;
                rob(arr, step, currentMoney, max, robed, doRob);
                return;
            }
            currentMoney = currentMoney + arr[step];
            if (max < currentMoney) {
                max = currentMoney;
            }
            robed = 1;
            rob(arr, step + 1, currentMoney, max, robed, doRob);
            doRob = false;
            rob(arr, step + 1, currentMoney, max, robed, doRob);
        }
        else {
            rob(arr, step + 1, currentMoney, max, robed, doRob);
            doRob = true;
            rob(arr, step, currentMoney, max, robed, doRob);
        }
    }

}
