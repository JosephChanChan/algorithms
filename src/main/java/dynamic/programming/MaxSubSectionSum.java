package dynamic.programming;

/**
 * 给定长度为n的整数序列，a[1...n], 求[1,n]某个子区间[i , j]使得a[i]+…+a[i]和最大.或者求出最大的这个和.
 *    例如(-2,11,-4,13,-5,2)的最大子段和为20,所求子区间为[2,4].
 *    这个最大子序列和问题，必须求出至少包含一个元素的子序列，意思是就算序列中全都是负数，那也只能选择出最小的哪个负数，而不是0
 *    例如 {-1 -2 -3 -4} 最大子序列应该是 -1，而不是不选择
 *
 *    算法思路:
 *    令f[i]表示以位置 i 为终点的所有子区间中和最大的一个(必须包含a[i])
 *  子问题:如i为终点的最大子区间包含了位置i-1,则以i-1为终点的最大子区间必然包括在其中
 *  如果f[i-1] >0, 那么显然f[i] = f[i-1] + a[i]，用之前最大的一个加上a[i]即可，因为a[i]必须包含
 *  如果f[i-1]<=0,那么f[i] = a[i] ,因为既然最大，前面的负数必然不能使你更大
 *
 *  所以推出 递推公式   设 f(i)为数组中以i为终点的最大子段和，那么对于前面的元素关系为f(i-1)>0 则f(i)=f(i-1)+a[i] 否则 f(i)=a[i]
 *  f(i) = max{f(i-1)+a[i],a[i]}
 *
 *  Created by Joseph on 2017/7/12.
 */
public class MaxSubSectionSum {

    public static void main(String[] args){
        int[] arr = {9,-14,-5,6,-12,13,-2,9,4,13};
        for(int j=0; j<10; j++){
            System.out.print(arr[j]+",");
        }

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