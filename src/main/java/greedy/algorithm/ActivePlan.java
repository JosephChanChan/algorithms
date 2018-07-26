package greedy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Question Description:
 * 有若干个活动，第i个开始时间和结束时间是[Si,fi)，只有一个教室，活动之间不能交叠，求最多安排多少个活动？
 *
 * Analysis:
 * 先举例2个很容易想到的策略，这2个策略很容易举出反例。
 * 1, 开始最早的活动优先，目标是想尽早结束活动，让出教室。但假设有这样的数据 [0, 100] [10, 20] [24, 50]
 * 这种策略只能执行第一个活动. 最优的是执行第2,3活动。
 *
 * 2，短活动优先， 目标也是尽量空出教室。
 * 但是不难构造如下反例： [0,5) [5,10) [3, 7), 这里[3,7)最短，但如果我们安排了[3,7)，其它两个无法安排了。
 * 但是最优解显然是安排其它两个，而放弃[3,7)，可见这个贪心策略也是不行的。
 *
 * 自己的分析：有N个活动，它们的开始时间和结束时间都是已确定了的，所以存在一个最优解 A[]。
 * 这个A[]包含了从N个活动挑选k个活动，时间不重叠。
 * 枚举每一个活动，以这一个活动作为活动链的开头，它结束后的下一个活动肯定是从剩下N-1个活动中挑选，开始时间大于前一个结束时间。
 * 假设有 x 个活动满足，在其中挑选出时间最短的一个。依次类推...
 * 直到结束后，每一个活动的活动链都枚举过了，并且每一条活动链都是以它开头最优的，只需要从中选出最长的一条链路，即是最多活动。
 *
 * 伪代码:
 *  for 1: 枚举每一个活动开头
 *      Ai = Active[i];// 以这一个活动开头找到最优的一条活动链
 *      for 2: 对于这条活动链长度是未知的，Aj ... An 最大可能为N，所以要遍历完活动数组...
 *          for 3: 从剩下的N-j个活动中找下一个开始时间大于前一个结束时间的活动, 并选出时间最短的.
 *  时间复杂度:
 *  for 1: N次
 *   for 2: 最大可能为 N-1
 *    for 3: 不做优化的情况下，最大为 N
 *
 *
 * created by Joseph
 * at 2018/7/25 17:49
 */
public class ActivePlan {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1 << 16);
        int activeCount = 0;
        List<Active> activeList = new ArrayList<>();
        try {
            activeCount = Integer.parseInt(reader.readLine());
            String[] params ;
            for (int i = 0; i < activeCount; i++){
                params = reader.readLine().split(" ");
                Active active = new Active();
                active.setSerialNo(i);
                active.setStartTime(Integer.parseInt(params[0]));
                active.setEndTime(Integer.parseInt(params[1]));
                active.setTime(Math.abs(active.getStartTime() - active.getEndTime()));
                activeList.add(active);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        /*
            伪代码:
              for 1: 枚举每一个活动开头
                  Ai = Active[i];// 以这一个活动开头找到最优的一条活动链
                  for 2: 对于这条活动链长度是未知的，Aj ... An 最大可能为N，所以要遍历完活动数组...
                      for 3: 从剩下的N-j个活动中找下一个开始时间大于前一个结束时间的活动, 并选出时间最短的.
         */
        int maxActiveCount = 0;

        for (int i = 0; i < activeCount; i++){
            Set<Integer> serialSet = new TreeSet<>();

            Active active = activeList.get(i);
            serialSet.add(active.getSerialNo());
            int preEndTime = active.getEndTime();

            for (int j = 0; j < activeCount; j++){
                Active min = null;

                for (int k = 0; k < activeCount; k++){
                    Active temp = activeList.get(k);
                    if (
                            temp.getStartTime() >= preEndTime &&
                            !serialSet.contains(temp.getSerialNo())
                       )
                    {
                        if (null == min){
                            min = temp;
                        }
                        else if (temp.getTime() < min.getTime()){
                            min = temp;
                        }
                    }
                }

                // 在活动数组中已经找不到合适的下一个活动了
                if (null == min){
                    break;
                }
                // 找到一个时间最短且可以衔接上上一个活动的活动
                else {
                    serialSet.add(min.getSerialNo());
                    preEndTime = min.getEndTime();
                }
            }

            if (serialSet.size() > maxActiveCount){
                maxActiveCount = serialSet.size();
            }
        }

        System.out.println(maxActiveCount);
    }


    static class Active {
        private int serialNo;
        private int startTime;
        private int endTime;
        private int time;

        public int getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(int serialNo) {
            this.serialNo = serialNo;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
