package greedy.algorithm;

import sort.QuickSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
 *  这种思路看起来没问题吧？抱歉，是错的... 错在和第2种策略是类似的。
 *
 *  下面讲正确的贪心策略：
 *  结束时间越早的活动优先。这个策略是有效的. 贪心策略的正确性证明可以通过数学归纳法证明.
 *  设 S[] 集合为按照活动结束时间升序排列的原集合。
 *  A[] 为最优解，同样也是按照活动结束时间升序排列的集合。
 *  P[] 为按照 “结束时间越早的活动优先” 的贪心策略选择出来的集合。
 *  f(i) 表示某个活动结束时间.
 *  现在考虑 A[0] = P[0] 吗？ 如果 A[0] = P[0] OK，很好。
 *  如果 A[0] != P[0] ，那么 f(P[0]) <= f(A[0]).(一开始我在这里卡了很久, 不明白为什么肯定小于等于?)
 *  P[0] 按照我们的贪心策略从S中选出来的，如果出现 Ai 和 Pi f(Ai) < f(Pi) 那么按照策略肯定选的是Ai，
 *  所以 f(P[0]) > f(A[0]) 有悖贪心策略，不存在的。
 *  那么 P[0] 就可以替代 A[0] ，P[0]也一定小于A[1]...A[m]的开始时间。
 *  这样就与设定矛盾，A[] 就不是最优解了！ 所以A[0] != P[0]的情况不存在！
 *  依次类推，证明了 P[0] = A[0] 可以扩展 P[k] 与 A[k] 的关系。同样的证明方法。
 *  至此，我们证明了按照策略选出来的P[]与最优解A[]是相同的, 贪心策略得证.
 *
 * created by Joseph
 * at 2018/7/25 17:49
 */
public class ActivePlan {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1 << 16);
        int activeCount = 0, maxAvailableCount = 0;
        Active[] activeArray = null;
        try {
            activeCount = Integer.parseInt(reader.readLine());
            String[] params ;
            activeArray = new Active[activeCount];
            for (int i = 0; i < activeCount; i++){
                params = reader.readLine().split(" ");
                Active active = new Active();
                active.setStartTime(Integer.parseInt(params[0]));
                active.setEndTime(Integer.parseInt(params[1]));
                active.setTime(Math.abs(active.getStartTime() - active.getEndTime()));
                activeArray[i] = active;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        /*
            贪心策略：每一次选择结束时间越早的活动且与前面活动相容的。
            为此我们需要先将活动排序
         */
        QuickSort.doQuickSort(activeArray, 0, activeArray.length - 1);

        int perEndTime = Integer.MIN_VALUE;

        for (int k = 0; k < activeCount; k++){
            if (activeArray[k].startTime >= perEndTime){
                maxAvailableCount++;
                perEndTime = activeArray[k].endTime;
            }
        }

        System.out.println(maxAvailableCount);
    }

    static class Active implements Comparable {
        private int startTime;
        private int endTime;
        private int time;

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

        @Override
        public int compareTo(Object o) {
            Active active = (Active) o;
            if (endTime > active.getEndTime()){
                return 1;
            }
            else if (endTime == active.getEndTime()){
                return 0;
            }
            else {
                return -1;
            }
        }
    }

}
