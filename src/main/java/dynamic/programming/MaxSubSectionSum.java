package dynamic.programming;

import java.util.Random;

/**
 * Created by Joseph on 2017/7/12.
 *  给定长度为n的整数序列，a[1...n], 求[1,n]某个子区间[i , j]使得a[i]+…+a[j]和最大.或者求出最大的这个和.
 *  例如(-2,11,-4,13,-5,2)的最大子段和为20,所求子区间为[2,4].
 *
 *  算法思路:
 *  令b[j]表示以位置 j 为终点的所有子区间中和最大的一个
 子问题:如j为终点的最大子区间包含了位置j-1,则以j-1为终点的最大子区间必然包括在其中
 如果b[j-1] >0, 那么显然b[j] = b[j-1] + a[j]，用之前最大的一个加上a[j]即可，因为a[j]必须包含
 如果b[j-1]<=0,那么b[j] = a[j] ,因为既然最大，前面的负数必然不能使你更大

 所以推出 递推公式   设 b(j)为数组中以j为终点的最大子段和，那么对于前面的元素关系为b(j-1)>0 则b(j)=b(j-1)+a[j] 否则 b(j)=a[j]
 b(j) = max{b(j-1)+a[j],a[j]}
 */
public class MaxSubSectionSum {

    public static void main(String[] args){
        int[] arr = {9,-14,-5,6,-12,13,-2,9,4,13};
//        int[] arr = new int[10];
//        Random random = new Random();
        for(int j=0; j<10; j++){
//            int i = random.nextInt(20);
//            int k = random.nextInt(20);
//            arr[j] = i-k;
            System.out.print(arr[j]+",");
        }

//        combination(arr);
        dynamicAl(arr);
    }

    //组合算法，穷举
    private static void combination(int[] arr){
        int sum=0,start=0,end=0,temp;
        for(int i=0; i<arr.length; i++){
            temp=arr[i];
            for(int j=i+1; j<arr.length; j++){
                temp += arr[j];
                if(temp>sum){
                    sum = temp;
                    start = i;
                    end = j;
                }
            }
        }

        System.out.println("最大子区间和="+sum+" 开始下标="+start+" 结束下标="+end);
    }

    //动态规划算法
    private static void dynamicAl(int[] arr){
        int sum = 0,temp=0,start=0,end=0;
        for(int i=0; i<arr.length; i++){
            if(temp>0){
                temp += arr[i];
            }else{
                temp = arr[i];
                start = i;
            }
            if(temp>sum){
                sum = temp;
                end = i;
            }
        }

        System.out.println("最大子区间和="+sum+" 开始下标="+start+" 结束下标="+end);
    }
}