package greedy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Question Description:
 * 有若干个活动，第i个开始时间和结束时间是[Si,fi)，活动之间不能交叠，要把活动都安排完，至少需要几个教室？
 *
 * Analysis:
 * 这个问题和之前的问题本质差别是什么？
 * 尝试抽象一下。第一个问题是 有限的资源，在给定的所有活动中，选择所有可以安排的活动。第二个问题，将所有活动用最少的资源处理完。
 * 用第一个问题的贪心策略可以解决？尽量将一个教室安排多的活动？答案是不行，反例： A：[1,2)  B：[1,4) C：[5,6) D：[3，7)
 *
 * 假设最优解是 k, 至少需要 k 间教室安排所有活动. 现在选择这些活动时，刚开始 k 是0.
 * 如果现有的k个教室里可以安排下这个活动则直接加进k个教室中。否则，代表什么？代表k个教室都有活动正在进行。
 * 那么必须开多一个教室，k+1. 就是说所有的活动中时间重叠最多的那个数量。意味着有个时间段有 N 个活动都要进行。
 * 那么必须开 N 个教室。其它的重叠活动数都小于这个max{重叠活动数}，N个教室中肯定有教室空下来可以安排剩余的活动。
 * 小于 N 个教室不行, 大于 N 个教室浪费。所以这个策略是正确的。
 *
 * 剩下的就是该如何实现算法？输入的活动都是无序的, 在遍历Ai个活动时需要判断与哪些活动重叠，这个重叠的是哪个时间段，
 * 非常麻烦。尝试将活动按照结束时间升序排列。如果Ai个活动和前面Ai-1活动重叠则代表Ai和前面的时间段重叠，
 * 否则Ai开始一个独立的时间段, Ai+1...n 是否与Ai的时间段重叠。以此类推，遇到一个不重叠的代表开启一个新的时间段。
 * 否则与前面时间段重叠，重叠数+1. 记录最大的重叠数即可。
 *
 * 时间复杂度:
 * 快排序 O(N*logN) + 线性判断 O(N)
 *
 * created by Joseph
 * at 2018/7/31 17:27
 */
public class ActivePlan2 {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1 << 16);
        int activeCount ;
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

        // 按照结束时间升序排列
        doQuickSort(activeArray, 0, activeArray.length - 1);

        int maxConflict = 0, currentConflict = 0;

        for (int i = 1; i < activeArray.length; i++){
            // Ai 和 Ai-1 活动时间重叠
            if (activeArray[i].startTime < activeArray[i-1].endTime){
                if (++currentConflict > maxConflict){
                    maxConflict = currentConflict;
                }
            }
            // Ai 和前面活动不重叠, 开启一个以Ai起始的新时间段
            else {
                currentConflict = 0;
            }
        }

        System.out.println(maxConflict+1);
    }

    public static <T extends Comparable<? super T>> void doQuickSort(T[] arr,int left,int right){
        if(left >= right){
            return;
        }

        T base = arr[left];

        int i = quickSort(arr, base, left, right);

        doQuickSort(arr,left,i-1);
        doQuickSort(arr,i+1,right);
    }
    private static <T extends Comparable<? super T>> int quickSort(T[] arr, T base, int left, int right){
        while (left < right){
            while (left < right && arr[right].compareTo(base) >= 0){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left].compareTo(base) <= 0){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = base;
        return left;
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
